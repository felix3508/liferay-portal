/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.commerce.product.service.impl;

import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPDefinitionLink;
import com.liferay.commerce.product.model.CProduct;
import com.liferay.commerce.product.service.CPDefinitionLocalService;
import com.liferay.commerce.product.service.CProductLocalService;
import com.liferay.commerce.product.service.base.CPDefinitionLinkLocalServiceBaseImpl;
import com.liferay.commerce.product.service.persistence.CPDefinitionPersistence;
import com.liferay.commerce.product.service.persistence.CProductPersistence;
import com.liferay.expando.kernel.service.ExpandoRowLocalService;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.SystemEventConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.systemevent.SystemEvent;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.util.List;

/**
 * @author Alessio Antonio Rendina
 * @author Marco Leo
 */
public class CPDefinitionLinkLocalServiceImpl
	extends CPDefinitionLinkLocalServiceBaseImpl {

	@Override
	public CPDefinitionLink addCPDefinitionLinkByCProductId(
			long cpDefinitionId, long cProductId, double priority, String type,
			ServiceContext serviceContext)
		throws PortalException {

		CPDefinition cpDefinition;

		if (_cpDefinitionLocalService.isVersionable(cpDefinitionId)) {
			cpDefinition = _cpDefinitionLocalService.copyCPDefinition(
				cpDefinitionId);

			cpDefinitionId = cpDefinition.getCPDefinitionId();
		}
		else {
			cpDefinition = _cpDefinitionPersistence.findByPrimaryKey(
				cpDefinitionId);
		}

		User user = _userLocalService.getUser(serviceContext.getUserId());

		long cpDefinitionLinkId = counterLocalService.increment();

		CPDefinitionLink cpDefinitionLink = cpDefinitionLinkPersistence.create(
			cpDefinitionLinkId);

		cpDefinitionLink.setGroupId(cpDefinition.getGroupId());
		cpDefinitionLink.setCompanyId(user.getCompanyId());
		cpDefinitionLink.setUserId(user.getUserId());
		cpDefinitionLink.setUserName(user.getFullName());
		cpDefinitionLink.setCPDefinitionId(cpDefinition.getCPDefinitionId());
		cpDefinitionLink.setCProductId(cProductId);
		cpDefinitionLink.setPriority(priority);
		cpDefinitionLink.setType(type);
		cpDefinitionLink.setExpandoBridgeAttributes(serviceContext);

		cpDefinitionLink = cpDefinitionLinkPersistence.update(cpDefinitionLink);

		CProduct cProduct = _cProductLocalService.getCProduct(cProductId);

		_reindexCPDefinition(cProduct.getPublishedCPDefinitionId());

		_reindexCPDefinition(cpDefinitionId);

		return cpDefinitionLink;
	}

	@Override
	@SystemEvent(type = SystemEventConstants.TYPE_DELETE)
	public CPDefinitionLink deleteCPDefinitionLink(
			CPDefinitionLink cpDefinitionLink)
		throws PortalException {

		if (_cpDefinitionLocalService.isVersionable(
				cpDefinitionLink.getCPDefinitionId())) {

			try {
				CPDefinition newCPDefinition =
					_cpDefinitionLocalService.copyCPDefinition(
						cpDefinitionLink.getCPDefinitionId());

				cpDefinitionLink = cpDefinitionLinkPersistence.findByC_C_T(
					newCPDefinition.getCPDefinitionId(),
					cpDefinitionLink.getCProductId(),
					cpDefinitionLink.getType());
			}
			catch (PortalException portalException) {
				throw new SystemException(portalException);
			}
		}

		// Commerce product definition link

		cpDefinitionLinkPersistence.remove(cpDefinitionLink);

		// Expando

		_expandoRowLocalService.deleteRows(
			cpDefinitionLink.getCPDefinitionLinkId());

		CProduct cProduct = _cProductLocalService.getCProduct(
			cpDefinitionLink.getCProductId());

		_reindexCPDefinition(cProduct.getPublishedCPDefinitionId());

		_reindexCPDefinition(cpDefinitionLink.getCPDefinitionId());

		return cpDefinitionLink;
	}

	@Override
	public CPDefinitionLink deleteCPDefinitionLink(long cpDefinitionLinkId)
		throws PortalException {

		CPDefinitionLink cpDefinitionLink =
			cpDefinitionLinkPersistence.findByPrimaryKey(cpDefinitionLinkId);

		return cpDefinitionLinkLocalService.deleteCPDefinitionLink(
			cpDefinitionLink);
	}

	@Override
	public void deleteCPDefinitionLinksByCPDefinitionId(long cpDefinitionId)
		throws PortalException {

		List<CPDefinitionLink> cpDefinitionLinks =
			cpDefinitionLinkPersistence.findByCPDefinitionId(cpDefinitionId);

		for (CPDefinitionLink cpDefinitionLink : cpDefinitionLinks) {
			cpDefinitionLinkLocalService.deleteCPDefinitionLink(
				cpDefinitionLink);
		}
	}

	@Override
	public void deleteCPDefinitionLinksByCProductId(long cProductId)
		throws PortalException {

		List<CPDefinitionLink> cpDefinitionLinks =
			cpDefinitionLinkPersistence.findByCProductId(cProductId);

		for (CPDefinitionLink cpDefinitionLink : cpDefinitionLinks) {
			cpDefinitionLinkLocalService.deleteCPDefinitionLink(
				cpDefinitionLink);
		}
	}

	@Override
	public CPDefinitionLink fetchCPDefinitionLink(
		long cpDefinitionId, long cProductId, String type) {

		return cpDefinitionLinkPersistence.fetchByC_C_T(
			cpDefinitionId, cProductId, type);
	}

	@Override
	public List<CPDefinitionLink> getCPDefinitionLinks(long cpDefinitionId) {
		return cpDefinitionLinkPersistence.findByCPDefinitionId(cpDefinitionId);
	}

	@Override
	public List<CPDefinitionLink> getCPDefinitionLinks(
		long cpDefinitionId, int start, int end) {

		return cpDefinitionLinkPersistence.findByCPDefinitionId(
			cpDefinitionId, start, end);
	}

	@Override
	public List<CPDefinitionLink> getCPDefinitionLinks(
		long cpDefinitionId, String type) {

		return cpDefinitionLinkPersistence.findByCPD_T(cpDefinitionId, type);
	}

	@Override
	public List<CPDefinitionLink> getCPDefinitionLinks(
		long cpDefinitionId, String type, int start, int end,
		OrderByComparator<CPDefinitionLink> orderByComparator) {

		return cpDefinitionLinkPersistence.findByCPD_T(
			cpDefinitionId, type, start, end, orderByComparator);
	}

	@Override
	public int getCPDefinitionLinksCount(long cpDefinitionId) {
		return cpDefinitionLinkPersistence.countByCPDefinitionId(
			cpDefinitionId);
	}

	@Override
	public int getCPDefinitionLinksCount(long cpDefinitionId, String type) {
		return cpDefinitionLinkPersistence.countByCPD_T(cpDefinitionId, type);
	}

	@Override
	public List<CPDefinitionLink> getReverseCPDefinitionLinks(
		long cProductId, String type) {

		return cpDefinitionLinkPersistence.findByCP_T(cProductId, type);
	}

	@Override
	public CPDefinitionLink updateCPDefinitionLink(
			long cpDefinitionLinkId, double priority,
			ServiceContext serviceContext)
		throws PortalException {

		CPDefinitionLink cpDefinitionLink =
			cpDefinitionLinkPersistence.findByPrimaryKey(cpDefinitionLinkId);

		if (_cpDefinitionLocalService.isVersionable(
				cpDefinitionLink.getCPDefinitionId())) {

			CPDefinition newCPDefinition =
				_cpDefinitionLocalService.copyCPDefinition(
					cpDefinitionLink.getCPDefinitionId());

			cpDefinitionLink = cpDefinitionLinkPersistence.findByC_C_T(
				newCPDefinition.getCPDefinitionId(),
				cpDefinitionLink.getCProductId(), cpDefinitionLink.getType());
		}

		cpDefinitionLink.setPriority(priority);
		cpDefinitionLink.setExpandoBridgeAttributes(serviceContext);

		cpDefinitionLink = cpDefinitionLinkPersistence.update(cpDefinitionLink);

		_reindexCPDefinition(cpDefinitionLink.getCPDefinitionId());

		CProduct cProduct = _cProductPersistence.findByPrimaryKey(
			cpDefinitionLink.getCProductId());

		_reindexCPDefinition(cProduct.getPublishedCPDefinitionId());

		return cpDefinitionLink;
	}

	@Override
	public void updateCPDefinitionLinkCProductIds(
			long cpDefinitionId, long[] cProductIds, String type,
			ServiceContext serviceContext)
		throws PortalException {

		if (cProductIds == null) {
			return;
		}

		List<CPDefinitionLink> cpDefinitionLinks = getCPDefinitionLinks(
			cpDefinitionId, type);

		for (CPDefinitionLink cpDefinitionLink : cpDefinitionLinks) {
			if (!ArrayUtil.contains(
					cProductIds, cpDefinitionLink.getCProductId())) {

				cpDefinitionLinkLocalService.deleteCPDefinitionLink(
					cpDefinitionLink);
			}
		}

		CPDefinition cpDefinition = _cpDefinitionPersistence.findByPrimaryKey(
			cpDefinitionId);

		for (long cProductId : cProductIds) {
			if (cpDefinition.getCProductId() != cProductId) {
				CPDefinitionLink cpDefinitionLink =
					cpDefinitionLinkPersistence.fetchByC_C_T(
						cpDefinitionId, cProductId, type);

				if (cpDefinitionLink == null) {
					cpDefinitionLinkLocalService.
						addCPDefinitionLinkByCProductId(
							cpDefinitionId, cProductId, 0, type,
							serviceContext);
				}
			}

			CProduct cProduct = _cProductLocalService.getCProduct(cProductId);

			_reindexCPDefinition(cProduct.getPublishedCPDefinitionId());
		}

		_reindexCPDefinition(cpDefinitionId);
	}

	private void _reindexCPDefinition(long cpDefinitionId)
		throws PortalException {

		Indexer<CPDefinition> indexer = IndexerRegistryUtil.nullSafeGetIndexer(
			CPDefinition.class);

		indexer.reindex(CPDefinition.class.getName(), cpDefinitionId);
	}

	@BeanReference(type = CPDefinitionLocalService.class)
	private CPDefinitionLocalService _cpDefinitionLocalService;

	@BeanReference(type = CPDefinitionPersistence.class)
	private CPDefinitionPersistence _cpDefinitionPersistence;

	@BeanReference(type = CProductLocalService.class)
	private CProductLocalService _cProductLocalService;

	@BeanReference(type = CProductPersistence.class)
	private CProductPersistence _cProductPersistence;

	@ServiceReference(type = ExpandoRowLocalService.class)
	private ExpandoRowLocalService _expandoRowLocalService;

	@ServiceReference(type = UserLocalService.class)
	private UserLocalService _userLocalService;

}
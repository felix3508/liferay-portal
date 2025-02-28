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

package com.liferay.marketplace.store.web.internal.oauth.util;

import com.liferay.expando.kernel.model.ExpandoColumnConstants;
import com.liferay.expando.kernel.model.ExpandoTable;
import com.liferay.expando.kernel.model.ExpandoValue;
import com.liferay.expando.kernel.service.ExpandoColumnLocalService;
import com.liferay.expando.kernel.service.ExpandoTableLocalService;
import com.liferay.expando.kernel.service.ExpandoValueLocalService;
import com.liferay.marketplace.store.web.internal.configuration.MarketplaceStoreWebConfigurationValues;
import com.liferay.marketplace.store.web.internal.oauth.api.MarketplaceApi;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.service.CompanyLocalService;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import org.scribe.builder.api.Api;
import org.scribe.model.OAuthConfig;
import org.scribe.model.SignatureType;
import org.scribe.model.Token;
import org.scribe.oauth.OAuthService;

/**
 * @author Ryan Park
 */
@Component(immediate = true, service = OAuthManager.class)
public class OAuthManager {

	public void deleteAccessToken(User user) throws PortalException {
		_expandoValueLocalService.deleteValue(
			user.getCompanyId(), User.class.getName(), "MP", "accessSecret",
			user.getUserId());
		_expandoValueLocalService.deleteValue(
			user.getCompanyId(), User.class.getName(), "MP", "accessToken",
			user.getUserId());
	}

	public void deleteRequestToken(User user) throws PortalException {
		_expandoValueLocalService.deleteValue(
			user.getCompanyId(), User.class.getName(), "MP", "requestSecret",
			user.getUserId());
		_expandoValueLocalService.deleteValue(
			user.getCompanyId(), User.class.getName(), "MP", "requestToken",
			user.getUserId());
	}

	public Token getAccessToken(User user) throws PortalException {
		ExpandoValue secretExpandoValue = _expandoValueLocalService.getValue(
			user.getCompanyId(), User.class.getName(), "MP", "accessSecret",
			user.getUserId());
		ExpandoValue tokenExpandoValue = _expandoValueLocalService.getValue(
			user.getCompanyId(), User.class.getName(), "MP", "accessToken",
			user.getUserId());

		if ((secretExpandoValue == null) || (tokenExpandoValue == null)) {
			return null;
		}

		return new Token(
			tokenExpandoValue.getString(), secretExpandoValue.getString());
	}

	public OAuthService getOAuthService() {
		Api api = new MarketplaceApi();

		OAuthConfig oAuthConfig = new OAuthConfig(
			MarketplaceStoreWebConfigurationValues.MARKETPLACE_KEY,
			MarketplaceStoreWebConfigurationValues.MARKETPLACE_SECRET,
			MarketplaceStoreWebConfigurationValues.MARKETPLACE_URL,
			SignatureType.Header, null, null);

		return api.createService(oAuthConfig);
	}

	public Token getRequestToken(User user) throws PortalException {
		ExpandoValue secretExpandoValue = _expandoValueLocalService.getValue(
			user.getCompanyId(), User.class.getName(), "MP", "requestSecret",
			user.getUserId());
		ExpandoValue tokenExpandoValue = _expandoValueLocalService.getValue(
			user.getCompanyId(), User.class.getName(), "MP", "requestToken",
			user.getUserId());

		if ((secretExpandoValue == null) || (tokenExpandoValue == null)) {
			return null;
		}

		return new Token(
			tokenExpandoValue.getString(), secretExpandoValue.getString());
	}

	public void updateAccessToken(User user, Token token)
		throws PortalException {

		_expandoValueLocalService.addValue(
			user.getCompanyId(), User.class.getName(), "MP", "accessSecret",
			user.getUserId(), token.getSecret());
		_expandoValueLocalService.addValue(
			user.getCompanyId(), User.class.getName(), "MP", "accessToken",
			user.getUserId(), token.getToken());
	}

	public void updateRequestToken(User user, Token token)
		throws PortalException {

		_expandoValueLocalService.addValue(
			user.getCompanyId(), User.class.getName(), "MP", "requestSecret",
			user.getUserId(), token.getSecret());
		_expandoValueLocalService.addValue(
			user.getCompanyId(), User.class.getName(), "MP", "requestToken",
			user.getUserId(), token.getToken());
	}

	@Activate
	protected void activate() {
		_companyLocalService.forEachCompanyId(
			companyId -> {
				try {
					_setupExpando(companyId);
				}
				catch (Exception exception) {
					if (_log.isWarnEnabled()) {
						_log.warn(
							StringBundler.concat(
								"Unable to setup Marketplace for company ",
								companyId, ": ", exception.getMessage()));
					}
				}
			});
	}

	private void _setupExpando(long companyId) throws Exception {
		ExpandoTable table = _expandoTableLocalService.fetchTable(
			companyId,
			_classNameLocalService.getClassNameId(User.class.getName()), "MP");

		if (table != null) {
			return;
		}

		table = _expandoTableLocalService.addTable(
			companyId, User.class.getName(), "MP");

		_expandoColumnLocalService.addColumn(
			table.getTableId(), "accessSecret", ExpandoColumnConstants.STRING);
		_expandoColumnLocalService.addColumn(
			table.getTableId(), "accessToken", ExpandoColumnConstants.STRING);
		_expandoColumnLocalService.addColumn(
			table.getTableId(), "requestSecret", ExpandoColumnConstants.STRING);
		_expandoColumnLocalService.addColumn(
			table.getTableId(), "requestToken", ExpandoColumnConstants.STRING);
	}

	private static final Log _log = LogFactoryUtil.getLog(OAuthManager.class);

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private CompanyLocalService _companyLocalService;

	@Reference
	private ExpandoColumnLocalService _expandoColumnLocalService;

	@Reference
	private ExpandoTableLocalService _expandoTableLocalService;

	@Reference
	private ExpandoValueLocalService _expandoValueLocalService;

	@Reference(target = ModuleServiceLifecycle.PORTAL_INITIALIZED)
	private ModuleServiceLifecycle _moduleServiceLifecycle;

}
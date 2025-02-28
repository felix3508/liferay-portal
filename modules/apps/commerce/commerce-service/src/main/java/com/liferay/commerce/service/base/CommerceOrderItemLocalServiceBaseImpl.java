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

package com.liferay.commerce.service.base;

import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.commerce.service.CommerceOrderItemLocalService;
import com.liferay.commerce.service.CommerceOrderItemLocalServiceUtil;
import com.liferay.commerce.service.persistence.CommerceOrderItemFinder;
import com.liferay.commerce.service.persistence.CommerceOrderItemPersistence;
import com.liferay.exportimport.kernel.lar.ExportImportHelperUtil;
import com.liferay.exportimport.kernel.lar.ManifestSummary;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.PersistedModelLocalServiceRegistry;
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the commerce order item local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.commerce.service.impl.CommerceOrderItemLocalServiceImpl}.
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @see com.liferay.commerce.service.impl.CommerceOrderItemLocalServiceImpl
 * @generated
 */
public abstract class CommerceOrderItemLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements CommerceOrderItemLocalService, IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>CommerceOrderItemLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>CommerceOrderItemLocalServiceUtil</code>.
	 */

	/**
	 * Adds the commerce order item to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CommerceOrderItemLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param commerceOrderItem the commerce order item
	 * @return the commerce order item that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CommerceOrderItem addCommerceOrderItem(
		CommerceOrderItem commerceOrderItem) {

		commerceOrderItem.setNew(true);

		return commerceOrderItemPersistence.update(commerceOrderItem);
	}

	/**
	 * Creates a new commerce order item with the primary key. Does not add the commerce order item to the database.
	 *
	 * @param commerceOrderItemId the primary key for the new commerce order item
	 * @return the new commerce order item
	 */
	@Override
	@Transactional(enabled = false)
	public CommerceOrderItem createCommerceOrderItem(long commerceOrderItemId) {
		return commerceOrderItemPersistence.create(commerceOrderItemId);
	}

	/**
	 * Deletes the commerce order item with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CommerceOrderItemLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param commerceOrderItemId the primary key of the commerce order item
	 * @return the commerce order item that was removed
	 * @throws PortalException if a commerce order item with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public CommerceOrderItem deleteCommerceOrderItem(long commerceOrderItemId)
		throws PortalException {

		return commerceOrderItemPersistence.remove(commerceOrderItemId);
	}

	/**
	 * Deletes the commerce order item from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CommerceOrderItemLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param commerceOrderItem the commerce order item
	 * @return the commerce order item that was removed
	 * @throws PortalException
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public CommerceOrderItem deleteCommerceOrderItem(
			CommerceOrderItem commerceOrderItem)
		throws PortalException {

		return commerceOrderItemPersistence.remove(commerceOrderItem);
	}

	@Override
	public <T> T dslQuery(DSLQuery dslQuery) {
		return commerceOrderItemPersistence.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(DSLQuery dslQuery) {
		Long count = dslQuery(dslQuery);

		return count.intValue();
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(
			CommerceOrderItem.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return commerceOrderItemPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.model.impl.CommerceOrderItemModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return commerceOrderItemPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.model.impl.CommerceOrderItemModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return commerceOrderItemPersistence.findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return commerceOrderItemPersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		DynamicQuery dynamicQuery, Projection projection) {

		return commerceOrderItemPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public CommerceOrderItem fetchCommerceOrderItem(long commerceOrderItemId) {
		return commerceOrderItemPersistence.fetchByPrimaryKey(
			commerceOrderItemId);
	}

	/**
	 * Returns the commerce order item matching the UUID and group.
	 *
	 * @param uuid the commerce order item's UUID
	 * @param groupId the primary key of the group
	 * @return the matching commerce order item, or <code>null</code> if a matching commerce order item could not be found
	 */
	@Override
	public CommerceOrderItem fetchCommerceOrderItemByUuidAndGroupId(
		String uuid, long groupId) {

		return commerceOrderItemPersistence.fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the commerce order item with the matching external reference code and company.
	 *
	 * @param companyId the primary key of the company
	 * @param externalReferenceCode the commerce order item's external reference code
	 * @return the matching commerce order item, or <code>null</code> if a matching commerce order item could not be found
	 */
	@Override
	public CommerceOrderItem fetchCommerceOrderItemByExternalReferenceCode(
		long companyId, String externalReferenceCode) {

		return commerceOrderItemPersistence.fetchByC_ERC(
			companyId, externalReferenceCode);
	}

	/**
	 * @deprecated As of Cavanaugh (7.4.x), replaced by {@link #fetchCommerceOrderItemByExternalReferenceCode(long, String)}
	 */
	@Deprecated
	@Override
	public CommerceOrderItem fetchCommerceOrderItemByReferenceCode(
		long companyId, String externalReferenceCode) {

		return fetchCommerceOrderItemByExternalReferenceCode(
			companyId, externalReferenceCode);
	}

	/**
	 * Returns the commerce order item with the matching external reference code and company.
	 *
	 * @param companyId the primary key of the company
	 * @param externalReferenceCode the commerce order item's external reference code
	 * @return the matching commerce order item
	 * @throws PortalException if a matching commerce order item could not be found
	 */
	@Override
	public CommerceOrderItem getCommerceOrderItemByExternalReferenceCode(
			long companyId, String externalReferenceCode)
		throws PortalException {

		return commerceOrderItemPersistence.findByC_ERC(
			companyId, externalReferenceCode);
	}

	/**
	 * Returns the commerce order item with the primary key.
	 *
	 * @param commerceOrderItemId the primary key of the commerce order item
	 * @return the commerce order item
	 * @throws PortalException if a commerce order item with the primary key could not be found
	 */
	@Override
	public CommerceOrderItem getCommerceOrderItem(long commerceOrderItemId)
		throws PortalException {

		return commerceOrderItemPersistence.findByPrimaryKey(
			commerceOrderItemId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(
			commerceOrderItemLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(CommerceOrderItem.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("commerceOrderItemId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			commerceOrderItemLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(CommerceOrderItem.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"commerceOrderItemId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(
			commerceOrderItemLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(CommerceOrderItem.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("commerceOrderItemId");
	}

	@Override
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		final PortletDataContext portletDataContext) {

		final ExportActionableDynamicQuery exportActionableDynamicQuery =
			new ExportActionableDynamicQuery() {

				@Override
				public long performCount() throws PortalException {
					ManifestSummary manifestSummary =
						portletDataContext.getManifestSummary();

					StagedModelType stagedModelType = getStagedModelType();

					long modelAdditionCount = super.performCount();

					manifestSummary.addModelAdditionCount(
						stagedModelType, modelAdditionCount);

					long modelDeletionCount =
						ExportImportHelperUtil.getModelDeletionCount(
							portletDataContext, stagedModelType);

					manifestSummary.addModelDeletionCount(
						stagedModelType, modelDeletionCount);

					return modelAdditionCount;
				}

			};

		initActionableDynamicQuery(exportActionableDynamicQuery);

		exportActionableDynamicQuery.setAddCriteriaMethod(
			new ActionableDynamicQuery.AddCriteriaMethod() {

				@Override
				public void addCriteria(DynamicQuery dynamicQuery) {
					portletDataContext.addDateRangeCriteria(
						dynamicQuery, "modifiedDate");
				}

			});

		exportActionableDynamicQuery.setCompanyId(
			portletDataContext.getCompanyId());

		exportActionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod
				<CommerceOrderItem>() {

				@Override
				public void performAction(CommerceOrderItem commerceOrderItem)
					throws PortalException {

					StagedModelDataHandlerUtil.exportStagedModel(
						portletDataContext, commerceOrderItem);
				}

			});
		exportActionableDynamicQuery.setStagedModelType(
			new StagedModelType(
				PortalUtil.getClassNameId(CommerceOrderItem.class.getName())));

		return exportActionableDynamicQuery;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return commerceOrderItemPersistence.create(
			((Long)primaryKeyObj).longValue());
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		if (_log.isWarnEnabled()) {
			_log.warn(
				"Implement CommerceOrderItemLocalServiceImpl#deleteCommerceOrderItem(CommerceOrderItem) to avoid orphaned data");
		}

		return commerceOrderItemLocalService.deleteCommerceOrderItem(
			(CommerceOrderItem)persistedModel);
	}

	@Override
	public BasePersistence<CommerceOrderItem> getBasePersistence() {
		return commerceOrderItemPersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return commerceOrderItemPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns all the commerce order items matching the UUID and company.
	 *
	 * @param uuid the UUID of the commerce order items
	 * @param companyId the primary key of the company
	 * @return the matching commerce order items, or an empty list if no matches were found
	 */
	@Override
	public List<CommerceOrderItem> getCommerceOrderItemsByUuidAndCompanyId(
		String uuid, long companyId) {

		return commerceOrderItemPersistence.findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of commerce order items matching the UUID and company.
	 *
	 * @param uuid the UUID of the commerce order items
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of commerce order items
	 * @param end the upper bound of the range of commerce order items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching commerce order items, or an empty list if no matches were found
	 */
	@Override
	public List<CommerceOrderItem> getCommerceOrderItemsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<CommerceOrderItem> orderByComparator) {

		return commerceOrderItemPersistence.findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the commerce order item matching the UUID and group.
	 *
	 * @param uuid the commerce order item's UUID
	 * @param groupId the primary key of the group
	 * @return the matching commerce order item
	 * @throws PortalException if a matching commerce order item could not be found
	 */
	@Override
	public CommerceOrderItem getCommerceOrderItemByUuidAndGroupId(
			String uuid, long groupId)
		throws PortalException {

		return commerceOrderItemPersistence.findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns a range of all the commerce order items.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.model.impl.CommerceOrderItemModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce order items
	 * @param end the upper bound of the range of commerce order items (not inclusive)
	 * @return the range of commerce order items
	 */
	@Override
	public List<CommerceOrderItem> getCommerceOrderItems(int start, int end) {
		return commerceOrderItemPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of commerce order items.
	 *
	 * @return the number of commerce order items
	 */
	@Override
	public int getCommerceOrderItemsCount() {
		return commerceOrderItemPersistence.countAll();
	}

	/**
	 * Updates the commerce order item in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CommerceOrderItemLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param commerceOrderItem the commerce order item
	 * @return the commerce order item that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CommerceOrderItem updateCommerceOrderItem(
		CommerceOrderItem commerceOrderItem) {

		return commerceOrderItemPersistence.update(commerceOrderItem);
	}

	/**
	 * Returns the commerce order item local service.
	 *
	 * @return the commerce order item local service
	 */
	public CommerceOrderItemLocalService getCommerceOrderItemLocalService() {
		return commerceOrderItemLocalService;
	}

	/**
	 * Sets the commerce order item local service.
	 *
	 * @param commerceOrderItemLocalService the commerce order item local service
	 */
	public void setCommerceOrderItemLocalService(
		CommerceOrderItemLocalService commerceOrderItemLocalService) {

		this.commerceOrderItemLocalService = commerceOrderItemLocalService;
	}

	/**
	 * Returns the commerce order item persistence.
	 *
	 * @return the commerce order item persistence
	 */
	public CommerceOrderItemPersistence getCommerceOrderItemPersistence() {
		return commerceOrderItemPersistence;
	}

	/**
	 * Sets the commerce order item persistence.
	 *
	 * @param commerceOrderItemPersistence the commerce order item persistence
	 */
	public void setCommerceOrderItemPersistence(
		CommerceOrderItemPersistence commerceOrderItemPersistence) {

		this.commerceOrderItemPersistence = commerceOrderItemPersistence;
	}

	/**
	 * Returns the commerce order item finder.
	 *
	 * @return the commerce order item finder
	 */
	public CommerceOrderItemFinder getCommerceOrderItemFinder() {
		return commerceOrderItemFinder;
	}

	/**
	 * Sets the commerce order item finder.
	 *
	 * @param commerceOrderItemFinder the commerce order item finder
	 */
	public void setCommerceOrderItemFinder(
		CommerceOrderItemFinder commerceOrderItemFinder) {

		this.commerceOrderItemFinder = commerceOrderItemFinder;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.kernel.service.CounterLocalService
		getCounterLocalService() {

		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.kernel.service.CounterLocalService
			counterLocalService) {

		this.counterLocalService = counterLocalService;
	}

	public void afterPropertiesSet() {
		persistedModelLocalServiceRegistry.register(
			"com.liferay.commerce.model.CommerceOrderItem",
			commerceOrderItemLocalService);

		_setLocalServiceUtilService(commerceOrderItemLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.liferay.commerce.model.CommerceOrderItem");

		_setLocalServiceUtilService(null);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return CommerceOrderItemLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return CommerceOrderItem.class;
	}

	protected String getModelClassName() {
		return CommerceOrderItem.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource =
				commerceOrderItemPersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(
				dataSource, sql);

			sqlUpdate.update();
		}
		catch (Exception exception) {
			throw new SystemException(exception);
		}
	}

	private void _setLocalServiceUtilService(
		CommerceOrderItemLocalService commerceOrderItemLocalService) {

		try {
			Field field =
				CommerceOrderItemLocalServiceUtil.class.getDeclaredField(
					"_service");

			field.setAccessible(true);

			field.set(null, commerceOrderItemLocalService);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@BeanReference(type = CommerceOrderItemLocalService.class)
	protected CommerceOrderItemLocalService commerceOrderItemLocalService;

	@BeanReference(type = CommerceOrderItemPersistence.class)
	protected CommerceOrderItemPersistence commerceOrderItemPersistence;

	@BeanReference(type = CommerceOrderItemFinder.class)
	protected CommerceOrderItemFinder commerceOrderItemFinder;

	@ServiceReference(
		type = com.liferay.counter.kernel.service.CounterLocalService.class
	)
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceOrderItemLocalServiceBaseImpl.class);

	@ServiceReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry
		persistedModelLocalServiceRegistry;

}
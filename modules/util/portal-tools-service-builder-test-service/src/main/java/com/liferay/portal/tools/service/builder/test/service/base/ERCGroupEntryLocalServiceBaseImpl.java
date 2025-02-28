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

package com.liferay.portal.tools.service.builder.test.service.base;

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
import com.liferay.portal.tools.service.builder.test.model.ERCGroupEntry;
import com.liferay.portal.tools.service.builder.test.service.ERCGroupEntryLocalService;
import com.liferay.portal.tools.service.builder.test.service.ERCGroupEntryLocalServiceUtil;
import com.liferay.portal.tools.service.builder.test.service.persistence.ERCGroupEntryPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the erc group entry local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.portal.tools.service.builder.test.service.impl.ERCGroupEntryLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portal.tools.service.builder.test.service.impl.ERCGroupEntryLocalServiceImpl
 * @generated
 */
public abstract class ERCGroupEntryLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements ERCGroupEntryLocalService, IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>ERCGroupEntryLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>ERCGroupEntryLocalServiceUtil</code>.
	 */

	/**
	 * Adds the erc group entry to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ERCGroupEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param ercGroupEntry the erc group entry
	 * @return the erc group entry that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public ERCGroupEntry addERCGroupEntry(ERCGroupEntry ercGroupEntry) {
		ercGroupEntry.setNew(true);

		return ercGroupEntryPersistence.update(ercGroupEntry);
	}

	/**
	 * Creates a new erc group entry with the primary key. Does not add the erc group entry to the database.
	 *
	 * @param ercGroupEntryId the primary key for the new erc group entry
	 * @return the new erc group entry
	 */
	@Override
	@Transactional(enabled = false)
	public ERCGroupEntry createERCGroupEntry(long ercGroupEntryId) {
		return ercGroupEntryPersistence.create(ercGroupEntryId);
	}

	/**
	 * Deletes the erc group entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ERCGroupEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param ercGroupEntryId the primary key of the erc group entry
	 * @return the erc group entry that was removed
	 * @throws PortalException if a erc group entry with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public ERCGroupEntry deleteERCGroupEntry(long ercGroupEntryId)
		throws PortalException {

		return ercGroupEntryPersistence.remove(ercGroupEntryId);
	}

	/**
	 * Deletes the erc group entry from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ERCGroupEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param ercGroupEntry the erc group entry
	 * @return the erc group entry that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public ERCGroupEntry deleteERCGroupEntry(ERCGroupEntry ercGroupEntry) {
		return ercGroupEntryPersistence.remove(ercGroupEntry);
	}

	@Override
	public <T> T dslQuery(DSLQuery dslQuery) {
		return ercGroupEntryPersistence.dslQuery(dslQuery);
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
			ERCGroupEntry.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return ercGroupEntryPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.tools.service.builder.test.model.impl.ERCGroupEntryModelImpl</code>.
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

		return ercGroupEntryPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.tools.service.builder.test.model.impl.ERCGroupEntryModelImpl</code>.
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

		return ercGroupEntryPersistence.findWithDynamicQuery(
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
		return ercGroupEntryPersistence.countWithDynamicQuery(dynamicQuery);
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

		return ercGroupEntryPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public ERCGroupEntry fetchERCGroupEntry(long ercGroupEntryId) {
		return ercGroupEntryPersistence.fetchByPrimaryKey(ercGroupEntryId);
	}

	/**
	 * Returns the erc group entry matching the UUID and group.
	 *
	 * @param uuid the erc group entry's UUID
	 * @param groupId the primary key of the group
	 * @return the matching erc group entry, or <code>null</code> if a matching erc group entry could not be found
	 */
	@Override
	public ERCGroupEntry fetchERCGroupEntryByUuidAndGroupId(
		String uuid, long groupId) {

		return ercGroupEntryPersistence.fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the erc group entry with the matching external reference code and group.
	 *
	 * @param groupId the primary key of the group
	 * @param externalReferenceCode the erc group entry's external reference code
	 * @return the matching erc group entry, or <code>null</code> if a matching erc group entry could not be found
	 */
	@Override
	public ERCGroupEntry fetchERCGroupEntryByExternalReferenceCode(
		long groupId, String externalReferenceCode) {

		return ercGroupEntryPersistence.fetchByG_ERC(
			groupId, externalReferenceCode);
	}

	/**
	 * @deprecated As of Cavanaugh (7.4.x), replaced by {@link #fetchERCGroupEntryByExternalReferenceCode(long, String)}
	 */
	@Deprecated
	@Override
	public ERCGroupEntry fetchERCGroupEntryByReferenceCode(
		long groupId, String externalReferenceCode) {

		return fetchERCGroupEntryByExternalReferenceCode(
			groupId, externalReferenceCode);
	}

	/**
	 * Returns the erc group entry with the matching external reference code and group.
	 *
	 * @param groupId the primary key of the group
	 * @param externalReferenceCode the erc group entry's external reference code
	 * @return the matching erc group entry
	 * @throws PortalException if a matching erc group entry could not be found
	 */
	@Override
	public ERCGroupEntry getERCGroupEntryByExternalReferenceCode(
			long groupId, String externalReferenceCode)
		throws PortalException {

		return ercGroupEntryPersistence.findByG_ERC(
			groupId, externalReferenceCode);
	}

	/**
	 * Returns the erc group entry with the primary key.
	 *
	 * @param ercGroupEntryId the primary key of the erc group entry
	 * @return the erc group entry
	 * @throws PortalException if a erc group entry with the primary key could not be found
	 */
	@Override
	public ERCGroupEntry getERCGroupEntry(long ercGroupEntryId)
		throws PortalException {

		return ercGroupEntryPersistence.findByPrimaryKey(ercGroupEntryId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(ercGroupEntryLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(ERCGroupEntry.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("ercGroupEntryId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			ercGroupEntryLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(ERCGroupEntry.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"ercGroupEntryId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(ercGroupEntryLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(ERCGroupEntry.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("ercGroupEntryId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return ercGroupEntryPersistence.create(
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
				"Implement ERCGroupEntryLocalServiceImpl#deleteERCGroupEntry(ERCGroupEntry) to avoid orphaned data");
		}

		return ercGroupEntryLocalService.deleteERCGroupEntry(
			(ERCGroupEntry)persistedModel);
	}

	@Override
	public BasePersistence<ERCGroupEntry> getBasePersistence() {
		return ercGroupEntryPersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return ercGroupEntryPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns all the erc group entries matching the UUID and company.
	 *
	 * @param uuid the UUID of the erc group entries
	 * @param companyId the primary key of the company
	 * @return the matching erc group entries, or an empty list if no matches were found
	 */
	@Override
	public List<ERCGroupEntry> getERCGroupEntriesByUuidAndCompanyId(
		String uuid, long companyId) {

		return ercGroupEntryPersistence.findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of erc group entries matching the UUID and company.
	 *
	 * @param uuid the UUID of the erc group entries
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of erc group entries
	 * @param end the upper bound of the range of erc group entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching erc group entries, or an empty list if no matches were found
	 */
	@Override
	public List<ERCGroupEntry> getERCGroupEntriesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<ERCGroupEntry> orderByComparator) {

		return ercGroupEntryPersistence.findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the erc group entry matching the UUID and group.
	 *
	 * @param uuid the erc group entry's UUID
	 * @param groupId the primary key of the group
	 * @return the matching erc group entry
	 * @throws PortalException if a matching erc group entry could not be found
	 */
	@Override
	public ERCGroupEntry getERCGroupEntryByUuidAndGroupId(
			String uuid, long groupId)
		throws PortalException {

		return ercGroupEntryPersistence.findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns a range of all the erc group entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.tools.service.builder.test.model.impl.ERCGroupEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of erc group entries
	 * @param end the upper bound of the range of erc group entries (not inclusive)
	 * @return the range of erc group entries
	 */
	@Override
	public List<ERCGroupEntry> getERCGroupEntries(int start, int end) {
		return ercGroupEntryPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of erc group entries.
	 *
	 * @return the number of erc group entries
	 */
	@Override
	public int getERCGroupEntriesCount() {
		return ercGroupEntryPersistence.countAll();
	}

	/**
	 * Updates the erc group entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ERCGroupEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param ercGroupEntry the erc group entry
	 * @return the erc group entry that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public ERCGroupEntry updateERCGroupEntry(ERCGroupEntry ercGroupEntry) {
		return ercGroupEntryPersistence.update(ercGroupEntry);
	}

	/**
	 * Returns the erc group entry local service.
	 *
	 * @return the erc group entry local service
	 */
	public ERCGroupEntryLocalService getERCGroupEntryLocalService() {
		return ercGroupEntryLocalService;
	}

	/**
	 * Sets the erc group entry local service.
	 *
	 * @param ercGroupEntryLocalService the erc group entry local service
	 */
	public void setERCGroupEntryLocalService(
		ERCGroupEntryLocalService ercGroupEntryLocalService) {

		this.ercGroupEntryLocalService = ercGroupEntryLocalService;
	}

	/**
	 * Returns the erc group entry persistence.
	 *
	 * @return the erc group entry persistence
	 */
	public ERCGroupEntryPersistence getERCGroupEntryPersistence() {
		return ercGroupEntryPersistence;
	}

	/**
	 * Sets the erc group entry persistence.
	 *
	 * @param ercGroupEntryPersistence the erc group entry persistence
	 */
	public void setERCGroupEntryPersistence(
		ERCGroupEntryPersistence ercGroupEntryPersistence) {

		this.ercGroupEntryPersistence = ercGroupEntryPersistence;
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
			"com.liferay.portal.tools.service.builder.test.model.ERCGroupEntry",
			ercGroupEntryLocalService);

		_setLocalServiceUtilService(ercGroupEntryLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.liferay.portal.tools.service.builder.test.model.ERCGroupEntry");

		_setLocalServiceUtilService(null);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return ERCGroupEntryLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return ERCGroupEntry.class;
	}

	protected String getModelClassName() {
		return ERCGroupEntry.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = ercGroupEntryPersistence.getDataSource();

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
		ERCGroupEntryLocalService ercGroupEntryLocalService) {

		try {
			Field field = ERCGroupEntryLocalServiceUtil.class.getDeclaredField(
				"_service");

			field.setAccessible(true);

			field.set(null, ercGroupEntryLocalService);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@BeanReference(type = ERCGroupEntryLocalService.class)
	protected ERCGroupEntryLocalService ercGroupEntryLocalService;

	@BeanReference(type = ERCGroupEntryPersistence.class)
	protected ERCGroupEntryPersistence ercGroupEntryPersistence;

	@ServiceReference(
		type = com.liferay.counter.kernel.service.CounterLocalService.class
	)
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	private static final Log _log = LogFactoryUtil.getLog(
		ERCGroupEntryLocalServiceBaseImpl.class);

	@ServiceReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry
		persistedModelLocalServiceRegistry;

}
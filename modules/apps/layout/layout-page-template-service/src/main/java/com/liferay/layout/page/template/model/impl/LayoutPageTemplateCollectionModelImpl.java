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

package com.liferay.layout.page.template.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.layout.page.template.model.LayoutPageTemplateCollection;
import com.liferay.layout.page.template.model.LayoutPageTemplateCollectionModel;
import com.liferay.layout.page.template.model.LayoutPageTemplateCollectionSoap;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the LayoutPageTemplateCollection service. Represents a row in the &quot;LayoutPageTemplateCollection&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>LayoutPageTemplateCollectionModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link LayoutPageTemplateCollectionImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LayoutPageTemplateCollectionImpl
 * @generated
 */
@JSON(strict = true)
public class LayoutPageTemplateCollectionModelImpl
	extends BaseModelImpl<LayoutPageTemplateCollection>
	implements LayoutPageTemplateCollectionModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a layout page template collection model instance should use the <code>LayoutPageTemplateCollection</code> interface instead.
	 */
	public static final String TABLE_NAME = "LayoutPageTemplateCollection";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"uuid_", Types.VARCHAR},
		{"layoutPageTemplateCollectionId", Types.BIGINT},
		{"groupId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"lptCollectionKey", Types.VARCHAR}, {"name", Types.VARCHAR},
		{"description", Types.VARCHAR}, {"lastPublishDate", Types.TIMESTAMP}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("layoutPageTemplateCollectionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("lptCollectionKey", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("description", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("lastPublishDate", Types.TIMESTAMP);
	}

	public static final String TABLE_SQL_CREATE =
		"create table LayoutPageTemplateCollection (mvccVersion LONG default 0 not null,uuid_ VARCHAR(75) null,layoutPageTemplateCollectionId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,lptCollectionKey VARCHAR(75) null,name VARCHAR(75) null,description STRING null,lastPublishDate DATE null)";

	public static final String TABLE_SQL_DROP =
		"drop table LayoutPageTemplateCollection";

	public static final String ORDER_BY_JPQL =
		" ORDER BY layoutPageTemplateCollection.name ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY LayoutPageTemplateCollection.name ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final long COMPANYID_COLUMN_BITMASK = 1L;

	public static final long GROUPID_COLUMN_BITMASK = 2L;

	public static final long LAYOUTPAGETEMPLATECOLLECTIONKEY_COLUMN_BITMASK =
		4L;

	public static final long NAME_COLUMN_BITMASK = 8L;

	public static final long UUID_COLUMN_BITMASK = 16L;

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static void setEntityCacheEnabled(boolean entityCacheEnabled) {
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static void setFinderCacheEnabled(boolean finderCacheEnabled) {
	}

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static LayoutPageTemplateCollection toModel(
		LayoutPageTemplateCollectionSoap soapModel) {

		if (soapModel == null) {
			return null;
		}

		LayoutPageTemplateCollection model =
			new LayoutPageTemplateCollectionImpl();

		model.setMvccVersion(soapModel.getMvccVersion());
		model.setUuid(soapModel.getUuid());
		model.setLayoutPageTemplateCollectionId(
			soapModel.getLayoutPageTemplateCollectionId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setLayoutPageTemplateCollectionKey(
			soapModel.getLayoutPageTemplateCollectionKey());
		model.setName(soapModel.getName());
		model.setDescription(soapModel.getDescription());
		model.setLastPublishDate(soapModel.getLastPublishDate());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<LayoutPageTemplateCollection> toModels(
		LayoutPageTemplateCollectionSoap[] soapModels) {

		if (soapModels == null) {
			return null;
		}

		List<LayoutPageTemplateCollection> models =
			new ArrayList<LayoutPageTemplateCollection>(soapModels.length);

		for (LayoutPageTemplateCollectionSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public LayoutPageTemplateCollectionModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _layoutPageTemplateCollectionId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setLayoutPageTemplateCollectionId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _layoutPageTemplateCollectionId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return LayoutPageTemplateCollection.class;
	}

	@Override
	public String getModelClassName() {
		return LayoutPageTemplateCollection.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<LayoutPageTemplateCollection, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<LayoutPageTemplateCollection, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<LayoutPageTemplateCollection, Object>
				attributeGetterFunction = entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply(
					(LayoutPageTemplateCollection)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<LayoutPageTemplateCollection, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<LayoutPageTemplateCollection, Object>
				attributeSetterBiConsumer = attributeSetterBiConsumers.get(
					attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(LayoutPageTemplateCollection)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<LayoutPageTemplateCollection, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<LayoutPageTemplateCollection, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, LayoutPageTemplateCollection>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			LayoutPageTemplateCollection.class.getClassLoader(),
			LayoutPageTemplateCollection.class, ModelWrapper.class);

		try {
			Constructor<LayoutPageTemplateCollection> constructor =
				(Constructor<LayoutPageTemplateCollection>)
					proxyClass.getConstructor(InvocationHandler.class);

			return invocationHandler -> {
				try {
					return constructor.newInstance(invocationHandler);
				}
				catch (ReflectiveOperationException
							reflectiveOperationException) {

					throw new InternalError(reflectiveOperationException);
				}
			};
		}
		catch (NoSuchMethodException noSuchMethodException) {
			throw new InternalError(noSuchMethodException);
		}
	}

	private static final Map
		<String, Function<LayoutPageTemplateCollection, Object>>
			_attributeGetterFunctions;
	private static final Map
		<String, BiConsumer<LayoutPageTemplateCollection, Object>>
			_attributeSetterBiConsumers;

	static {
		Map<String, Function<LayoutPageTemplateCollection, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String, Function<LayoutPageTemplateCollection, Object>>();
		Map<String, BiConsumer<LayoutPageTemplateCollection, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap
					<String, BiConsumer<LayoutPageTemplateCollection, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion", LayoutPageTemplateCollection::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<LayoutPageTemplateCollection, Long>)
				LayoutPageTemplateCollection::setMvccVersion);
		attributeGetterFunctions.put(
			"uuid", LayoutPageTemplateCollection::getUuid);
		attributeSetterBiConsumers.put(
			"uuid",
			(BiConsumer<LayoutPageTemplateCollection, String>)
				LayoutPageTemplateCollection::setUuid);
		attributeGetterFunctions.put(
			"layoutPageTemplateCollectionId",
			LayoutPageTemplateCollection::getLayoutPageTemplateCollectionId);
		attributeSetterBiConsumers.put(
			"layoutPageTemplateCollectionId",
			(BiConsumer<LayoutPageTemplateCollection, Long>)
				LayoutPageTemplateCollection::
					setLayoutPageTemplateCollectionId);
		attributeGetterFunctions.put(
			"groupId", LayoutPageTemplateCollection::getGroupId);
		attributeSetterBiConsumers.put(
			"groupId",
			(BiConsumer<LayoutPageTemplateCollection, Long>)
				LayoutPageTemplateCollection::setGroupId);
		attributeGetterFunctions.put(
			"companyId", LayoutPageTemplateCollection::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<LayoutPageTemplateCollection, Long>)
				LayoutPageTemplateCollection::setCompanyId);
		attributeGetterFunctions.put(
			"userId", LayoutPageTemplateCollection::getUserId);
		attributeSetterBiConsumers.put(
			"userId",
			(BiConsumer<LayoutPageTemplateCollection, Long>)
				LayoutPageTemplateCollection::setUserId);
		attributeGetterFunctions.put(
			"userName", LayoutPageTemplateCollection::getUserName);
		attributeSetterBiConsumers.put(
			"userName",
			(BiConsumer<LayoutPageTemplateCollection, String>)
				LayoutPageTemplateCollection::setUserName);
		attributeGetterFunctions.put(
			"createDate", LayoutPageTemplateCollection::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<LayoutPageTemplateCollection, Date>)
				LayoutPageTemplateCollection::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", LayoutPageTemplateCollection::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<LayoutPageTemplateCollection, Date>)
				LayoutPageTemplateCollection::setModifiedDate);
		attributeGetterFunctions.put(
			"layoutPageTemplateCollectionKey",
			LayoutPageTemplateCollection::getLayoutPageTemplateCollectionKey);
		attributeSetterBiConsumers.put(
			"layoutPageTemplateCollectionKey",
			(BiConsumer<LayoutPageTemplateCollection, String>)
				LayoutPageTemplateCollection::
					setLayoutPageTemplateCollectionKey);
		attributeGetterFunctions.put(
			"name", LayoutPageTemplateCollection::getName);
		attributeSetterBiConsumers.put(
			"name",
			(BiConsumer<LayoutPageTemplateCollection, String>)
				LayoutPageTemplateCollection::setName);
		attributeGetterFunctions.put(
			"description", LayoutPageTemplateCollection::getDescription);
		attributeSetterBiConsumers.put(
			"description",
			(BiConsumer<LayoutPageTemplateCollection, String>)
				LayoutPageTemplateCollection::setDescription);
		attributeGetterFunctions.put(
			"lastPublishDate",
			LayoutPageTemplateCollection::getLastPublishDate);
		attributeSetterBiConsumers.put(
			"lastPublishDate",
			(BiConsumer<LayoutPageTemplateCollection, Date>)
				LayoutPageTemplateCollection::setLastPublishDate);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public long getMvccVersion() {
		return _mvccVersion;
	}

	@Override
	public void setMvccVersion(long mvccVersion) {
		_mvccVersion = mvccVersion;
	}

	@JSON
	@Override
	public String getUuid() {
		if (_uuid == null) {
			return "";
		}
		else {
			return _uuid;
		}
	}

	@Override
	public void setUuid(String uuid) {
		_columnBitmask |= UUID_COLUMN_BITMASK;

		if (_originalUuid == null) {
			_originalUuid = _uuid;
		}

		_uuid = uuid;
	}

	public String getOriginalUuid() {
		return GetterUtil.getString(_originalUuid);
	}

	@JSON
	@Override
	public long getLayoutPageTemplateCollectionId() {
		return _layoutPageTemplateCollectionId;
	}

	@Override
	public void setLayoutPageTemplateCollectionId(
		long layoutPageTemplateCollectionId) {

		_layoutPageTemplateCollectionId = layoutPageTemplateCollectionId;
	}

	@JSON
	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_columnBitmask |= GROUPID_COLUMN_BITMASK;

		if (!_setOriginalGroupId) {
			_setOriginalGroupId = true;

			_originalGroupId = _groupId;
		}

		_groupId = groupId;
	}

	public long getOriginalGroupId() {
		return _originalGroupId;
	}

	@JSON
	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_columnBitmask |= COMPANYID_COLUMN_BITMASK;

		if (!_setOriginalCompanyId) {
			_setOriginalCompanyId = true;

			_originalCompanyId = _companyId;
		}

		_companyId = companyId;
	}

	public long getOriginalCompanyId() {
		return _originalCompanyId;
	}

	@JSON
	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException portalException) {
			return "";
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@JSON
	@Override
	public String getUserName() {
		if (_userName == null) {
			return "";
		}
		else {
			return _userName;
		}
	}

	@Override
	public void setUserName(String userName) {
		_userName = userName;
	}

	@JSON
	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	@JSON
	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public boolean hasSetModifiedDate() {
		return _setModifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_setModifiedDate = true;

		_modifiedDate = modifiedDate;
	}

	@JSON
	@Override
	public String getLayoutPageTemplateCollectionKey() {
		if (_layoutPageTemplateCollectionKey == null) {
			return "";
		}
		else {
			return _layoutPageTemplateCollectionKey;
		}
	}

	@Override
	public void setLayoutPageTemplateCollectionKey(
		String layoutPageTemplateCollectionKey) {

		_columnBitmask |= LAYOUTPAGETEMPLATECOLLECTIONKEY_COLUMN_BITMASK;

		if (_originalLayoutPageTemplateCollectionKey == null) {
			_originalLayoutPageTemplateCollectionKey =
				_layoutPageTemplateCollectionKey;
		}

		_layoutPageTemplateCollectionKey = layoutPageTemplateCollectionKey;
	}

	public String getOriginalLayoutPageTemplateCollectionKey() {
		return GetterUtil.getString(_originalLayoutPageTemplateCollectionKey);
	}

	@JSON
	@Override
	public String getName() {
		if (_name == null) {
			return "";
		}
		else {
			return _name;
		}
	}

	@Override
	public void setName(String name) {
		_columnBitmask = -1L;

		if (_originalName == null) {
			_originalName = _name;
		}

		_name = name;
	}

	public String getOriginalName() {
		return GetterUtil.getString(_originalName);
	}

	@JSON
	@Override
	public String getDescription() {
		if (_description == null) {
			return "";
		}
		else {
			return _description;
		}
	}

	@Override
	public void setDescription(String description) {
		_description = description;
	}

	@JSON
	@Override
	public Date getLastPublishDate() {
		return _lastPublishDate;
	}

	@Override
	public void setLastPublishDate(Date lastPublishDate) {
		_lastPublishDate = lastPublishDate;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(
			PortalUtil.getClassNameId(
				LayoutPageTemplateCollection.class.getName()));
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), LayoutPageTemplateCollection.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public LayoutPageTemplateCollection toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, LayoutPageTemplateCollection>
				escapedModelProxyProviderFunction =
					EscapedModelProxyProviderFunctionHolder.
						_escapedModelProxyProviderFunction;

			_escapedModel = escapedModelProxyProviderFunction.apply(
				new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		LayoutPageTemplateCollectionImpl layoutPageTemplateCollectionImpl =
			new LayoutPageTemplateCollectionImpl();

		layoutPageTemplateCollectionImpl.setMvccVersion(getMvccVersion());
		layoutPageTemplateCollectionImpl.setUuid(getUuid());
		layoutPageTemplateCollectionImpl.setLayoutPageTemplateCollectionId(
			getLayoutPageTemplateCollectionId());
		layoutPageTemplateCollectionImpl.setGroupId(getGroupId());
		layoutPageTemplateCollectionImpl.setCompanyId(getCompanyId());
		layoutPageTemplateCollectionImpl.setUserId(getUserId());
		layoutPageTemplateCollectionImpl.setUserName(getUserName());
		layoutPageTemplateCollectionImpl.setCreateDate(getCreateDate());
		layoutPageTemplateCollectionImpl.setModifiedDate(getModifiedDate());
		layoutPageTemplateCollectionImpl.setLayoutPageTemplateCollectionKey(
			getLayoutPageTemplateCollectionKey());
		layoutPageTemplateCollectionImpl.setName(getName());
		layoutPageTemplateCollectionImpl.setDescription(getDescription());
		layoutPageTemplateCollectionImpl.setLastPublishDate(
			getLastPublishDate());

		layoutPageTemplateCollectionImpl.resetOriginalValues();

		return layoutPageTemplateCollectionImpl;
	}

	@Override
	public int compareTo(
		LayoutPageTemplateCollection layoutPageTemplateCollection) {

		int value = 0;

		value = getName().compareTo(layoutPageTemplateCollection.getName());

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof LayoutPageTemplateCollection)) {
			return false;
		}

		LayoutPageTemplateCollection layoutPageTemplateCollection =
			(LayoutPageTemplateCollection)object;

		long primaryKey = layoutPageTemplateCollection.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public boolean isEntityCacheEnabled() {
		return true;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public boolean isFinderCacheEnabled() {
		return true;
	}

	@Override
	public void resetOriginalValues() {
		LayoutPageTemplateCollectionModelImpl
			layoutPageTemplateCollectionModelImpl = this;

		layoutPageTemplateCollectionModelImpl._originalUuid =
			layoutPageTemplateCollectionModelImpl._uuid;

		layoutPageTemplateCollectionModelImpl._originalGroupId =
			layoutPageTemplateCollectionModelImpl._groupId;

		layoutPageTemplateCollectionModelImpl._setOriginalGroupId = false;

		layoutPageTemplateCollectionModelImpl._originalCompanyId =
			layoutPageTemplateCollectionModelImpl._companyId;

		layoutPageTemplateCollectionModelImpl._setOriginalCompanyId = false;

		layoutPageTemplateCollectionModelImpl._setModifiedDate = false;

		layoutPageTemplateCollectionModelImpl.
			_originalLayoutPageTemplateCollectionKey =
				layoutPageTemplateCollectionModelImpl.
					_layoutPageTemplateCollectionKey;

		layoutPageTemplateCollectionModelImpl._originalName =
			layoutPageTemplateCollectionModelImpl._name;

		layoutPageTemplateCollectionModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<LayoutPageTemplateCollection> toCacheModel() {
		LayoutPageTemplateCollectionCacheModel
			layoutPageTemplateCollectionCacheModel =
				new LayoutPageTemplateCollectionCacheModel();

		layoutPageTemplateCollectionCacheModel.mvccVersion = getMvccVersion();

		layoutPageTemplateCollectionCacheModel.uuid = getUuid();

		String uuid = layoutPageTemplateCollectionCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			layoutPageTemplateCollectionCacheModel.uuid = null;
		}

		layoutPageTemplateCollectionCacheModel.layoutPageTemplateCollectionId =
			getLayoutPageTemplateCollectionId();

		layoutPageTemplateCollectionCacheModel.groupId = getGroupId();

		layoutPageTemplateCollectionCacheModel.companyId = getCompanyId();

		layoutPageTemplateCollectionCacheModel.userId = getUserId();

		layoutPageTemplateCollectionCacheModel.userName = getUserName();

		String userName = layoutPageTemplateCollectionCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			layoutPageTemplateCollectionCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			layoutPageTemplateCollectionCacheModel.createDate =
				createDate.getTime();
		}
		else {
			layoutPageTemplateCollectionCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			layoutPageTemplateCollectionCacheModel.modifiedDate =
				modifiedDate.getTime();
		}
		else {
			layoutPageTemplateCollectionCacheModel.modifiedDate =
				Long.MIN_VALUE;
		}

		layoutPageTemplateCollectionCacheModel.layoutPageTemplateCollectionKey =
			getLayoutPageTemplateCollectionKey();

		String layoutPageTemplateCollectionKey =
			layoutPageTemplateCollectionCacheModel.
				layoutPageTemplateCollectionKey;

		if ((layoutPageTemplateCollectionKey != null) &&
			(layoutPageTemplateCollectionKey.length() == 0)) {

			layoutPageTemplateCollectionCacheModel.
				layoutPageTemplateCollectionKey = null;
		}

		layoutPageTemplateCollectionCacheModel.name = getName();

		String name = layoutPageTemplateCollectionCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			layoutPageTemplateCollectionCacheModel.name = null;
		}

		layoutPageTemplateCollectionCacheModel.description = getDescription();

		String description = layoutPageTemplateCollectionCacheModel.description;

		if ((description != null) && (description.length() == 0)) {
			layoutPageTemplateCollectionCacheModel.description = null;
		}

		Date lastPublishDate = getLastPublishDate();

		if (lastPublishDate != null) {
			layoutPageTemplateCollectionCacheModel.lastPublishDate =
				lastPublishDate.getTime();
		}
		else {
			layoutPageTemplateCollectionCacheModel.lastPublishDate =
				Long.MIN_VALUE;
		}

		return layoutPageTemplateCollectionCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<LayoutPageTemplateCollection, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<LayoutPageTemplateCollection, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<LayoutPageTemplateCollection, Object>
				attributeGetterFunction = entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(
				attributeGetterFunction.apply(
					(LayoutPageTemplateCollection)this));
			sb.append(", ");
		}

		if (sb.index() > 1) {
			sb.setIndex(sb.index() - 1);
		}

		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		Map<String, Function<LayoutPageTemplateCollection, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<LayoutPageTemplateCollection, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<LayoutPageTemplateCollection, Object>
				attributeGetterFunction = entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(
				attributeGetterFunction.apply(
					(LayoutPageTemplateCollection)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function
			<InvocationHandler, LayoutPageTemplateCollection>
				_escapedModelProxyProviderFunction =
					_getProxyProviderFunction();

	}

	private long _mvccVersion;
	private String _uuid;
	private String _originalUuid;
	private long _layoutPageTemplateCollectionId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private String _layoutPageTemplateCollectionKey;
	private String _originalLayoutPageTemplateCollectionKey;
	private String _name;
	private String _originalName;
	private String _description;
	private Date _lastPublishDate;
	private long _columnBitmask;
	private LayoutPageTemplateCollection _escapedModel;

}
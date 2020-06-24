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

package com.liferay.dynamic.data.mapping.model.impl;

import com.liferay.dynamic.data.mapping.model.DDMFormInstanceVersion;
import com.liferay.dynamic.data.mapping.model.DDMFormInstanceVersionModel;
import com.liferay.dynamic.data.mapping.model.DDMFormInstanceVersionSoap;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.LocaleException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

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
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the DDMFormInstanceVersion service. Represents a row in the &quot;DDMFormInstanceVersion&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>DDMFormInstanceVersionModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link DDMFormInstanceVersionImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DDMFormInstanceVersionImpl
 * @generated
 */
@JSON(strict = true)
public class DDMFormInstanceVersionModelImpl
	extends BaseModelImpl<DDMFormInstanceVersion>
	implements DDMFormInstanceVersionModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a ddm form instance version model instance should use the <code>DDMFormInstanceVersion</code> interface instead.
	 */
	public static final String TABLE_NAME = "DDMFormInstanceVersion";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"ctCollectionId", Types.BIGINT},
		{"formInstanceVersionId", Types.BIGINT}, {"groupId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"userName", Types.VARCHAR}, {"createDate", Types.TIMESTAMP},
		{"formInstanceId", Types.BIGINT}, {"structureVersionId", Types.BIGINT},
		{"name", Types.VARCHAR}, {"description", Types.VARCHAR},
		{"settings_", Types.CLOB}, {"version", Types.VARCHAR},
		{"status", Types.INTEGER}, {"statusByUserId", Types.BIGINT},
		{"statusByUserName", Types.VARCHAR}, {"statusDate", Types.TIMESTAMP}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("ctCollectionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("formInstanceVersionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("formInstanceId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("structureVersionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("description", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("settings_", Types.CLOB);
		TABLE_COLUMNS_MAP.put("version", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("status", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("statusByUserId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("statusByUserName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("statusDate", Types.TIMESTAMP);
	}

	public static final String TABLE_SQL_CREATE =
		"create table DDMFormInstanceVersion (mvccVersion LONG default 0 not null,ctCollectionId LONG default 0 not null,formInstanceVersionId LONG not null,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,formInstanceId LONG,structureVersionId LONG,name STRING null,description STRING null,settings_ TEXT null,version VARCHAR(75) null,status INTEGER,statusByUserId LONG,statusByUserName VARCHAR(75) null,statusDate DATE null,primary key (formInstanceVersionId, ctCollectionId))";

	public static final String TABLE_SQL_DROP =
		"drop table DDMFormInstanceVersion";

	public static final String ORDER_BY_JPQL =
		" ORDER BY ddmFormInstanceVersion.formInstanceVersionId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY DDMFormInstanceVersion.formInstanceVersionId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final long FORMINSTANCEID_COLUMN_BITMASK = 1L;

	public static final long STATUS_COLUMN_BITMASK = 2L;

	public static final long VERSION_COLUMN_BITMASK = 4L;

	public static final long FORMINSTANCEVERSIONID_COLUMN_BITMASK = 8L;

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
	public static DDMFormInstanceVersion toModel(
		DDMFormInstanceVersionSoap soapModel) {

		if (soapModel == null) {
			return null;
		}

		DDMFormInstanceVersion model = new DDMFormInstanceVersionImpl();

		model.setMvccVersion(soapModel.getMvccVersion());
		model.setCtCollectionId(soapModel.getCtCollectionId());
		model.setFormInstanceVersionId(soapModel.getFormInstanceVersionId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setFormInstanceId(soapModel.getFormInstanceId());
		model.setStructureVersionId(soapModel.getStructureVersionId());
		model.setName(soapModel.getName());
		model.setDescription(soapModel.getDescription());
		model.setSettings(soapModel.getSettings());
		model.setVersion(soapModel.getVersion());
		model.setStatus(soapModel.getStatus());
		model.setStatusByUserId(soapModel.getStatusByUserId());
		model.setStatusByUserName(soapModel.getStatusByUserName());
		model.setStatusDate(soapModel.getStatusDate());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<DDMFormInstanceVersion> toModels(
		DDMFormInstanceVersionSoap[] soapModels) {

		if (soapModels == null) {
			return null;
		}

		List<DDMFormInstanceVersion> models =
			new ArrayList<DDMFormInstanceVersion>(soapModels.length);

		for (DDMFormInstanceVersionSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public DDMFormInstanceVersionModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _formInstanceVersionId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setFormInstanceVersionId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _formInstanceVersionId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return DDMFormInstanceVersion.class;
	}

	@Override
	public String getModelClassName() {
		return DDMFormInstanceVersion.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<DDMFormInstanceVersion, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<DDMFormInstanceVersion, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<DDMFormInstanceVersion, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((DDMFormInstanceVersion)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<DDMFormInstanceVersion, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<DDMFormInstanceVersion, Object>
				attributeSetterBiConsumer = attributeSetterBiConsumers.get(
					attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(DDMFormInstanceVersion)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<DDMFormInstanceVersion, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<DDMFormInstanceVersion, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, DDMFormInstanceVersion>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			DDMFormInstanceVersion.class.getClassLoader(),
			DDMFormInstanceVersion.class, ModelWrapper.class);

		try {
			Constructor<DDMFormInstanceVersion> constructor =
				(Constructor<DDMFormInstanceVersion>)proxyClass.getConstructor(
					InvocationHandler.class);

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

	private static final Map<String, Function<DDMFormInstanceVersion, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<DDMFormInstanceVersion, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<DDMFormInstanceVersion, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String, Function<DDMFormInstanceVersion, Object>>();
		Map<String, BiConsumer<DDMFormInstanceVersion, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap
					<String, BiConsumer<DDMFormInstanceVersion, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion", DDMFormInstanceVersion::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<DDMFormInstanceVersion, Long>)
				DDMFormInstanceVersion::setMvccVersion);
		attributeGetterFunctions.put(
			"ctCollectionId", DDMFormInstanceVersion::getCtCollectionId);
		attributeSetterBiConsumers.put(
			"ctCollectionId",
			(BiConsumer<DDMFormInstanceVersion, Long>)
				DDMFormInstanceVersion::setCtCollectionId);
		attributeGetterFunctions.put(
			"formInstanceVersionId",
			DDMFormInstanceVersion::getFormInstanceVersionId);
		attributeSetterBiConsumers.put(
			"formInstanceVersionId",
			(BiConsumer<DDMFormInstanceVersion, Long>)
				DDMFormInstanceVersion::setFormInstanceVersionId);
		attributeGetterFunctions.put(
			"groupId", DDMFormInstanceVersion::getGroupId);
		attributeSetterBiConsumers.put(
			"groupId",
			(BiConsumer<DDMFormInstanceVersion, Long>)
				DDMFormInstanceVersion::setGroupId);
		attributeGetterFunctions.put(
			"companyId", DDMFormInstanceVersion::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<DDMFormInstanceVersion, Long>)
				DDMFormInstanceVersion::setCompanyId);
		attributeGetterFunctions.put(
			"userId", DDMFormInstanceVersion::getUserId);
		attributeSetterBiConsumers.put(
			"userId",
			(BiConsumer<DDMFormInstanceVersion, Long>)
				DDMFormInstanceVersion::setUserId);
		attributeGetterFunctions.put(
			"userName", DDMFormInstanceVersion::getUserName);
		attributeSetterBiConsumers.put(
			"userName",
			(BiConsumer<DDMFormInstanceVersion, String>)
				DDMFormInstanceVersion::setUserName);
		attributeGetterFunctions.put(
			"createDate", DDMFormInstanceVersion::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<DDMFormInstanceVersion, Date>)
				DDMFormInstanceVersion::setCreateDate);
		attributeGetterFunctions.put(
			"formInstanceId", DDMFormInstanceVersion::getFormInstanceId);
		attributeSetterBiConsumers.put(
			"formInstanceId",
			(BiConsumer<DDMFormInstanceVersion, Long>)
				DDMFormInstanceVersion::setFormInstanceId);
		attributeGetterFunctions.put(
			"structureVersionId",
			DDMFormInstanceVersion::getStructureVersionId);
		attributeSetterBiConsumers.put(
			"structureVersionId",
			(BiConsumer<DDMFormInstanceVersion, Long>)
				DDMFormInstanceVersion::setStructureVersionId);
		attributeGetterFunctions.put("name", DDMFormInstanceVersion::getName);
		attributeSetterBiConsumers.put(
			"name",
			(BiConsumer<DDMFormInstanceVersion, String>)
				DDMFormInstanceVersion::setName);
		attributeGetterFunctions.put(
			"description", DDMFormInstanceVersion::getDescription);
		attributeSetterBiConsumers.put(
			"description",
			(BiConsumer<DDMFormInstanceVersion, String>)
				DDMFormInstanceVersion::setDescription);
		attributeGetterFunctions.put(
			"settings", DDMFormInstanceVersion::getSettings);
		attributeSetterBiConsumers.put(
			"settings",
			(BiConsumer<DDMFormInstanceVersion, String>)
				DDMFormInstanceVersion::setSettings);
		attributeGetterFunctions.put(
			"version", DDMFormInstanceVersion::getVersion);
		attributeSetterBiConsumers.put(
			"version",
			(BiConsumer<DDMFormInstanceVersion, String>)
				DDMFormInstanceVersion::setVersion);
		attributeGetterFunctions.put(
			"status", DDMFormInstanceVersion::getStatus);
		attributeSetterBiConsumers.put(
			"status",
			(BiConsumer<DDMFormInstanceVersion, Integer>)
				DDMFormInstanceVersion::setStatus);
		attributeGetterFunctions.put(
			"statusByUserId", DDMFormInstanceVersion::getStatusByUserId);
		attributeSetterBiConsumers.put(
			"statusByUserId",
			(BiConsumer<DDMFormInstanceVersion, Long>)
				DDMFormInstanceVersion::setStatusByUserId);
		attributeGetterFunctions.put(
			"statusByUserName", DDMFormInstanceVersion::getStatusByUserName);
		attributeSetterBiConsumers.put(
			"statusByUserName",
			(BiConsumer<DDMFormInstanceVersion, String>)
				DDMFormInstanceVersion::setStatusByUserName);
		attributeGetterFunctions.put(
			"statusDate", DDMFormInstanceVersion::getStatusDate);
		attributeSetterBiConsumers.put(
			"statusDate",
			(BiConsumer<DDMFormInstanceVersion, Date>)
				DDMFormInstanceVersion::setStatusDate);

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
	public long getCtCollectionId() {
		return _ctCollectionId;
	}

	@Override
	public void setCtCollectionId(long ctCollectionId) {
		_ctCollectionId = ctCollectionId;
	}

	@JSON
	@Override
	public long getFormInstanceVersionId() {
		return _formInstanceVersionId;
	}

	@Override
	public void setFormInstanceVersionId(long formInstanceVersionId) {
		_formInstanceVersionId = formInstanceVersionId;
	}

	@JSON
	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	@JSON
	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_companyId = companyId;
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
	public long getFormInstanceId() {
		return _formInstanceId;
	}

	@Override
	public void setFormInstanceId(long formInstanceId) {
		_columnBitmask |= FORMINSTANCEID_COLUMN_BITMASK;

		if (!_setOriginalFormInstanceId) {
			_setOriginalFormInstanceId = true;

			_originalFormInstanceId = _formInstanceId;
		}

		_formInstanceId = formInstanceId;
	}

	public long getOriginalFormInstanceId() {
		return _originalFormInstanceId;
	}

	@JSON
	@Override
	public long getStructureVersionId() {
		return _structureVersionId;
	}

	@Override
	public void setStructureVersionId(long structureVersionId) {
		_structureVersionId = structureVersionId;
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
	public String getName(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getName(languageId);
	}

	@Override
	public String getName(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getName(languageId, useDefault);
	}

	@Override
	public String getName(String languageId) {
		return LocalizationUtil.getLocalization(getName(), languageId);
	}

	@Override
	public String getName(String languageId, boolean useDefault) {
		return LocalizationUtil.getLocalization(
			getName(), languageId, useDefault);
	}

	@Override
	public String getNameCurrentLanguageId() {
		return _nameCurrentLanguageId;
	}

	@JSON
	@Override
	public String getNameCurrentValue() {
		Locale locale = getLocale(_nameCurrentLanguageId);

		return getName(locale);
	}

	@Override
	public Map<Locale, String> getNameMap() {
		return LocalizationUtil.getLocalizationMap(getName());
	}

	@Override
	public void setName(String name) {
		_name = name;
	}

	@Override
	public void setName(String name, Locale locale) {
		setName(name, locale, LocaleUtil.getDefault());
	}

	@Override
	public void setName(String name, Locale locale, Locale defaultLocale) {
		String languageId = LocaleUtil.toLanguageId(locale);
		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		if (Validator.isNotNull(name)) {
			setName(
				LocalizationUtil.updateLocalization(
					getName(), "Name", name, languageId, defaultLanguageId));
		}
		else {
			setName(
				LocalizationUtil.removeLocalization(
					getName(), "Name", languageId));
		}
	}

	@Override
	public void setNameCurrentLanguageId(String languageId) {
		_nameCurrentLanguageId = languageId;
	}

	@Override
	public void setNameMap(Map<Locale, String> nameMap) {
		setNameMap(nameMap, LocaleUtil.getDefault());
	}

	@Override
	public void setNameMap(Map<Locale, String> nameMap, Locale defaultLocale) {
		if (nameMap == null) {
			return;
		}

		setName(
			LocalizationUtil.updateLocalization(
				nameMap, getName(), "Name",
				LocaleUtil.toLanguageId(defaultLocale)));
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
	public String getDescription(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getDescription(languageId);
	}

	@Override
	public String getDescription(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getDescription(languageId, useDefault);
	}

	@Override
	public String getDescription(String languageId) {
		return LocalizationUtil.getLocalization(getDescription(), languageId);
	}

	@Override
	public String getDescription(String languageId, boolean useDefault) {
		return LocalizationUtil.getLocalization(
			getDescription(), languageId, useDefault);
	}

	@Override
	public String getDescriptionCurrentLanguageId() {
		return _descriptionCurrentLanguageId;
	}

	@JSON
	@Override
	public String getDescriptionCurrentValue() {
		Locale locale = getLocale(_descriptionCurrentLanguageId);

		return getDescription(locale);
	}

	@Override
	public Map<Locale, String> getDescriptionMap() {
		return LocalizationUtil.getLocalizationMap(getDescription());
	}

	@Override
	public void setDescription(String description) {
		_description = description;
	}

	@Override
	public void setDescription(String description, Locale locale) {
		setDescription(description, locale, LocaleUtil.getDefault());
	}

	@Override
	public void setDescription(
		String description, Locale locale, Locale defaultLocale) {

		String languageId = LocaleUtil.toLanguageId(locale);
		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		if (Validator.isNotNull(description)) {
			setDescription(
				LocalizationUtil.updateLocalization(
					getDescription(), "Description", description, languageId,
					defaultLanguageId));
		}
		else {
			setDescription(
				LocalizationUtil.removeLocalization(
					getDescription(), "Description", languageId));
		}
	}

	@Override
	public void setDescriptionCurrentLanguageId(String languageId) {
		_descriptionCurrentLanguageId = languageId;
	}

	@Override
	public void setDescriptionMap(Map<Locale, String> descriptionMap) {
		setDescriptionMap(descriptionMap, LocaleUtil.getDefault());
	}

	@Override
	public void setDescriptionMap(
		Map<Locale, String> descriptionMap, Locale defaultLocale) {

		if (descriptionMap == null) {
			return;
		}

		setDescription(
			LocalizationUtil.updateLocalization(
				descriptionMap, getDescription(), "Description",
				LocaleUtil.toLanguageId(defaultLocale)));
	}

	@JSON
	@Override
	public String getSettings() {
		if (_settings == null) {
			return "";
		}
		else {
			return _settings;
		}
	}

	@Override
	public void setSettings(String settings) {
		_settings = settings;
	}

	@JSON
	@Override
	public String getVersion() {
		if (_version == null) {
			return "";
		}
		else {
			return _version;
		}
	}

	@Override
	public void setVersion(String version) {
		_columnBitmask |= VERSION_COLUMN_BITMASK;

		if (_originalVersion == null) {
			_originalVersion = _version;
		}

		_version = version;
	}

	public String getOriginalVersion() {
		return GetterUtil.getString(_originalVersion);
	}

	@JSON
	@Override
	public int getStatus() {
		return _status;
	}

	@Override
	public void setStatus(int status) {
		_columnBitmask |= STATUS_COLUMN_BITMASK;

		if (!_setOriginalStatus) {
			_setOriginalStatus = true;

			_originalStatus = _status;
		}

		_status = status;
	}

	public int getOriginalStatus() {
		return _originalStatus;
	}

	@JSON
	@Override
	public long getStatusByUserId() {
		return _statusByUserId;
	}

	@Override
	public void setStatusByUserId(long statusByUserId) {
		_statusByUserId = statusByUserId;
	}

	@Override
	public String getStatusByUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getStatusByUserId());

			return user.getUuid();
		}
		catch (PortalException portalException) {
			return "";
		}
	}

	@Override
	public void setStatusByUserUuid(String statusByUserUuid) {
	}

	@JSON
	@Override
	public String getStatusByUserName() {
		if (_statusByUserName == null) {
			return "";
		}
		else {
			return _statusByUserName;
		}
	}

	@Override
	public void setStatusByUserName(String statusByUserName) {
		_statusByUserName = statusByUserName;
	}

	@JSON
	@Override
	public Date getStatusDate() {
		return _statusDate;
	}

	@Override
	public void setStatusDate(Date statusDate) {
		_statusDate = statusDate;
	}

	@Override
	public boolean isApproved() {
		if (getStatus() == WorkflowConstants.STATUS_APPROVED) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isDenied() {
		if (getStatus() == WorkflowConstants.STATUS_DENIED) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isDraft() {
		if (getStatus() == WorkflowConstants.STATUS_DRAFT) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isExpired() {
		if (getStatus() == WorkflowConstants.STATUS_EXPIRED) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isInactive() {
		if (getStatus() == WorkflowConstants.STATUS_INACTIVE) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isIncomplete() {
		if (getStatus() == WorkflowConstants.STATUS_INCOMPLETE) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isPending() {
		if (getStatus() == WorkflowConstants.STATUS_PENDING) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isScheduled() {
		if (getStatus() == WorkflowConstants.STATUS_SCHEDULED) {
			return true;
		}
		else {
			return false;
		}
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), DDMFormInstanceVersion.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public String[] getAvailableLanguageIds() {
		Set<String> availableLanguageIds = new TreeSet<String>();

		Map<Locale, String> nameMap = getNameMap();

		for (Map.Entry<Locale, String> entry : nameMap.entrySet()) {
			Locale locale = entry.getKey();
			String value = entry.getValue();

			if (Validator.isNotNull(value)) {
				availableLanguageIds.add(LocaleUtil.toLanguageId(locale));
			}
		}

		Map<Locale, String> descriptionMap = getDescriptionMap();

		for (Map.Entry<Locale, String> entry : descriptionMap.entrySet()) {
			Locale locale = entry.getKey();
			String value = entry.getValue();

			if (Validator.isNotNull(value)) {
				availableLanguageIds.add(LocaleUtil.toLanguageId(locale));
			}
		}

		return availableLanguageIds.toArray(
			new String[availableLanguageIds.size()]);
	}

	@Override
	public String getDefaultLanguageId() {
		String xml = getName();

		if (xml == null) {
			return "";
		}

		Locale defaultLocale = LocaleUtil.getDefault();

		return LocalizationUtil.getDefaultLanguageId(xml, defaultLocale);
	}

	@Override
	public void prepareLocalizedFieldsForImport() throws LocaleException {
		Locale defaultLocale = LocaleUtil.fromLanguageId(
			getDefaultLanguageId());

		Locale[] availableLocales = LocaleUtil.fromLanguageIds(
			getAvailableLanguageIds());

		Locale defaultImportLocale = LocalizationUtil.getDefaultImportLocale(
			DDMFormInstanceVersion.class.getName(), getPrimaryKey(),
			defaultLocale, availableLocales);

		prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	@SuppressWarnings("unused")
	public void prepareLocalizedFieldsForImport(Locale defaultImportLocale)
		throws LocaleException {

		Locale defaultLocale = LocaleUtil.getDefault();

		String modelDefaultLanguageId = getDefaultLanguageId();

		String name = getName(defaultLocale);

		if (Validator.isNull(name)) {
			setName(getName(modelDefaultLanguageId), defaultLocale);
		}
		else {
			setName(getName(defaultLocale), defaultLocale, defaultLocale);
		}

		String description = getDescription(defaultLocale);

		if (Validator.isNull(description)) {
			setDescription(
				getDescription(modelDefaultLanguageId), defaultLocale);
		}
		else {
			setDescription(
				getDescription(defaultLocale), defaultLocale, defaultLocale);
		}
	}

	@Override
	public DDMFormInstanceVersion toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, DDMFormInstanceVersion>
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
		DDMFormInstanceVersionImpl ddmFormInstanceVersionImpl =
			new DDMFormInstanceVersionImpl();

		ddmFormInstanceVersionImpl.setMvccVersion(getMvccVersion());
		ddmFormInstanceVersionImpl.setCtCollectionId(getCtCollectionId());
		ddmFormInstanceVersionImpl.setFormInstanceVersionId(
			getFormInstanceVersionId());
		ddmFormInstanceVersionImpl.setGroupId(getGroupId());
		ddmFormInstanceVersionImpl.setCompanyId(getCompanyId());
		ddmFormInstanceVersionImpl.setUserId(getUserId());
		ddmFormInstanceVersionImpl.setUserName(getUserName());
		ddmFormInstanceVersionImpl.setCreateDate(getCreateDate());
		ddmFormInstanceVersionImpl.setFormInstanceId(getFormInstanceId());
		ddmFormInstanceVersionImpl.setStructureVersionId(
			getStructureVersionId());
		ddmFormInstanceVersionImpl.setName(getName());
		ddmFormInstanceVersionImpl.setDescription(getDescription());
		ddmFormInstanceVersionImpl.setSettings(getSettings());
		ddmFormInstanceVersionImpl.setVersion(getVersion());
		ddmFormInstanceVersionImpl.setStatus(getStatus());
		ddmFormInstanceVersionImpl.setStatusByUserId(getStatusByUserId());
		ddmFormInstanceVersionImpl.setStatusByUserName(getStatusByUserName());
		ddmFormInstanceVersionImpl.setStatusDate(getStatusDate());

		ddmFormInstanceVersionImpl.resetOriginalValues();

		return ddmFormInstanceVersionImpl;
	}

	@Override
	public int compareTo(DDMFormInstanceVersion ddmFormInstanceVersion) {
		long primaryKey = ddmFormInstanceVersion.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof DDMFormInstanceVersion)) {
			return false;
		}

		DDMFormInstanceVersion ddmFormInstanceVersion =
			(DDMFormInstanceVersion)object;

		long primaryKey = ddmFormInstanceVersion.getPrimaryKey();

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
		DDMFormInstanceVersionModelImpl ddmFormInstanceVersionModelImpl = this;

		ddmFormInstanceVersionModelImpl._originalFormInstanceId =
			ddmFormInstanceVersionModelImpl._formInstanceId;

		ddmFormInstanceVersionModelImpl._setOriginalFormInstanceId = false;

		ddmFormInstanceVersionModelImpl._originalVersion =
			ddmFormInstanceVersionModelImpl._version;

		ddmFormInstanceVersionModelImpl._originalStatus =
			ddmFormInstanceVersionModelImpl._status;

		ddmFormInstanceVersionModelImpl._setOriginalStatus = false;

		ddmFormInstanceVersionModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<DDMFormInstanceVersion> toCacheModel() {
		DDMFormInstanceVersionCacheModel ddmFormInstanceVersionCacheModel =
			new DDMFormInstanceVersionCacheModel();

		ddmFormInstanceVersionCacheModel.mvccVersion = getMvccVersion();

		ddmFormInstanceVersionCacheModel.ctCollectionId = getCtCollectionId();

		ddmFormInstanceVersionCacheModel.formInstanceVersionId =
			getFormInstanceVersionId();

		ddmFormInstanceVersionCacheModel.groupId = getGroupId();

		ddmFormInstanceVersionCacheModel.companyId = getCompanyId();

		ddmFormInstanceVersionCacheModel.userId = getUserId();

		ddmFormInstanceVersionCacheModel.userName = getUserName();

		String userName = ddmFormInstanceVersionCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			ddmFormInstanceVersionCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			ddmFormInstanceVersionCacheModel.createDate = createDate.getTime();
		}
		else {
			ddmFormInstanceVersionCacheModel.createDate = Long.MIN_VALUE;
		}

		ddmFormInstanceVersionCacheModel.formInstanceId = getFormInstanceId();

		ddmFormInstanceVersionCacheModel.structureVersionId =
			getStructureVersionId();

		ddmFormInstanceVersionCacheModel.name = getName();

		String name = ddmFormInstanceVersionCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			ddmFormInstanceVersionCacheModel.name = null;
		}

		ddmFormInstanceVersionCacheModel.description = getDescription();

		String description = ddmFormInstanceVersionCacheModel.description;

		if ((description != null) && (description.length() == 0)) {
			ddmFormInstanceVersionCacheModel.description = null;
		}

		ddmFormInstanceVersionCacheModel.settings = getSettings();

		String settings = ddmFormInstanceVersionCacheModel.settings;

		if ((settings != null) && (settings.length() == 0)) {
			ddmFormInstanceVersionCacheModel.settings = null;
		}

		ddmFormInstanceVersionCacheModel.version = getVersion();

		String version = ddmFormInstanceVersionCacheModel.version;

		if ((version != null) && (version.length() == 0)) {
			ddmFormInstanceVersionCacheModel.version = null;
		}

		ddmFormInstanceVersionCacheModel.status = getStatus();

		ddmFormInstanceVersionCacheModel.statusByUserId = getStatusByUserId();

		ddmFormInstanceVersionCacheModel.statusByUserName =
			getStatusByUserName();

		String statusByUserName =
			ddmFormInstanceVersionCacheModel.statusByUserName;

		if ((statusByUserName != null) && (statusByUserName.length() == 0)) {
			ddmFormInstanceVersionCacheModel.statusByUserName = null;
		}

		Date statusDate = getStatusDate();

		if (statusDate != null) {
			ddmFormInstanceVersionCacheModel.statusDate = statusDate.getTime();
		}
		else {
			ddmFormInstanceVersionCacheModel.statusDate = Long.MIN_VALUE;
		}

		return ddmFormInstanceVersionCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<DDMFormInstanceVersion, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<DDMFormInstanceVersion, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<DDMFormInstanceVersion, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(
				attributeGetterFunction.apply((DDMFormInstanceVersion)this));
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
		Map<String, Function<DDMFormInstanceVersion, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<DDMFormInstanceVersion, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<DDMFormInstanceVersion, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(
				attributeGetterFunction.apply((DDMFormInstanceVersion)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, DDMFormInstanceVersion>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _mvccVersion;
	private long _ctCollectionId;
	private long _formInstanceVersionId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private long _formInstanceId;
	private long _originalFormInstanceId;
	private boolean _setOriginalFormInstanceId;
	private long _structureVersionId;
	private String _name;
	private String _nameCurrentLanguageId;
	private String _description;
	private String _descriptionCurrentLanguageId;
	private String _settings;
	private String _version;
	private String _originalVersion;
	private int _status;
	private int _originalStatus;
	private boolean _setOriginalStatus;
	private long _statusByUserId;
	private String _statusByUserName;
	private Date _statusDate;
	private long _columnBitmask;
	private DDMFormInstanceVersion _escapedModel;

}
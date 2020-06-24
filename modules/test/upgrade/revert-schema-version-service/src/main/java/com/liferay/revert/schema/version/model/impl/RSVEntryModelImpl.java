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

package com.liferay.revert.schema.version.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.revert.schema.version.model.RSVEntry;
import com.liferay.revert.schema.version.model.RSVEntryModel;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Types;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the RSVEntry service. Represents a row in the &quot;RSVEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>RSVEntryModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link RSVEntryImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RSVEntryImpl
 * @generated
 */
public class RSVEntryModelImpl
	extends BaseModelImpl<RSVEntry> implements RSVEntryModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a rsv entry model instance should use the <code>RSVEntry</code> interface instead.
	 */
	public static final String TABLE_NAME = "RSVEntry";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"rsvEntryId", Types.BIGINT},
		{"companyId", Types.BIGINT}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("rsvEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE =
		"create table RSVEntry (mvccVersion LONG default 0 not null,rsvEntryId LONG not null primary key,companyId LONG)";

	public static final String TABLE_SQL_DROP = "drop table RSVEntry";

	public static final String ORDER_BY_JPQL =
		" ORDER BY rsvEntry.rsvEntryId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY RSVEntry.rsvEntryId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

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

	public RSVEntryModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _rsvEntryId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setRsvEntryId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _rsvEntryId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return RSVEntry.class;
	}

	@Override
	public String getModelClassName() {
		return RSVEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<RSVEntry, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<RSVEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<RSVEntry, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName, attributeGetterFunction.apply((RSVEntry)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<RSVEntry, Object>> attributeSetterBiConsumers =
			getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<RSVEntry, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(RSVEntry)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<RSVEntry, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<RSVEntry, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, RSVEntry>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			RSVEntry.class.getClassLoader(), RSVEntry.class,
			ModelWrapper.class);

		try {
			Constructor<RSVEntry> constructor =
				(Constructor<RSVEntry>)proxyClass.getConstructor(
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

	private static final Map<String, Function<RSVEntry, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<RSVEntry, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<RSVEntry, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<RSVEntry, Object>>();
		Map<String, BiConsumer<RSVEntry, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<RSVEntry, ?>>();

		attributeGetterFunctions.put("mvccVersion", RSVEntry::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<RSVEntry, Long>)RSVEntry::setMvccVersion);
		attributeGetterFunctions.put("rsvEntryId", RSVEntry::getRsvEntryId);
		attributeSetterBiConsumers.put(
			"rsvEntryId", (BiConsumer<RSVEntry, Long>)RSVEntry::setRsvEntryId);
		attributeGetterFunctions.put("companyId", RSVEntry::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId", (BiConsumer<RSVEntry, Long>)RSVEntry::setCompanyId);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@Override
	public long getMvccVersion() {
		return _mvccVersion;
	}

	@Override
	public void setMvccVersion(long mvccVersion) {
		_mvccVersion = mvccVersion;
	}

	@Override
	public long getRsvEntryId() {
		return _rsvEntryId;
	}

	@Override
	public void setRsvEntryId(long rsvEntryId) {
		_rsvEntryId = rsvEntryId;
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), RSVEntry.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public RSVEntry toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, RSVEntry>
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
		RSVEntryImpl rsvEntryImpl = new RSVEntryImpl();

		rsvEntryImpl.setMvccVersion(getMvccVersion());
		rsvEntryImpl.setRsvEntryId(getRsvEntryId());
		rsvEntryImpl.setCompanyId(getCompanyId());

		rsvEntryImpl.resetOriginalValues();

		return rsvEntryImpl;
	}

	@Override
	public int compareTo(RSVEntry rsvEntry) {
		long primaryKey = rsvEntry.getPrimaryKey();

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

		if (!(object instanceof RSVEntry)) {
			return false;
		}

		RSVEntry rsvEntry = (RSVEntry)object;

		long primaryKey = rsvEntry.getPrimaryKey();

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
	}

	@Override
	public CacheModel<RSVEntry> toCacheModel() {
		RSVEntryCacheModel rsvEntryCacheModel = new RSVEntryCacheModel();

		rsvEntryCacheModel.mvccVersion = getMvccVersion();

		rsvEntryCacheModel.rsvEntryId = getRsvEntryId();

		rsvEntryCacheModel.companyId = getCompanyId();

		return rsvEntryCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<RSVEntry, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<RSVEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<RSVEntry, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((RSVEntry)this));
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
		Map<String, Function<RSVEntry, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<RSVEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<RSVEntry, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((RSVEntry)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, RSVEntry>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _mvccVersion;
	private long _rsvEntryId;
	private long _companyId;
	private RSVEntry _escapedModel;

}
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

package com.liferay.object.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.StagedAuditedModel;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the ObjectState service. Represents a row in the &quot;ObjectState&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.object.model.impl.ObjectStateModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.object.model.impl.ObjectStateImpl</code>.
 * </p>
 *
 * @author Marco Leo
 * @see ObjectState
 * @generated
 */
@ProviderType
public interface ObjectStateModel
	extends BaseModel<ObjectState>, MVCCModel, ShardedModel,
			StagedAuditedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a object state model instance should use the {@link ObjectState} interface instead.
	 */

	/**
	 * Returns the primary key of this object state.
	 *
	 * @return the primary key of this object state
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this object state.
	 *
	 * @param primaryKey the primary key of this object state
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this object state.
	 *
	 * @return the mvcc version of this object state
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this object state.
	 *
	 * @param mvccVersion the mvcc version of this object state
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the uuid of this object state.
	 *
	 * @return the uuid of this object state
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this object state.
	 *
	 * @param uuid the uuid of this object state
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the object state ID of this object state.
	 *
	 * @return the object state ID of this object state
	 */
	public long getObjectStateId();

	/**
	 * Sets the object state ID of this object state.
	 *
	 * @param objectStateId the object state ID of this object state
	 */
	public void setObjectStateId(long objectStateId);

	/**
	 * Returns the company ID of this object state.
	 *
	 * @return the company ID of this object state
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this object state.
	 *
	 * @param companyId the company ID of this object state
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this object state.
	 *
	 * @return the user ID of this object state
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this object state.
	 *
	 * @param userId the user ID of this object state
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this object state.
	 *
	 * @return the user uuid of this object state
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this object state.
	 *
	 * @param userUuid the user uuid of this object state
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this object state.
	 *
	 * @return the user name of this object state
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this object state.
	 *
	 * @param userName the user name of this object state
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this object state.
	 *
	 * @return the create date of this object state
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this object state.
	 *
	 * @param createDate the create date of this object state
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this object state.
	 *
	 * @return the modified date of this object state
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this object state.
	 *
	 * @param modifiedDate the modified date of this object state
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the list type entry ID of this object state.
	 *
	 * @return the list type entry ID of this object state
	 */
	public long getListTypeEntryId();

	/**
	 * Sets the list type entry ID of this object state.
	 *
	 * @param listTypeEntryId the list type entry ID of this object state
	 */
	public void setListTypeEntryId(long listTypeEntryId);

	/**
	 * Returns the object state flow ID of this object state.
	 *
	 * @return the object state flow ID of this object state
	 */
	public long getObjectStateFlowId();

	/**
	 * Sets the object state flow ID of this object state.
	 *
	 * @param objectStateFlowId the object state flow ID of this object state
	 */
	public void setObjectStateFlowId(long objectStateFlowId);

	@Override
	public ObjectState cloneWithOriginalValues();

}
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

package com.liferay.portal.kernel.poller;

import com.liferay.petra.string.StringBundler;

import java.io.Serializable;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @author Brian Wing Shun Chan
 */
public class PollerRequest implements Serializable {

	public PollerRequest(
		PollerHeader pollerHeader, String portletId,
		Map<String, String> parameterMap, String chunkId,
		boolean receiveRequest) {

		_pollerHeader = pollerHeader;
		_portletId = portletId;
		_parameterMap = parameterMap;
		_chunkId = chunkId;
		_receiveRequest = receiveRequest;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof PollerRequest)) {
			return false;
		}

		PollerRequest portletRequest = (PollerRequest)object;

		if (Objects.equals(_portletId, portletRequest._portletId)) {
			return true;
		}

		return false;
	}

	public long getBrowserKey() {
		return _pollerHeader.getBrowserKey();
	}

	public String getChunkId() {
		return _chunkId;
	}

	public long getCompanyId() {
		return _pollerHeader.getCompanyId();
	}

	public Map<String, String> getParameterMap() {
		return _parameterMap;
	}

	public PollerHeader getPollerHeader() {
		return _pollerHeader;
	}

	public String getPortletId() {
		return _portletId;
	}

	public Set<String> getPortletIds() {
		Map<String, Boolean> portletIdsMap = _pollerHeader.getPortletIdsMap();

		return portletIdsMap.keySet();
	}

	public long getTimestamp() {
		return _pollerHeader.getTimestamp();
	}

	public long getUserId() {
		return _pollerHeader.getUserId();
	}

	@Override
	public int hashCode() {
		if (_portletId != null) {
			return _portletId.hashCode();
		}

		return 0;
	}

	public boolean isInitialRequest() {
		Map<String, Boolean> portletIdsMap = _pollerHeader.getPortletIdsMap();

		return portletIdsMap.get(_portletId);
	}

	public boolean isReceiveRequest() {
		return _receiveRequest;
	}

	public boolean isStartPolling() {
		return _pollerHeader.isStartPolling();
	}

	@Override
	public String toString() {
		return StringBundler.concat(
			"{chunkId=", _chunkId, ", parameterMap=", _parameterMap,
			", pollerHeader=", _pollerHeader, ", portletId=", _portletId,
			", receiveRequest=", _receiveRequest, "}");
	}

	private final String _chunkId;
	private final Map<String, String> _parameterMap;
	private final PollerHeader _pollerHeader;
	private final String _portletId;
	private final boolean _receiveRequest;

}
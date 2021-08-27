<%--
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
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ page import="com.liferay.portal.cluster.multiple.sample.web.internal.ClusterSampleData" %>

<portlet:defineObjects />

<%
ClusterSampleData localData = new ClusterSampleData();
%>

<h4>Server Data:</h4>

<p>Following data is from the server that generated this response:</p>

<ul>
	<li>
		<b>Computer Name:</b> <%= localData.getComputerName() %>
	</li>
	<li>
		<b>Liferay Home:</b> <%= localData.getLiferayHome() %>
	</li>
	<li>
		<b>Current timestamp:</b> <%= localData.getTimestamp() %>
	</li>
</ul>

<h4>Session Data:</h4>

<%
ClusterSampleData sessionData = (ClusterSampleData)portletSession.getAttribute("data");
%>

<c:choose>
	<c:when test="<%= sessionData != null %>">
		<p>Following data is stored in the portlet session:</p>

		<ul>
			<li>
				<b>Stored data:</b> <%= sessionData.getData() %>
			</li>
			<li>
				<b>Stored timestamp:</b> <%= sessionData.getTimestamp() %>
			</li>
		</ul>

		<p>The data was stored by:</p>

		<ul>
			<li>
				<b>Computer Name:</b> <%= sessionData.getComputerName() %>
			</li>
			<li>
				<b>Liferay Home:</b> <%= sessionData.getLiferayHome() %>
			</li>
		</ul>
	</c:when>
	<c:otherwise>

		<%
		portletSession.setAttribute("data", localData);
		%>

		<p>No session data exists, generating a new one with random string: <i><%= localData.getData() %></i></p>
	</c:otherwise>
</c:choose>
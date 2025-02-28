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

package com.liferay.user.groups.admin.web.internal.portlet;

import com.liferay.portal.kernel.exception.DuplicateUserGroupException;
import com.liferay.portal.kernel.exception.NoSuchUserGroupException;
import com.liferay.portal.kernel.exception.RequiredUserGroupException;
import com.liferay.portal.kernel.exception.UserGroupNameException;
import com.liferay.portal.kernel.model.UserGroup;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.membershippolicy.MembershipPolicyException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.service.UserGroupService;
import com.liferay.portal.kernel.service.UserService;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.sites.kernel.util.Sites;
import com.liferay.user.groups.admin.constants.UserGroupsAdminPortletKeys;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Charles May
 * @author Drew Brokke
 */
@Component(
	property = {
		"com.liferay.portlet.css-class-wrapper=portlet-users-admin",
		"com.liferay.portlet.display-category=category.hidden",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.icon=/icons/user_groups_admin.png",
		"com.liferay.portlet.private-request-attributes=false",
		"com.liferay.portlet.private-session-attributes=false",
		"com.liferay.portlet.render-weight=50",
		"com.liferay.portlet.use-default-template=true",
		"javax.portlet.display-name=User Groups Admin",
		"javax.portlet.expiration-cache=0",
		"javax.portlet.init-param.template-path=/META-INF/resources/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + UserGroupsAdminPortletKeys.USER_GROUPS_ADMIN,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=administrator",
		"javax.portlet.version=3.0"
	},
	service = Portlet.class
)
public class UserGroupsAdminPortlet extends MVCPortlet {

	public void deleteUserGroups(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long[] deleteUserGroupIds = StringUtil.split(
			ParamUtil.getString(actionRequest, "deleteUserGroupIds"), 0L);

		for (long deleteUserGroupId : deleteUserGroupIds) {
			_userGroupService.deleteUserGroup(deleteUserGroupId);
		}
	}

	public void editUserGroup(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long userGroupId = ParamUtil.getLong(actionRequest, "userGroupId");

		String name = ParamUtil.getString(actionRequest, "name");
		String description = ParamUtil.getString(actionRequest, "description");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			UserGroup.class.getName(), actionRequest);

		UserGroup userGroup = null;

		if (userGroupId <= 0) {

			// Add user group

			userGroup = _userGroupService.addUserGroup(
				name, description, serviceContext);
		}
		else {

			// Update user group

			userGroup = _userGroupService.updateUserGroup(
				userGroupId, name, description, serviceContext);
		}

		// Layout set prototypes

		long publicLayoutSetPrototypeId = ParamUtil.getLong(
			actionRequest, "publicLayoutSetPrototypeId");
		long privateLayoutSetPrototypeId = ParamUtil.getLong(
			actionRequest, "privateLayoutSetPrototypeId");

		if ((privateLayoutSetPrototypeId > 0) ||
			(publicLayoutSetPrototypeId > 0)) {

			boolean publicLayoutSetPrototypeLinkEnabled = ParamUtil.getBoolean(
				actionRequest, "publicLayoutSetPrototypeLinkEnabled");
			boolean privateLayoutSetPrototypeLinkEnabled = ParamUtil.getBoolean(
				actionRequest, "privateLayoutSetPrototypeLinkEnabled");

			_sites.updateLayoutSetPrototypesLinks(
				userGroup.getGroup(), publicLayoutSetPrototypeId,
				privateLayoutSetPrototypeId,
				publicLayoutSetPrototypeLinkEnabled,
				privateLayoutSetPrototypeLinkEnabled);
		}
	}

	public void editUserGroupAssignments(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long userGroupId = ParamUtil.getLong(actionRequest, "userGroupId");

		long[] addUserIds = StringUtil.split(
			ParamUtil.getString(actionRequest, "addUserIds"), 0L);
		long[] removeUserIds = StringUtil.split(
			ParamUtil.getString(actionRequest, "removeUserIds"), 0L);

		_userService.addUserGroupUsers(userGroupId, addUserIds);
		_userService.unsetUserGroupUsers(userGroupId, removeUserIds);
	}

	@Override
	protected void doDispatch(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		if (SessionErrors.contains(
				renderRequest, NoSuchUserGroupException.class.getName()) ||
			SessionErrors.contains(
				renderRequest, PrincipalException.getNestedClasses())) {

			include("/error.jsp", renderRequest, renderResponse);
		}
		else if (SessionErrors.contains(
					renderRequest,
					RequiredUserGroupException.class.getName())) {

			include("/view.jsp", renderRequest, renderResponse);
		}
		else if (SessionErrors.contains(
					renderRequest,
					DuplicateUserGroupException.class.getName()) ||
				 SessionErrors.contains(
					 renderRequest, UserGroupNameException.class.getName())) {

			include("/edit_user_group.jsp", renderRequest, renderResponse);
		}
		else {
			super.doDispatch(renderRequest, renderResponse);
		}
	}

	@Override
	protected boolean isSessionErrorException(Throwable throwable) {
		if (throwable instanceof DuplicateUserGroupException ||
			throwable instanceof MembershipPolicyException ||
			throwable instanceof NoSuchUserGroupException ||
			throwable instanceof PrincipalException ||
			throwable instanceof RequiredUserGroupException ||
			throwable instanceof UserGroupNameException) {

			return true;
		}

		return false;
	}

	@Reference
	private Sites _sites;

	@Reference
	private UserGroupService _userGroupService;

	@Reference
	private UserService _userService;

}
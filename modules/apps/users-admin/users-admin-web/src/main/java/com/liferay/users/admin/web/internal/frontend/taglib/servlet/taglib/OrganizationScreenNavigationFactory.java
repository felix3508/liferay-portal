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

package com.liferay.users.admin.web.internal.frontend.taglib.servlet.taglib;

import com.liferay.frontend.taglib.servlet.taglib.ScreenNavigationCategory;
import com.liferay.frontend.taglib.servlet.taglib.ScreenNavigationEntry;
import com.liferay.frontend.taglib.servlet.taglib.util.JSPRenderer;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.OrganizationService;
import com.liferay.portal.kernel.util.Portal;

import java.util.function.BiFunction;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Drew Brokke
 */
@Component(
	immediate = true, service = OrganizationScreenNavigationFactory.class
)
public class OrganizationScreenNavigationFactory {

	public ScreenNavigationCategory createScreenNavigationCategory(
		String categoryKey) {

		return new OrganizationScreenNavigationCategory(categoryKey);
	}

	public ScreenNavigationEntry<Organization> createScreenNavigationEntry(
		String entryKey, String categoryKey, String jspPath,
		String mvcActionCommandName) {

		return createScreenNavigationEntry(
			entryKey, categoryKey, jspPath, mvcActionCommandName,
			(user, organization) -> true);
	}

	public ScreenNavigationEntry<Organization> createScreenNavigationEntry(
		String entryKey, String categoryKey, String jspPath,
		String mvcActionCommandName,
		BiFunction<User, Organization, Boolean> isVisiblePredicate) {

		return new OrganizationScreenNavigationEntry(
			_jspRenderer, _organizationService, _portal, entryKey, categoryKey,
			jspPath, mvcActionCommandName, isVisiblePredicate);
	}

	public ScreenNavigationEntry<Organization>
		createUpdateOnlyScreenNavigationEntry(
			String entryKey, String categoryKey, String jspPath,
			String mvcActionCommandName) {

		return createScreenNavigationEntry(
			entryKey, categoryKey, jspPath, mvcActionCommandName,
			(user, organization) -> {
				if (organization != null) {
					return true;
				}

				return false;
			});
	}

	@Reference
	private JSPRenderer _jspRenderer;

	@Reference
	private OrganizationService _organizationService;

	@Reference
	private Portal _portal;

}
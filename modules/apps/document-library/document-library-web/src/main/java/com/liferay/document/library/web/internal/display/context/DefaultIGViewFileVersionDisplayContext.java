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

package com.liferay.document.library.web.internal.display.context;

import com.liferay.document.library.display.context.IGViewFileVersionDisplayContext;
import com.liferay.document.library.kernel.versioning.VersioningStrategy;
import com.liferay.document.library.util.DLURLHelper;
import com.liferay.document.library.web.internal.display.context.helper.DLPortletInstanceSettingsHelper;
import com.liferay.document.library.web.internal.display.context.helper.IGRequestHelper;
import com.liferay.document.library.web.internal.display.context.logic.UIItemsBuilder;
import com.liferay.document.library.web.internal.helper.DLTrashHelper;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItemListBuilder;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.repository.model.FileShortcut;
import com.liferay.portal.kernel.repository.model.FileVersion;

import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Adolfo Pérez
 */
public class DefaultIGViewFileVersionDisplayContext
	implements IGViewFileVersionDisplayContext {

	public DefaultIGViewFileVersionDisplayContext(
			DLTrashHelper dlTrashHelper, DLURLHelper dlURLHelper,
			FileShortcut fileShortcut, FileVersion fileVersion,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse,
			ResourceBundle resourceBundle,
			VersioningStrategy versioningStrategy)
		throws PortalException {

		_igRequestHelper = new IGRequestHelper(httpServletRequest);

		_dlPortletInstanceSettingsHelper = new DLPortletInstanceSettingsHelper(
			_igRequestHelper);

		if (fileShortcut == null) {
			_uiItemsBuilder = new UIItemsBuilder(
				httpServletRequest, fileVersion, resourceBundle, dlTrashHelper,
				versioningStrategy, dlURLHelper);
		}
		else {
			_uiItemsBuilder = new UIItemsBuilder(
				httpServletRequest, fileShortcut, resourceBundle, dlTrashHelper,
				versioningStrategy, dlURLHelper);
		}
	}

	public DefaultIGViewFileVersionDisplayContext(
			DLTrashHelper dlTrashHelper, DLURLHelper dlURLHelper,
			FileShortcut fileShortcut, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse,
			ResourceBundle resourceBundle,
			VersioningStrategy versioningStrategy)
		throws PortalException {

		this(
			dlTrashHelper, dlURLHelper, fileShortcut,
			fileShortcut.getFileVersion(), httpServletRequest,
			httpServletResponse, resourceBundle, versioningStrategy);
	}

	public DefaultIGViewFileVersionDisplayContext(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, FileVersion fileVersion,
			ResourceBundle resourceBundle, DLTrashHelper dlTrashHelper,
			VersioningStrategy versioningStrategy, DLURLHelper dlURLHelper)
		throws PortalException {

		this(
			dlTrashHelper, dlURLHelper, null, fileVersion, httpServletRequest,
			httpServletResponse, resourceBundle, versioningStrategy);
	}

	@Override
	public List<DropdownItem> getActionDropdownItems() {
		if (!_dlPortletInstanceSettingsHelper.isShowActions()) {
			return null;
		}

		return DropdownItemListBuilder.addGroup(
			dropdownGroupItem -> {
				dropdownGroupItem.setDropdownItems(
					DropdownItemListBuilder.add(
						_uiItemsBuilder::isDownloadActionAvailable,
						_uiItemsBuilder.createDownloadDropdownItem()
					).add(
						_uiItemsBuilder::isViewOriginalFileActionAvailable,
						_uiItemsBuilder.createViewOriginalFileDropdownItem()
					).add(
						_uiItemsBuilder::isEditActionAvailable,
						_uiItemsBuilder.createEditDropdownItem()
					).build());
				dropdownGroupItem.setSeparator(true);
			}
		).addGroup(
			dropdownGroupItem -> {
				dropdownGroupItem.setDropdownItems(
					DropdownItemListBuilder.add(
						_uiItemsBuilder::isPermissionsActionAvailable,
						_uiItemsBuilder.createPermissionsDropdownItem()
					).add(
						_uiItemsBuilder::isDeleteActionAvailable,
						_uiItemsBuilder.createDeleteDropdownItem()
					).build());
				dropdownGroupItem.setSeparator(true);
			}
		).build();
	}

	@Override
	public UUID getUuid() {
		return _UUID;
	}

	private static final UUID _UUID = UUID.fromString(
		"C04528F9-C005-4E21-A926-F068750B99DB");

	private final DLPortletInstanceSettingsHelper
		_dlPortletInstanceSettingsHelper;
	private final IGRequestHelper _igRequestHelper;
	private final UIItemsBuilder _uiItemsBuilder;

}
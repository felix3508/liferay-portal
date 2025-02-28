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

package com.liferay.fragment.web.internal.display.context;

import com.liferay.fragment.constants.FragmentActionKeys;
import com.liferay.fragment.constants.FragmentConstants;
import com.liferay.fragment.web.internal.info.field.type.CaptchaInfoFieldType;
import com.liferay.fragment.web.internal.security.permission.resource.FragmentPermission;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.CreationMenu;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.CreationMenuBuilder;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItemListBuilder;
import com.liferay.info.field.type.BooleanInfoFieldType;
import com.liferay.info.field.type.DateInfoFieldType;
import com.liferay.info.field.type.FileInfoFieldType;
import com.liferay.info.field.type.InfoFieldType;
import com.liferay.info.field.type.NumberInfoFieldType;
import com.liferay.info.field.type.RelationshipInfoFieldType;
import com.liferay.info.field.type.SelectInfoFieldType;
import com.liferay.info.field.type.TextInfoFieldType;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.url.builder.PortletURLBuilder;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.List;
import java.util.Map;

import javax.portlet.ResourceURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Eudaldo Alonso
 */
public class BasicFragmentManagementToolbarDisplayContext
	extends FragmentManagementToolbarDisplayContext {

	public BasicFragmentManagementToolbarDisplayContext(
		HttpServletRequest httpServletRequest,
		LiferayPortletRequest liferayPortletRequest,
		LiferayPortletResponse liferayPortletResponse,
		FragmentDisplayContext fragmentDisplayContext) {

		super(
			httpServletRequest, liferayPortletRequest, liferayPortletResponse,
			fragmentDisplayContext.getFragmentEntriesSearchContainer(),
			fragmentDisplayContext);
	}

	@Override
	public List<DropdownItem> getActionDropdownItems() {
		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		boolean hasManageFragmentEntriesPermission =
			FragmentPermission.contains(
				themeDisplay.getPermissionChecker(),
				themeDisplay.getScopeGroupId(),
				FragmentActionKeys.MANAGE_FRAGMENT_ENTRIES);

		return DropdownItemListBuilder.addGroup(
			dropdownGroupItem -> {
				dropdownGroupItem.setDropdownItems(
					DropdownItemListBuilder.add(
						dropdownItem -> {
							dropdownItem.putData(
								"action",
								"exportFragmentCompositionsAndFragmentEntries");
							dropdownItem.setIcon("upload");
							dropdownItem.setLabel(
								LanguageUtil.get(httpServletRequest, "export"));
							dropdownItem.setQuickAction(true);
						}
					).add(
						() -> hasManageFragmentEntriesPermission,
						dropdownItem -> {
							dropdownItem.putData(
								"action", "copySelectedFragmentEntries");
							dropdownItem.setIcon("copy");
							dropdownItem.setLabel(
								LanguageUtil.get(
									httpServletRequest, "make-a-copy"));
							dropdownItem.setQuickAction(true);
						}
					).add(
						() -> hasManageFragmentEntriesPermission,
						dropdownItem -> {
							dropdownItem.putData(
								"action",
								"moveFragmentCompositionsAndFragmentEntries");
							dropdownItem.setIcon("move-folder");
							dropdownItem.setLabel(
								LanguageUtil.get(httpServletRequest, "move"));
							dropdownItem.setQuickAction(true);
						}
					).build());
				dropdownGroupItem.setSeparator(true);
			}
		).addGroup(
			dropdownGroupItem -> {
				dropdownGroupItem.setDropdownItems(
					DropdownItemListBuilder.add(
						() -> hasManageFragmentEntriesPermission,
						dropdownItem -> {
							dropdownItem.putData(
								"action",
								"deleteFragmentCompositionsAndFragmentEntries");
							dropdownItem.setIcon("trash");
							dropdownItem.setLabel(
								LanguageUtil.get(httpServletRequest, "delete"));
							dropdownItem.setQuickAction(true);
						}
					).build());
				dropdownGroupItem.setSeparator(true);
			}
		).build();
	}

	@Override
	public Map<String, Object> getComponentContext() throws Exception {
		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		return HashMapBuilder.<String, Object>put(
			"addFragmentEntryURL",
			() -> PortletURLBuilder.createActionURL(
				liferayPortletResponse
			).setActionName(
				"/fragment/add_fragment_entry"
			).setParameter(
				"fragmentCollectionId",
				fragmentDisplayContext.getFragmentCollectionId()
			).buildString()
		).put(
			"copyFragmentEntryURL",
			() -> PortletURLBuilder.createActionURL(
				liferayPortletResponse
			).setActionName(
				"/fragment/copy_fragment_entry"
			).setRedirect(
				themeDisplay.getURLCurrent()
			).buildString()
		).put(
			"deleteFragmentCompositionsAndFragmentEntriesURL",
			PortletURLBuilder.createActionURL(
				liferayPortletResponse
			).setActionName(
				"/fragment/delete_fragment_compositions_and_fragment_entries"
			).setRedirect(
				themeDisplay.getURLCurrent()
			).buildString()
		).put(
			"exportFragmentCompositionsAndFragmentEntriesURL",
			() -> {
				ResourceURL exportFragmentCompositionsAndFragmentEntriesURL =
					liferayPortletResponse.createResourceURL();

				exportFragmentCompositionsAndFragmentEntriesURL.setResourceID(
					"/fragment" +
						"/export_fragment_compositions_and_fragment_entries");

				return exportFragmentCompositionsAndFragmentEntriesURL.
					toString();
			}
		).put(
			"fieldTypes", _getFieldTypesJSONArray()
		).put(
			"fragmentCollectionId",
			ParamUtil.getLong(liferayPortletRequest, "fragmentCollectionId")
		).put(
			"fragmentTypes", _getFragmentTypesJSONArray(themeDisplay)
		).put(
			"moveFragmentCompositionsAndFragmentEntriesURL",
			() -> PortletURLBuilder.createActionURL(
				liferayPortletResponse
			).setActionName(
				"/fragment/move_fragment_compositions_and_fragment_entries"
			).setRedirect(
				themeDisplay.getURLCurrent()
			).buildString()
		).put(
			"selectFragmentCollectionURL",
			() -> PortletURLBuilder.createActionURL(
				liferayPortletResponse
			).setMVCRenderCommandName(
				"/fragment/select_fragment_collection"
			).setWindowState(
				LiferayWindowState.POP_UP
			).buildString()
		).build();
	}

	@Override
	public CreationMenu getCreationMenu() {
		return CreationMenuBuilder.addDropdownItem(
			dropdownItem -> {
				dropdownItem.putData("action", "addFragmentEntry");

				dropdownItem.putData(
					"addFragmentEntryURL",
					PortletURLBuilder.createActionURL(
						liferayPortletResponse
					).setActionName(
						"/fragment/add_fragment_entry"
					).setParameter(
						"fragmentCollectionId",
						fragmentDisplayContext.getFragmentCollectionId()
					).setParameter(
						"type", FragmentConstants.TYPE_COMPONENT
					).buildString());

				dropdownItem.putData(
					"title",
					LanguageUtil.get(httpServletRequest, "add-fragment"));
				dropdownItem.setLabel(
					LanguageUtil.get(httpServletRequest, "add"));
			}
		).build();
	}

	@Override
	public Boolean isShowCreationMenu() {
		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		if (FragmentPermission.contains(
				themeDisplay.getPermissionChecker(),
				themeDisplay.getScopeGroupId(),
				FragmentActionKeys.MANAGE_FRAGMENT_ENTRIES)) {

			return true;
		}

		return false;
	}

	private JSONArray _getFieldTypesJSONArray() {
		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		for (InfoFieldType infoFieldType : _INFO_FIELD_TYPES) {
			jsonArray.put(
				JSONUtil.put(
					"key", infoFieldType.getName()
				).put(
					"label", infoFieldType.getLabel(themeDisplay.getLocale())
				));
		}

		return jsonArray;
	}

	private JSONArray _getFragmentTypesJSONArray(ThemeDisplay themeDisplay) {
		return JSONUtil.putAll(
			JSONUtil.put(
				"description",
				LanguageUtil.get(
					themeDisplay.getLocale(),
					"build-fragments-using-html-css-and-javascript")
			).put(
				"key", FragmentConstants.TYPE_COMPONENT
			).put(
				"name", "basic"
			).put(
				"symbol", "code"
			).put(
				"title",
				LanguageUtil.get(themeDisplay.getLocale(), "basic-fragment")
			),
			JSONUtil.put(
				"description",
				LanguageUtil.get(
					themeDisplay.getLocale(),
					"build-input-fragments-for-forms-using-html-css-and-" +
						"javascript")
			).put(
				"key", FragmentConstants.TYPE_INPUT
			).put(
				"name", "form"
			).put(
				"symbol", "forms"
			).put(
				"title",
				LanguageUtil.get(themeDisplay.getLocale(), "form-fragment")
			));
	}

	private static final InfoFieldType[] _INFO_FIELD_TYPES = {
		BooleanInfoFieldType.INSTANCE, CaptchaInfoFieldType.INSTANCE,
		DateInfoFieldType.INSTANCE, FileInfoFieldType.INSTANCE,
		NumberInfoFieldType.INSTANCE, RelationshipInfoFieldType.INSTANCE,
		SelectInfoFieldType.INSTANCE, TextInfoFieldType.INSTANCE
	};

}
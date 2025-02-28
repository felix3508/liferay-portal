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

package com.liferay.journal.web.internal.portlet.action;

import com.liferay.dynamic.data.mapping.constants.DDMTemplateConstants;
import com.liferay.dynamic.data.mapping.model.DDMTemplate;
import com.liferay.dynamic.data.mapping.service.DDMTemplateService;
import com.liferay.journal.constants.JournalPortletKeys;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.portlet.url.builder.PortletURLBuilder;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.template.TemplateConstants;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.Localization;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.File;

import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + JournalPortletKeys.JOURNAL,
		"mvc.command.name=/journal/update_ddm_template"
	},
	service = MVCActionCommand.class
)
public class UpdateDDMTemplateMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		UploadPortletRequest uploadPortletRequest =
			_portal.getUploadPortletRequest(actionRequest);

		long ddmTemplateId = ParamUtil.getLong(
			uploadPortletRequest, "ddmTemplateId");

		long classPK = ParamUtil.getLong(uploadPortletRequest, "classPK");
		Map<Locale, String> nameMap = _localization.getLocalizationMap(
			uploadPortletRequest, "name");
		Map<Locale, String> descriptionMap = _localization.getLocalizationMap(
			uploadPortletRequest, "description");
		String script = ActionUtil.getScript(uploadPortletRequest);
		boolean cacheable = ParamUtil.getBoolean(
			uploadPortletRequest, "cacheable");

		String smallImageSource = ParamUtil.getString(
			uploadPortletRequest, "smallImageSource", "none");

		boolean smallImage = !Objects.equals(smallImageSource, "none");

		String smallImageURL = StringPool.BLANK;
		File smallImageFile = null;

		if (Objects.equals(smallImageSource, "url")) {
			smallImageURL = ParamUtil.getString(
				uploadPortletRequest, "smallImageURL");
		}
		else if (Objects.equals(smallImageSource, "file")) {
			smallImageFile = uploadPortletRequest.getFile("smallImageFile");
		}

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			DDMTemplate.class.getName(), uploadPortletRequest);

		DDMTemplate ddmTemplate = _ddmTemplateService.updateTemplate(
			ddmTemplateId, classPK, nameMap, descriptionMap,
			DDMTemplateConstants.TEMPLATE_TYPE_DISPLAY, StringPool.BLANK,
			TemplateConstants.LANG_TYPE_FTL, script, cacheable, smallImage,
			smallImageURL, smallImageFile, serviceContext);

		boolean saveAndContinue = ParamUtil.getBoolean(
			uploadPortletRequest, "saveAndContinue");

		if (saveAndContinue) {
			actionRequest.setAttribute(
				WebKeys.REDIRECT,
				PortletURLBuilder.createRenderURL(
					_portal.getLiferayPortletResponse(actionResponse)
				).setMVCPath(
					"/edit_ddm_template.jsp"
				).setRedirect(
					ParamUtil.getString(uploadPortletRequest, "redirect")
				).setParameter(
					"ddmTemplateId", ddmTemplate.getTemplateId()
				).buildString());
		}
	}

	@Reference
	private DDMTemplateService _ddmTemplateService;

	@Reference
	private Localization _localization;

	@Reference
	private Portal _portal;

}
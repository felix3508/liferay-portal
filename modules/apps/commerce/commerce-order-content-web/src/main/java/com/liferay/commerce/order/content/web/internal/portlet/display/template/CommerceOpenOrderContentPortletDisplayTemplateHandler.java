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

package com.liferay.commerce.order.content.web.internal.portlet.display.template;

import com.liferay.commerce.constants.CommercePortletKeys;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.order.content.web.internal.display.context.CommerceOrderContentDisplayContext;
import com.liferay.commerce.order.content.web.internal.portlet.CommerceOpenOrderContentPortlet;
import com.liferay.commerce.service.CommerceOrderLocalService;
import com.liferay.commerce.service.CommerceOrderService;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.portletdisplaytemplate.BasePortletDisplayTemplateHandler;
import com.liferay.portal.kernel.template.TemplateHandler;
import com.liferay.portal.kernel.template.TemplateVariableGroup;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portlet.display.template.constants.PortletDisplayTemplateConstants;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	immediate = true,
	property = "javax.portlet.name=" + CommercePortletKeys.COMMERCE_OPEN_ORDER_CONTENT,
	service = TemplateHandler.class
)
public class CommerceOpenOrderContentPortletDisplayTemplateHandler
	extends BasePortletDisplayTemplateHandler {

	@Override
	public String getClassName() {
		return CommerceOpenOrderContentPortlet.class.getName();
	}

	@Override
	public String getName(Locale locale) {
		StringBundler sb = new StringBundler(3);

		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		sb.append(
			_portal.getPortletTitle(
				CommercePortletKeys.COMMERCE_OPEN_ORDER_CONTENT,
				resourceBundle));

		sb.append(StringPool.SPACE);
		sb.append(_language.get(locale, "template"));

		return sb.toString();
	}

	@Override
	public String getResourceName() {
		return CommercePortletKeys.COMMERCE_OPEN_ORDER_CONTENT;
	}

	@Override
	public Map<String, TemplateVariableGroup> getTemplateVariableGroups(
			long classPK, String language, Locale locale)
		throws Exception {

		Map<String, TemplateVariableGroup> templateVariableGroups =
			super.getTemplateVariableGroups(classPK, language, locale);

		TemplateVariableGroup templateVariableGroup =
			templateVariableGroups.get("fields");

		templateVariableGroup.empty();

		templateVariableGroup.addVariable(
			"commerce-order-content-display-context",
			CommerceOrderContentDisplayContext.class,
			"commerceOrderContentDisplayContext");
		templateVariableGroup.addCollectionVariable(
			"commerce-orders", List.class,
			PortletDisplayTemplateConstants.ENTRIES, "commerceOrder",
			CommerceOrder.class, "curCommerceOrder", "commerceOrderId");

		TemplateVariableGroup commerceOrderServicesTemplateVariableGroup =
			new TemplateVariableGroup(
				"commerce-order-services", getRestrictedVariables(language));

		commerceOrderServicesTemplateVariableGroup.setAutocompleteEnabled(
			false);

		commerceOrderServicesTemplateVariableGroup.addServiceLocatorVariables(
			CommerceOrderLocalService.class, CommerceOrderService.class);

		templateVariableGroups.put(
			commerceOrderServicesTemplateVariableGroup.getLabel(),
			commerceOrderServicesTemplateVariableGroup);

		return templateVariableGroups;
	}

	@Reference
	private Language _language;

	@Reference
	private Portal _portal;

}
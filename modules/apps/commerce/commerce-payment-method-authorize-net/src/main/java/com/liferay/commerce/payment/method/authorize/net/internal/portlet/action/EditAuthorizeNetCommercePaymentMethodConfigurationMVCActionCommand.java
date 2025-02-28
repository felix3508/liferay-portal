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

package com.liferay.commerce.payment.method.authorize.net.internal.portlet.action;

import com.liferay.commerce.payment.method.authorize.net.internal.constants.AuthorizeNetCommercePaymentMethodConstants;
import com.liferay.commerce.product.constants.CPPortletKeys;
import com.liferay.commerce.product.model.CommerceChannel;
import com.liferay.commerce.product.service.CommerceChannelService;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.settings.GroupServiceSettingsLocator;
import com.liferay.portal.kernel.settings.ModifiableSettings;
import com.liferay.portal.kernel.settings.Settings;
import com.liferay.portal.kernel.settings.SettingsFactory;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Luca Pellizzon
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + CPPortletKeys.COMMERCE_PAYMENT_METHODS,
		"mvc.command.name=/commerce_payment_methods/edit_authorize_net_commerce_payment_method_configuration"
	},
	service = MVCActionCommand.class
)
public class EditAuthorizeNetCommercePaymentMethodConfigurationMVCActionCommand
	extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		if (cmd.equals(Constants.UPDATE)) {
			_updateCommercePaymentMethod(actionRequest);
		}
	}

	private void _updateCommercePaymentMethod(ActionRequest actionRequest)
		throws Exception {

		long commerceChannelId = ParamUtil.getLong(
			actionRequest, "commerceChannelId");

		CommerceChannel commerceChannel =
			_commerceChannelService.getCommerceChannel(commerceChannelId);

		Settings settings = _settingsFactory.getSettings(
			new GroupServiceSettingsLocator(
				commerceChannel.getGroupId(),
				AuthorizeNetCommercePaymentMethodConstants.SERVICE_NAME));

		ModifiableSettings modifiableSettings =
			settings.getModifiableSettings();

		String apiLoginId = ParamUtil.getString(
			actionRequest, "settings--apiLoginId--");

		modifiableSettings.setValue("apiLoginId", apiLoginId);

		String environment = ParamUtil.getString(
			actionRequest, "settings--environment--");

		modifiableSettings.setValue("environment", environment);

		String requireCaptcha = ParamUtil.getString(
			actionRequest, "settings--requireCaptcha--");

		modifiableSettings.setValue("requireCaptcha", requireCaptcha);

		String requireCardCodeVerification = ParamUtil.getString(
			actionRequest, "settings--requireCardCodeVerification--");

		modifiableSettings.setValue(
			"requireCardCodeVerification", requireCardCodeVerification);

		String showBankAccount = ParamUtil.getString(
			actionRequest, "settings--showBankAccount--");

		modifiableSettings.setValue("showBankAccount", showBankAccount);

		String showCreditCard = ParamUtil.getString(
			actionRequest, "settings--showCreditCard--");

		modifiableSettings.setValue("showCreditCard", showCreditCard);

		String showStoreName = ParamUtil.getString(
			actionRequest, "settings--showStoreName--");

		modifiableSettings.setValue("showStoreName", showStoreName);

		String transactionKey = ParamUtil.getString(
			actionRequest, "settings--transactionKey--");

		modifiableSettings.setValue("transactionKey", transactionKey);

		modifiableSettings.store();
	}

	@Reference
	private CommerceChannelService _commerceChannelService;

	@Reference
	private SettingsFactory _settingsFactory;

}
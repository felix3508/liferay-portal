/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.commerce.dashboard.web.internal.configuration.definition;

import com.liferay.commerce.dashboard.web.internal.configuration.CommerceDashboardForecastPortletInstanceConfiguration;
import com.liferay.portal.kernel.settings.definition.ConfigurationBeanDeclaration;

import org.osgi.service.component.annotations.Component;

/**
 * @author Riccardo Ferrari
 */
@Component(service = ConfigurationBeanDeclaration.class)
public class
	CommerceDashboardForecastPortletInstanceConfigurationBeanDeclaration
		implements ConfigurationBeanDeclaration {

	@Override
	public Class<?> getConfigurationBeanClass() {
		return CommerceDashboardForecastPortletInstanceConfiguration.class;
	}

}
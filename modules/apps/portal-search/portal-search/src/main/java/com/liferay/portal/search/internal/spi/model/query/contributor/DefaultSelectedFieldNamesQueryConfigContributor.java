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

package com.liferay.portal.search.internal.spi.model.query.contributor;

import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.QueryConfig;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Localization;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.search.spi.model.query.contributor.QueryConfigContributor;
import com.liferay.portal.search.spi.model.query.contributor.helper.QueryConfigContributorHelper;

import java.util.HashSet;
import java.util.Set;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Michael C. Han
 */
@Component(
	immediate = true,
	property = "service.ranking:Integer=" + DefaultSelectedFieldNamesQueryConfigContributor.RANKING,
	service = QueryConfigContributor.class
)
public class DefaultSelectedFieldNamesQueryConfigContributor
	implements QueryConfigContributor {

	public static final int RANKING = 1;

	@Override
	public void contributeQueryConfigurations(
		SearchContext searchContext,
		QueryConfigContributorHelper queryConfigContributorHelper) {

		QueryConfig queryConfig = searchContext.getQueryConfig();

		if (ArrayUtil.isNotEmpty(queryConfig.getSelectedFieldNames())) {
			return;
		}

		Set<String> selectedFieldNames = null;

		String[] defaultSelectedFieldNames =
			queryConfigContributorHelper.getDefaultSelectedFieldNames();

		if (!ArrayUtil.isEmpty(defaultSelectedFieldNames)) {
			selectedFieldNames = SetUtil.fromArray(defaultSelectedFieldNames);

			if (searchContext.isIncludeAttachments() ||
				searchContext.isIncludeDiscussions()) {

				selectedFieldNames.add(Field.CLASS_NAME_ID);
				selectedFieldNames.add(Field.CLASS_PK);
			}
		}

		if (!ArrayUtil.isEmpty(
				queryConfigContributorHelper.
					getDefaultSelectedLocalizedFieldNames())) {

			if (selectedFieldNames == null) {
				selectedFieldNames = new HashSet<>();
			}

			if (queryConfigContributorHelper.isSelectAllLocales()) {
				_addSelectedLocalizedFieldNames(
					queryConfigContributorHelper, selectedFieldNames,
					LocaleUtil.toLanguageIds(_language.getAvailableLocales()));
			}
			else {
				_addSelectedLocalizedFieldNames(
					queryConfigContributorHelper, selectedFieldNames,
					LocaleUtil.toLanguageId(queryConfig.getLocale()));
			}
		}

		if ((selectedFieldNames != null) && !selectedFieldNames.isEmpty()) {
			queryConfig.setSelectedFieldNames(
				selectedFieldNames.toArray(new String[0]));
		}
	}

	private void _addSelectedLocalizedFieldNames(
		QueryConfigContributorHelper queryConfigContributorHelper,
		Set<String> selectedFieldNames, String... languageIds) {

		for (String defaultLocalizedSelectedFieldName :
				queryConfigContributorHelper.
					getDefaultSelectedLocalizedFieldNames()) {

			selectedFieldNames.add(defaultLocalizedSelectedFieldName);

			for (String languageId : languageIds) {
				String localizedFieldName = _localization.getLocalizedName(
					defaultLocalizedSelectedFieldName, languageId);

				selectedFieldNames.add(localizedFieldName);
			}
		}
	}

	@Reference
	private Language _language;

	@Reference
	private Localization _localization;

}
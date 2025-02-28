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

package com.liferay.list.type.service.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.list.type.exception.ListTypeDefinitionNameException;
import com.liferay.list.type.exception.RequiredListTypeDefinitionException;
import com.liferay.list.type.model.ListTypeDefinition;
import com.liferay.list.type.model.ListTypeEntry;
import com.liferay.list.type.service.ListTypeDefinitionLocalService;
import com.liferay.list.type.service.ListTypeEntryLocalService;
import com.liferay.object.constants.ObjectDefinitionConstants;
import com.liferay.object.constants.ObjectFieldConstants;
import com.liferay.object.model.ObjectDefinition;
import com.liferay.object.model.ObjectField;
import com.liferay.object.service.ObjectDefinitionLocalService;
import com.liferay.object.util.LocalizedMapUtil;
import com.liferay.object.util.ObjectFieldUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnicodePropertiesBuilder;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.util.PropsUtil;

import java.util.Collections;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Gabriel Albuquerque
 */
@RunWith(Arquillian.class)
public class ListTypeDefinitionLocalServiceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@BeforeClass
	public static void setUpClass() throws Exception {
		PropsUtil.addProperties(
			UnicodePropertiesBuilder.setProperty(
				"feature.flag.LPS-164278", "true"
			).build());
	}

	@AfterClass
	public static void tearDownClass() throws Exception {
		PropsUtil.addProperties(
			UnicodePropertiesBuilder.setProperty(
				"feature.flag.LPS-164278", "false"
			).build());
	}

	@Test
	public void testAddListTypeDefinition() throws Exception {
		ListTypeDefinition listTypeDefinition = _addListTypeDefinition();

		Assert.assertNotNull(listTypeDefinition);
		Assert.assertTrue(
			Validator.isNotNull(listTypeDefinition.getExternalReferenceCode()));
		Assert.assertTrue(Validator.isNotNull(listTypeDefinition.getName()));
		Assert.assertNotNull(
			_listTypeDefinitionLocalService.fetchListTypeDefinition(
				listTypeDefinition.getListTypeDefinitionId()));

		try {
			_listTypeDefinitionLocalService.addListTypeDefinition(
				TestPropsValues.getUserId(),
				Collections.singletonMap(LocaleUtil.US, ""));

			Assert.fail();
		}
		catch (ListTypeDefinitionNameException
					listTypeDefinitionNameException) {

			Assert.assertEquals(
				"Name is null for locale " + LocaleUtil.US.getDisplayName(),
				listTypeDefinitionNameException.getMessage());
		}
	}

	@Test
	public void testDeleteListTypeDefinition() throws Exception {
		ListTypeDefinition listTypeDefinition =
			_listTypeDefinitionLocalService.addListTypeDefinition(
				TestPropsValues.getUserId(),
				Collections.singletonMap(
					LocaleUtil.US, RandomTestUtil.randomString()));

		ListTypeEntry listTypeEntry =
			_listTypeEntryLocalService.addListTypeEntry(
				TestPropsValues.getUserId(),
				listTypeDefinition.getListTypeDefinitionId(),
				StringUtil.randomId(),
				Collections.singletonMap(
					LocaleUtil.US, RandomTestUtil.randomString()));

		Assert.assertNotNull(listTypeEntry);

		ObjectField objectField = ObjectFieldUtil.createObjectField(
			ObjectFieldConstants.BUSINESS_TYPE_TEXT,
			ObjectFieldConstants.DB_TYPE_STRING, StringUtil.randomId());

		objectField.setListTypeDefinitionId(
			listTypeDefinition.getListTypeDefinitionId());

		ObjectDefinition objectDefinition =
			_objectDefinitionLocalService.addCustomObjectDefinition(
				TestPropsValues.getUserId(),
				LocalizedMapUtil.getLocalizedMap("Test"), "Test", null, null,
				LocalizedMapUtil.getLocalizedMap("Tests"),
				ObjectDefinitionConstants.SCOPE_COMPANY,
				ObjectDefinitionConstants.STORAGE_TYPE_DEFAULT,
				Collections.singletonList(objectField));

		try {
			_listTypeDefinitionLocalService.deleteListTypeDefinition(
				listTypeDefinition.getListTypeDefinitionId());

			Assert.fail();
		}
		catch (RequiredListTypeDefinitionException
					requiredListTypeDefinitionException) {

			Assert.assertNotNull(requiredListTypeDefinitionException);
		}

		_objectDefinitionLocalService.deleteObjectDefinition(
			objectDefinition.getObjectDefinitionId());

		_listTypeDefinitionLocalService.deleteListTypeDefinition(
			listTypeDefinition.getListTypeDefinitionId());

		Assert.assertNull(
			_listTypeDefinitionLocalService.fetchListTypeDefinition(
				listTypeDefinition.getListTypeDefinitionId()));
		Assert.assertNull(
			_listTypeEntryLocalService.fetchListTypeEntry(
				listTypeEntry.getListTypeEntryId()));
	}

	@Test
	public void testUpdateListTypeDefinition() throws Exception {
		ListTypeDefinition listTypeDefinition = _addListTypeDefinition();

		String externalReferenceCode = RandomTestUtil.randomString();
		String name = RandomTestUtil.randomString();

		listTypeDefinition =
			_listTypeDefinitionLocalService.updateListTypeDefinition(
				externalReferenceCode,
				listTypeDefinition.getListTypeDefinitionId(),
				Collections.singletonMap(LocaleUtil.getDefault(), name));

		Assert.assertEquals(
			externalReferenceCode,
			listTypeDefinition.getExternalReferenceCode());
		Assert.assertEquals(
			name, listTypeDefinition.getName(LocaleUtil.getDefault()));

		listTypeDefinition =
			_listTypeDefinitionLocalService.updateListTypeDefinition(
				StringPool.BLANK, listTypeDefinition.getListTypeDefinitionId(),
				Collections.singletonMap(LocaleUtil.getDefault(), name));

		externalReferenceCode = listTypeDefinition.getExternalReferenceCode();

		Assert.assertFalse(externalReferenceCode.isEmpty());
	}

	private ListTypeDefinition _addListTypeDefinition() throws Exception {
		return _listTypeDefinitionLocalService.addListTypeDefinition(
			TestPropsValues.getUserId(),
			Collections.singletonMap(
				LocaleUtil.US, RandomTestUtil.randomString()));
	}

	@Inject
	private ListTypeDefinitionLocalService _listTypeDefinitionLocalService;

	@Inject
	private ListTypeEntryLocalService _listTypeEntryLocalService;

	@Inject
	private ObjectDefinitionLocalService _objectDefinitionLocalService;

}
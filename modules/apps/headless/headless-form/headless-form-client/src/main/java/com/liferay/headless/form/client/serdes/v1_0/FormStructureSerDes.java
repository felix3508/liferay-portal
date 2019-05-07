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

package com.liferay.headless.form.client.serdes.v1_0;

import com.liferay.headless.form.client.dto.v1_0.FormLayoutPage;
import com.liferay.headless.form.client.dto.v1_0.FormStructure;
import com.liferay.headless.form.client.json.BaseJSONParser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

import javax.annotation.Generated;

/**
 * @author Javier Gamarra
 * @generated
 */
@Generated("")
public class FormStructureSerDes {

	public static FormStructure toDTO(String json) {
		FormStructureJSONParser formStructureJSONParser =
			new FormStructureJSONParser();

		return formStructureJSONParser.parseToDTO(json);
	}

	public static FormStructure[] toDTOs(String json) {
		FormStructureJSONParser formStructureJSONParser =
			new FormStructureJSONParser();

		return formStructureJSONParser.parseToDTOs(json);
	}

	public static String toJSON(FormStructure formStructure) {
		if (formStructure == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		DateFormat liferayToJSONDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss'Z'");

		if (formStructure.getAvailableLanguages() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"availableLanguages\": ");

			sb.append("[");

			for (int i = 0; i < formStructure.getAvailableLanguages().length;
				 i++) {

				sb.append("\"");

				sb.append(_escape(formStructure.getAvailableLanguages()[i]));

				sb.append("\"");

				if ((i + 1) < formStructure.getAvailableLanguages().length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		if (formStructure.getCreator() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"creator\": ");

			sb.append(String.valueOf(formStructure.getCreator()));
		}

		if (formStructure.getDateCreated() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"dateCreated\": ");

			sb.append("\"");

			sb.append(
				liferayToJSONDateFormat.format(formStructure.getDateCreated()));

			sb.append("\"");
		}

		if (formStructure.getDateModified() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"dateModified\": ");

			sb.append("\"");

			sb.append(
				liferayToJSONDateFormat.format(
					formStructure.getDateModified()));

			sb.append("\"");
		}

		if (formStructure.getDescription() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"description\": ");

			sb.append("\"");

			sb.append(_escape(formStructure.getDescription()));

			sb.append("\"");
		}

		if (formStructure.getFormLayoutPages() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"formLayoutPages\": ");

			sb.append("[");

			for (int i = 0; i < formStructure.getFormLayoutPages().length;
				 i++) {

				sb.append(
					String.valueOf(formStructure.getFormLayoutPages()[i]));

				if ((i + 1) < formStructure.getFormLayoutPages().length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		if (formStructure.getFormSuccessPageSettings() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"formSuccessPageSettings\": ");

			sb.append(
				String.valueOf(formStructure.getFormSuccessPageSettings()));
		}

		if (formStructure.getId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"id\": ");

			sb.append(formStructure.getId());
		}

		if (formStructure.getName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"name\": ");

			sb.append("\"");

			sb.append(_escape(formStructure.getName()));

			sb.append("\"");
		}

		if (formStructure.getSiteId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"siteId\": ");

			sb.append(formStructure.getSiteId());
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		FormStructureJSONParser formStructureJSONParser =
			new FormStructureJSONParser();

		return formStructureJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(FormStructure formStructure) {
		if (formStructure == null) {
			return null;
		}

		Map<String, String> map = new HashMap<>();

		DateFormat liferayToJSONDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss'Z'");

		if (formStructure.getAvailableLanguages() == null) {
			map.put("availableLanguages", null);
		}
		else {
			map.put(
				"availableLanguages",
				String.valueOf(formStructure.getAvailableLanguages()));
		}

		if (formStructure.getCreator() == null) {
			map.put("creator", null);
		}
		else {
			map.put("creator", String.valueOf(formStructure.getCreator()));
		}

		map.put(
			"dateCreated",
			liferayToJSONDateFormat.format(formStructure.getDateCreated()));

		map.put(
			"dateModified",
			liferayToJSONDateFormat.format(formStructure.getDateModified()));

		if (formStructure.getDescription() == null) {
			map.put("description", null);
		}
		else {
			map.put(
				"description", String.valueOf(formStructure.getDescription()));
		}

		if (formStructure.getFormLayoutPages() == null) {
			map.put("formLayoutPages", null);
		}
		else {
			map.put(
				"formLayoutPages",
				String.valueOf(formStructure.getFormLayoutPages()));
		}

		if (formStructure.getFormSuccessPageSettings() == null) {
			map.put("formSuccessPageSettings", null);
		}
		else {
			map.put(
				"formSuccessPageSettings",
				String.valueOf(formStructure.getFormSuccessPageSettings()));
		}

		if (formStructure.getId() == null) {
			map.put("id", null);
		}
		else {
			map.put("id", String.valueOf(formStructure.getId()));
		}

		if (formStructure.getName() == null) {
			map.put("name", null);
		}
		else {
			map.put("name", String.valueOf(formStructure.getName()));
		}

		if (formStructure.getSiteId() == null) {
			map.put("siteId", null);
		}
		else {
			map.put("siteId", String.valueOf(formStructure.getSiteId()));
		}

		return map;
	}

	private static String _escape(Object object) {
		String string = String.valueOf(object);

		return string.replaceAll("\"", "\\\\\"");
	}

	private static String _toJSON(Map<String, ?> map) {
		StringBuilder sb = new StringBuilder("{");

		@SuppressWarnings("unchecked")
		Set set = map.entrySet();

		@SuppressWarnings("unchecked")
		Iterator<Map.Entry<String, ?>> iterator = set.iterator();

		while (iterator.hasNext()) {
			Map.Entry<String, ?> entry = iterator.next();

			sb.append("\"");
			sb.append(entry.getKey());
			sb.append("\":");
			sb.append("\"");
			sb.append(entry.getValue());
			sb.append("\"");

			if (iterator.hasNext()) {
				sb.append(",");
			}
		}

		sb.append("}");

		return sb.toString();
	}

	private static class FormStructureJSONParser
		extends BaseJSONParser<FormStructure> {

		@Override
		protected FormStructure createDTO() {
			return new FormStructure();
		}

		@Override
		protected FormStructure[] createDTOArray(int size) {
			return new FormStructure[size];
		}

		@Override
		protected void setField(
			FormStructure formStructure, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "availableLanguages")) {
				if (jsonParserFieldValue != null) {
					formStructure.setAvailableLanguages(
						toStrings((Object[])jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "creator")) {
				if (jsonParserFieldValue != null) {
					formStructure.setCreator(
						CreatorSerDes.toDTO((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "dateCreated")) {
				if (jsonParserFieldValue != null) {
					formStructure.setDateCreated(
						toDate((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "dateModified")) {
				if (jsonParserFieldValue != null) {
					formStructure.setDateModified(
						toDate((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "description")) {
				if (jsonParserFieldValue != null) {
					formStructure.setDescription((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "formLayoutPages")) {
				if (jsonParserFieldValue != null) {
					formStructure.setFormLayoutPages(
						Stream.of(
							toStrings((Object[])jsonParserFieldValue)
						).map(
							object -> FormLayoutPageSerDes.toDTO((String)object)
						).toArray(
							size -> new FormLayoutPage[size]
						));
				}
			}
			else if (Objects.equals(
						jsonParserFieldName, "formSuccessPageSettings")) {

				if (jsonParserFieldValue != null) {
					formStructure.setFormSuccessPageSettings(
						FormSuccessPageSettingsSerDes.toDTO(
							(String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "id")) {
				if (jsonParserFieldValue != null) {
					formStructure.setId(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "name")) {
				if (jsonParserFieldValue != null) {
					formStructure.setName((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "siteId")) {
				if (jsonParserFieldValue != null) {
					formStructure.setSiteId(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else {
				throw new IllegalArgumentException(
					"Unsupported field name " + jsonParserFieldName);
			}
		}

	}

}
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

package com.liferay.headless.delivery.client.serdes.v1_0;

import com.liferay.headless.delivery.client.dto.v1_0.CustomCSSViewport;
import com.liferay.headless.delivery.client.dto.v1_0.FragmentViewport;
import com.liferay.headless.delivery.client.dto.v1_0.PageSectionDefinition;
import com.liferay.headless.delivery.client.json.BaseJSONParser;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Stream;

import javax.annotation.Generated;

/**
 * @author Javier Gamarra
 * @generated
 */
@Generated("")
public class PageSectionDefinitionSerDes {

	public static PageSectionDefinition toDTO(String json) {
		PageSectionDefinitionJSONParser pageSectionDefinitionJSONParser =
			new PageSectionDefinitionJSONParser();

		return pageSectionDefinitionJSONParser.parseToDTO(json);
	}

	public static PageSectionDefinition[] toDTOs(String json) {
		PageSectionDefinitionJSONParser pageSectionDefinitionJSONParser =
			new PageSectionDefinitionJSONParser();

		return pageSectionDefinitionJSONParser.parseToDTOs(json);
	}

	public static String toJSON(PageSectionDefinition pageSectionDefinition) {
		if (pageSectionDefinition == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (pageSectionDefinition.getBackgroundColor() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"backgroundColor\": ");

			sb.append("\"");

			sb.append(_escape(pageSectionDefinition.getBackgroundColor()));

			sb.append("\"");
		}

		if (pageSectionDefinition.getBackgroundFragmentImage() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"backgroundFragmentImage\": ");

			sb.append(
				String.valueOf(
					pageSectionDefinition.getBackgroundFragmentImage()));
		}

		if (pageSectionDefinition.getBackgroundImage() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"backgroundImage\": ");

			sb.append(
				String.valueOf(pageSectionDefinition.getBackgroundImage()));
		}

		if (pageSectionDefinition.getContentVisibility() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"contentVisibility\": ");

			sb.append("\"");

			sb.append(_escape(pageSectionDefinition.getContentVisibility()));

			sb.append("\"");
		}

		if (pageSectionDefinition.getCssClasses() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"cssClasses\": ");

			sb.append("[");

			for (int i = 0; i < pageSectionDefinition.getCssClasses().length;
				 i++) {

				sb.append("\"");

				sb.append(_escape(pageSectionDefinition.getCssClasses()[i]));

				sb.append("\"");

				if ((i + 1) < pageSectionDefinition.getCssClasses().length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		if (pageSectionDefinition.getCustomCSS() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"customCSS\": ");

			sb.append("\"");

			sb.append(_escape(pageSectionDefinition.getCustomCSS()));

			sb.append("\"");
		}

		if (pageSectionDefinition.getCustomCSSViewports() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"customCSSViewports\": ");

			sb.append("[");

			for (int i = 0;
				 i < pageSectionDefinition.getCustomCSSViewports().length;
				 i++) {

				sb.append(
					String.valueOf(
						pageSectionDefinition.getCustomCSSViewports()[i]));

				if ((i + 1) <
						pageSectionDefinition.getCustomCSSViewports().length) {

					sb.append(", ");
				}
			}

			sb.append("]");
		}

		if (pageSectionDefinition.getFragmentLink() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"fragmentLink\": ");

			sb.append(String.valueOf(pageSectionDefinition.getFragmentLink()));
		}

		if (pageSectionDefinition.getFragmentStyle() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"fragmentStyle\": ");

			sb.append(String.valueOf(pageSectionDefinition.getFragmentStyle()));
		}

		if (pageSectionDefinition.getFragmentViewports() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"fragmentViewports\": ");

			sb.append("[");

			for (int i = 0;
				 i < pageSectionDefinition.getFragmentViewports().length; i++) {

				sb.append(
					String.valueOf(
						pageSectionDefinition.getFragmentViewports()[i]));

				if ((i + 1) <
						pageSectionDefinition.getFragmentViewports().length) {

					sb.append(", ");
				}
			}

			sb.append("]");
		}

		if (pageSectionDefinition.getHtmlProperties() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"htmlProperties\": ");

			sb.append(
				String.valueOf(pageSectionDefinition.getHtmlProperties()));
		}

		if (pageSectionDefinition.getIndexed() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"indexed\": ");

			sb.append(pageSectionDefinition.getIndexed());
		}

		if (pageSectionDefinition.getLayout() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"layout\": ");

			sb.append(String.valueOf(pageSectionDefinition.getLayout()));
		}

		if (pageSectionDefinition.getName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"name\": ");

			sb.append("\"");

			sb.append(_escape(pageSectionDefinition.getName()));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		PageSectionDefinitionJSONParser pageSectionDefinitionJSONParser =
			new PageSectionDefinitionJSONParser();

		return pageSectionDefinitionJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(
		PageSectionDefinition pageSectionDefinition) {

		if (pageSectionDefinition == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (pageSectionDefinition.getBackgroundColor() == null) {
			map.put("backgroundColor", null);
		}
		else {
			map.put(
				"backgroundColor",
				String.valueOf(pageSectionDefinition.getBackgroundColor()));
		}

		if (pageSectionDefinition.getBackgroundFragmentImage() == null) {
			map.put("backgroundFragmentImage", null);
		}
		else {
			map.put(
				"backgroundFragmentImage",
				String.valueOf(
					pageSectionDefinition.getBackgroundFragmentImage()));
		}

		if (pageSectionDefinition.getBackgroundImage() == null) {
			map.put("backgroundImage", null);
		}
		else {
			map.put(
				"backgroundImage",
				String.valueOf(pageSectionDefinition.getBackgroundImage()));
		}

		if (pageSectionDefinition.getContentVisibility() == null) {
			map.put("contentVisibility", null);
		}
		else {
			map.put(
				"contentVisibility",
				String.valueOf(pageSectionDefinition.getContentVisibility()));
		}

		if (pageSectionDefinition.getCssClasses() == null) {
			map.put("cssClasses", null);
		}
		else {
			map.put(
				"cssClasses",
				String.valueOf(pageSectionDefinition.getCssClasses()));
		}

		if (pageSectionDefinition.getCustomCSS() == null) {
			map.put("customCSS", null);
		}
		else {
			map.put(
				"customCSS",
				String.valueOf(pageSectionDefinition.getCustomCSS()));
		}

		if (pageSectionDefinition.getCustomCSSViewports() == null) {
			map.put("customCSSViewports", null);
		}
		else {
			map.put(
				"customCSSViewports",
				String.valueOf(pageSectionDefinition.getCustomCSSViewports()));
		}

		if (pageSectionDefinition.getFragmentLink() == null) {
			map.put("fragmentLink", null);
		}
		else {
			map.put(
				"fragmentLink",
				String.valueOf(pageSectionDefinition.getFragmentLink()));
		}

		if (pageSectionDefinition.getFragmentStyle() == null) {
			map.put("fragmentStyle", null);
		}
		else {
			map.put(
				"fragmentStyle",
				String.valueOf(pageSectionDefinition.getFragmentStyle()));
		}

		if (pageSectionDefinition.getFragmentViewports() == null) {
			map.put("fragmentViewports", null);
		}
		else {
			map.put(
				"fragmentViewports",
				String.valueOf(pageSectionDefinition.getFragmentViewports()));
		}

		if (pageSectionDefinition.getHtmlProperties() == null) {
			map.put("htmlProperties", null);
		}
		else {
			map.put(
				"htmlProperties",
				String.valueOf(pageSectionDefinition.getHtmlProperties()));
		}

		if (pageSectionDefinition.getIndexed() == null) {
			map.put("indexed", null);
		}
		else {
			map.put(
				"indexed", String.valueOf(pageSectionDefinition.getIndexed()));
		}

		if (pageSectionDefinition.getLayout() == null) {
			map.put("layout", null);
		}
		else {
			map.put(
				"layout", String.valueOf(pageSectionDefinition.getLayout()));
		}

		if (pageSectionDefinition.getName() == null) {
			map.put("name", null);
		}
		else {
			map.put("name", String.valueOf(pageSectionDefinition.getName()));
		}

		return map;
	}

	public static class PageSectionDefinitionJSONParser
		extends BaseJSONParser<PageSectionDefinition> {

		@Override
		protected PageSectionDefinition createDTO() {
			return new PageSectionDefinition();
		}

		@Override
		protected PageSectionDefinition[] createDTOArray(int size) {
			return new PageSectionDefinition[size];
		}

		@Override
		protected void setField(
			PageSectionDefinition pageSectionDefinition,
			String jsonParserFieldName, Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "backgroundColor")) {
				if (jsonParserFieldValue != null) {
					pageSectionDefinition.setBackgroundColor(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(
						jsonParserFieldName, "backgroundFragmentImage")) {

				if (jsonParserFieldValue != null) {
					pageSectionDefinition.setBackgroundFragmentImage(
						FragmentImageSerDes.toDTO(
							(String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "backgroundImage")) {
				if (jsonParserFieldValue != null) {
					pageSectionDefinition.setBackgroundImage(
						BackgroundImageSerDes.toDTO(
							(String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "contentVisibility")) {
				if (jsonParserFieldValue != null) {
					pageSectionDefinition.setContentVisibility(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "cssClasses")) {
				if (jsonParserFieldValue != null) {
					pageSectionDefinition.setCssClasses(
						toStrings((Object[])jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "customCSS")) {
				if (jsonParserFieldValue != null) {
					pageSectionDefinition.setCustomCSS(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(
						jsonParserFieldName, "customCSSViewports")) {

				if (jsonParserFieldValue != null) {
					pageSectionDefinition.setCustomCSSViewports(
						Stream.of(
							toStrings((Object[])jsonParserFieldValue)
						).map(
							object -> CustomCSSViewportSerDes.toDTO(
								(String)object)
						).toArray(
							size -> new CustomCSSViewport[size]
						));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "fragmentLink")) {
				if (jsonParserFieldValue != null) {
					pageSectionDefinition.setFragmentLink(
						FragmentLinkSerDes.toDTO((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "fragmentStyle")) {
				if (jsonParserFieldValue != null) {
					pageSectionDefinition.setFragmentStyle(
						FragmentStyleSerDes.toDTO(
							(String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "fragmentViewports")) {
				if (jsonParserFieldValue != null) {
					pageSectionDefinition.setFragmentViewports(
						Stream.of(
							toStrings((Object[])jsonParserFieldValue)
						).map(
							object -> FragmentViewportSerDes.toDTO(
								(String)object)
						).toArray(
							size -> new FragmentViewport[size]
						));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "htmlProperties")) {
				if (jsonParserFieldValue != null) {
					pageSectionDefinition.setHtmlProperties(
						HtmlPropertiesSerDes.toDTO(
							(String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "indexed")) {
				if (jsonParserFieldValue != null) {
					pageSectionDefinition.setIndexed(
						(Boolean)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "layout")) {
				if (jsonParserFieldValue != null) {
					pageSectionDefinition.setLayout(
						LayoutSerDes.toDTO((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "name")) {
				if (jsonParserFieldValue != null) {
					pageSectionDefinition.setName((String)jsonParserFieldValue);
				}
			}
		}

	}

	private static String _escape(Object object) {
		String string = String.valueOf(object);

		for (String[] strings : BaseJSONParser.JSON_ESCAPE_STRINGS) {
			string = string.replace(strings[0], strings[1]);
		}

		return string;
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
			sb.append("\": ");

			Object value = entry.getValue();

			Class<?> valueClass = value.getClass();

			if (value instanceof Map) {
				sb.append(_toJSON((Map)value));
			}
			else if (valueClass.isArray()) {
				Object[] values = (Object[])value;

				sb.append("[");

				for (int i = 0; i < values.length; i++) {
					sb.append("\"");
					sb.append(_escape(values[i]));
					sb.append("\"");

					if ((i + 1) < values.length) {
						sb.append(", ");
					}
				}

				sb.append("]");
			}
			else if (value instanceof String) {
				sb.append("\"");
				sb.append(_escape(entry.getValue()));
				sb.append("\"");
			}
			else {
				sb.append(String.valueOf(entry.getValue()));
			}

			if (iterator.hasNext()) {
				sb.append(", ");
			}
		}

		sb.append("}");

		return sb.toString();
	}

}
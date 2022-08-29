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

package com.liferay.portal.kernel.util;

import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.io.unsync.UnsyncBufferedReader;
import com.liferay.portal.kernel.io.unsync.UnsyncStringReader;
import com.liferay.portal.kernel.model.CompanyConstants;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.net.URL;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Brian Wing Shun Chan
 * @author Mirco Tamburini
 * @author Brett Randall
 * @author Shuyang Zhou
 */
public class SystemProperties {

	public static final String SYSTEM_ENV_OVERRIDE_PREFIX = "SYSTEM_LIFERAY_";

	public static final String SYSTEM_PROPERTIES_QUIET =
		"system.properties.quiet";

	public static final String SYSTEM_PROPERTIES_SET = "system.properties.set";

	public static final String SYSTEM_PROPERTIES_SET_OVERRIDE =
		"system.properties.set.override";

	public static final String TMP_DIR = "java.io.tmpdir";

	public static void clear(String key) {
		System.clearProperty(key);

		_properties.remove(key);
	}

	public static String get(String key) {
		return get(key, null);
	}

	public static String get(String key, String defaultValue) {
		String value = _properties.get(key);

		if (value == null) {
			value = System.getProperty(key, defaultValue);
		}

		return value;
	}

	public static String[] getArray(String key) {
		return StringUtil.split(get(key));
	}

	public static Properties getProperties() {
		return PropertiesUtil.fromMap(_properties);
	}

	public static Map<String, String> getProperties(
		String prefix, boolean removePrefix) {

		Map<String, String> properties = new HashMap<>();

		for (Map.Entry<String, String> entry : _properties.entrySet()) {
			String key = entry.getKey();

			if (key.startsWith(prefix)) {
				if (removePrefix) {
					key = key.substring(prefix.length());
				}

				properties.put(key, entry.getValue());
			}
		}

		return properties;
	}

	public static void load(ClassLoader classLoader) {
		Properties properties = new Properties();

		List<URL> urls = null;

		if (!GetterUtil.getBoolean(
				System.getProperty(SYSTEM_PROPERTIES_QUIET))) {

			urls = new ArrayList<>();
		}

		// system.properties

		try {
			Enumeration<URL> enumeration = classLoader.getResources(
				"system.properties");

			while (enumeration.hasMoreElements()) {
				URL url = enumeration.nextElement();

				_load(url, properties);

				if (urls != null) {
					urls.add(url);
				}
			}
		}
		catch (IOException ioException) {
			throw new ExceptionInInitializerError(ioException);
		}

		// system-ext.properties

		try {
			Enumeration<URL> enumeration = classLoader.getResources(
				"system-ext.properties");

			while (enumeration.hasMoreElements()) {
				URL url = enumeration.nextElement();

				_load(url, properties);

				if (urls != null) {
					urls.add(url);
				}
			}
		}
		catch (IOException ioException) {
			throw new ExceptionInInitializerError(ioException);
		}

		// Set environment properties

		SystemEnv.setProperties(properties);

		// Set system properties

		if (GetterUtil.getBoolean(
				System.getProperty(SYSTEM_PROPERTIES_SET), true)) {

			boolean systemPropertiesSetOverride = GetterUtil.getBoolean(
				System.getProperty(SYSTEM_PROPERTIES_SET_OVERRIDE), true);

			for (Map.Entry<Object, Object> entry : properties.entrySet()) {
				String key = String.valueOf(entry.getKey());

				if (systemPropertiesSetOverride ||
					Validator.isNull(System.getProperty(key))) {

					System.setProperty(key, String.valueOf(entry.getValue()));
				}
			}

			if (!systemPropertiesSetOverride) {
				Properties systemProperties = System.getProperties();

				for (Map.Entry<Object, Object> entry :
						systemProperties.entrySet()) {

					String key = String.valueOf(entry.getKey());

					if (Validator.isNotNull(properties.get(key))) {
						properties.put(key, entry.getValue());
					}
				}
			}
		}

		// Use a fast concurrent hash map implementation instead of the slower
		// java.util.Properties

		PropertiesUtil.fromProperties(properties, _properties);

		EnvPropertiesUtil.loadEnvOverrides(
			SYSTEM_ENV_OVERRIDE_PREFIX, CompanyConstants.SYSTEM,
			SystemProperties::set);

		if (urls != null) {
			for (URL url : urls) {
				System.out.println("Loading " + url);
			}
		}
	}

	public static void set(String key, String value) {
		System.setProperty(key, value);

		_properties.put(key, value);
	}

	private static void _load(URL url, Properties properties)
		throws IOException {

		try (InputStream inputStream = url.openStream();
			InputStreamReader inputStreamReader = new InputStreamReader(
				inputStream);
			UnsyncBufferedReader unsyncBufferedReader =
				new UnsyncBufferedReader(inputStreamReader)) {

			String line = null;
			StringBundler sb = new StringBundler();

			while ((line = unsyncBufferedReader.readLine()) != null) {
				line = line.trim();

				// Empty line, Comment line or "\"

				if (line.isEmpty() || (line.charAt(0) == CharPool.POUND) ||
					line.equals(StringPool.BACK_SLASH)) {

					continue;
				}

				sb.append(line);
				sb.append(StringPool.NEW_LINE);
			}

			if (sb.index() != 0) {
				try (UnsyncStringReader unsyncStringReader =
						new UnsyncStringReader(sb.toString())) {

					properties.load(unsyncStringReader);
				}
			}
		}
	}

	private static final Map<String, String> _properties =
		new ConcurrentHashMap<>();

	static {
		Thread currentThread = Thread.currentThread();

		ClassLoader classLoader = currentThread.getContextClassLoader();

		load(classLoader);
	}

}
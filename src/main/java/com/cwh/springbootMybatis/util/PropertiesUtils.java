/*
package com.cwh.springbootMybatis.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;

public class PropertiesUtils {
	private static final Properties global = loadProperties("manager.properties");
	public static String getproperties(String key,String defaultValue)
	{
		String ret = global.getProperty(key);
		return  StringUtils.isBlank(ret) ? defaultValue :ret;
	}
	private static Properties loadProperties(String resources) {

		// 使用InputStream得到一个资源文件

		InputStream inputstream = PropertiesUtils.class.getClassLoader().getResourceAsStream(resources);

		// new 一个Properties

		Properties properties = new Properties();

		try {

			// 加载配置文件

			properties.load(inputstream);

			return properties;

		} catch (IOException e) {

			throw new RuntimeException(e);//NOSONAR

		} finally {

				try {
					inputstream.close();
				} catch (IOException e) {//NOSONAR
					e.printStackTrace();
				}

		}
	}
}
*/

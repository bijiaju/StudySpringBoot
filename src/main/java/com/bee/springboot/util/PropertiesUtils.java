
package com.bee.springboot.util;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class PropertiesUtils {
	private static final Properties global = loadProperties("manager.properties");

	/**
	 *  如果没有key，就获取默认值
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static String getproperties(String key,String defaultValue)
	{
		String ret = global.getProperty(key);
		return  StringUtils.isBlank(ret) ? defaultValue :ret;
	}

	/*private static Properties loadProperties(String... resources){
		Properties properties = new Properties();
		for (String resource : resources) {
			loadProperties(properties,resource);
		}
		return properties;
	}*/

	/**
	 * 加载Properties资源
	 * @param resources
	 * @return
	 */
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

  /*  static Properties properties = new Properties();

    static{
        try {
            Class clazz = DBUtil.class;
            InputStreamReader fileReader =
                    new InputStreamReader(clazz.getResourceAsStream("/db.properties"));
            properties.load(fileReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String getUserName(){
        String userName =properties.getProperty("userName");
        return userName;
    }

    public static String getPassword(){
        return	properties.getProperty("password");
    }
    public static void main(String[] args) {
        System.out.println("用户名："+ getUserName());
        System.out.println("密码: "+  getPassword());
    }*/

}


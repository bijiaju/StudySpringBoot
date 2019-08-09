package com.bee.springboot.entity;

import com.bee.springboot.util.PropertiesUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author weichunhe
 * created at 2019/1/4
 */
public class ConfigurableProperties {
    static Logger LOG = LoggerFactory.getLogger(ConfigurableProperties.class);

    //redis configuration property names
    public static final String
            REDIS_NODE_PROPERTY = "redis.nodes", REDIS_DEFAULT_TTL_KEY = "redis.ttl.seconds";



    public static Integer getValue(String propertyName, Integer defaultValue) {
        String config = PropertiesUtils.getproperties(propertyName, defaultValue + "");
        try {
            return Integer.parseInt(config);
        } catch (Exception e) {
            LOG.warn("Redis use default value [{}] for [{}],because parse configuration[{}={}] failed.", defaultValue, propertyName, propertyName, config);
        }
        return defaultValue;
    }
}

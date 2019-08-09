package com.bee.springboot.util.redis;

import com.bee.springboot.entity.ConfigurableProperties;
import com.bee.springboot.util.PropertiesUtils;
import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisCommands;

import java.util.HashSet;
import java.util.Set;

/**
 * @author bee
 * created at 2019/1/4
 */
public class RedisUtil {


    private static JedisCluster cluster = null;

    private static Jedis single = null;

    private static int TIMEOUT_SECONDS = 30;
    //one day
    public static int DEFAULT_TTL_SECONDS = 24 * 60 * 60;//redis使用ttl查看key(键)的生存时间

    public static int DEFAULT_TTL_SECONDS_MONTH = 30*24 * 60 * 60;

    static {
        DEFAULT_TTL_SECONDS = ConfigurableProperties.getValue(ConfigurableProperties.REDIS_DEFAULT_TTL_KEY, DEFAULT_TTL_SECONDS);
        Set<HostAndPort> nodes = getNodes();
        if (nodes.size() == 1) {//单节点操作
            //get the only one host and port
            HostAndPort hostAndPort = nodes.toArray(new HostAndPort[1])[0];
            single = new Jedis(hostAndPort.getHost(), hostAndPort.getPort(), TIMEOUT_SECONDS);
        } else {//集群Redis
            cluster = new JedisCluster(nodes, TIMEOUT_SECONDS, nodes.size());
        }
    }

    private RedisUtil() {
    }

    public static JedisCommands connect() {
        if (single != null) {
            return single;
        } else {
            return cluster;
        }
    }

    /**
     * 获取集群的端口和地址
     * @return
     */
    private static Set<HostAndPort> getNodes() {
        String config = PropertiesUtils.getproperties(ConfigurableProperties.REDIS_NODE_PROPERTY, "127.0.0.1:6379");
        String[] addresses = config.replaceAll(" +", "").split(",");
        Set<HostAndPort> hostAndPorts = new HashSet<>();
        for (String address : addresses) {
            if (StringUtils.isNotEmpty(address)) {
                System.out.println("..........................读上来的地址是：............."+address);
                hostAndPorts.add(HostAndPort.parseString(address));
            }
        }
        return hostAndPorts;
    }
}

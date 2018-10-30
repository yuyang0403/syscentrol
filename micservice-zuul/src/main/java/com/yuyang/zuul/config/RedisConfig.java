package com.yuyang.zuul.config;

import com.yuyang.common.cache.RedisCache;
import com.yuyang.common.cache.RedisClusterCacheImpl;
import com.yuyang.common.cache.RedisSentinelCacheImpl;
import com.yuyang.common.cache.RedisSingleCacheImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import redis.clients.jedis.*;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * RedisConfig.java
 *
 * @Description redis服务配置
 **/
@Configuration
public class RedisConfig {
    /**
     * Redis单体配置
     */
    @Value("${redis.single.ip}")
    private String redisIp;

    @Value("${redis.single.port}")
    private Integer redisPort;

    /**
     * Redis集群配置
     */
    @Value("${redis.cluster.address}")
    private String address;

    @Value("${redis.cluster.maxRedirections}")
    private Integer maxRedirections;

    /**
     * Redis哨兵配置
     */
    @Value("${redis.sentinel.master}")
    private String masterName;

    @Value("${redis.sentinel.nodes}")
    private String sentinelNodes;

    /**
     * Redis通用配置
     */
    @Value("${redis.max-active}")
    private Integer redisMaxActive;

    @Value("${redis.max-idle}")
    private Integer redisMaxIdle;

    @Value("${redis.max-wait}")
    private Long redisMaxWait;

    @Value("${redis.testOnBorrow}")
    private Boolean redisTestOnBorrow;

    @Value("${redis.timeout}")
    private Integer redisTimeout;
    /**
     * 注入Redis单体实例
     * @return
     */
    @Bean
    public RedisCache redisCache(){
        return new RedisSingleCacheImpl(getPool());
    }

    /**
     * 注入Redis集群实例
     * @return
     */
    // @Bean
    public RedisCache redisClusterCache(){
        return new RedisClusterCacheImpl(getJedisCluster());
    }

    /**
     * 注入Redis哨兵实例
     * @return
     */
    //@Bean
    public RedisCache redisSentinelCache() {
        return new RedisSentinelCacheImpl(getSentinelPool());
    }

    private JedisPool getPool(){
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(redisMaxActive);
        config.setMaxIdle(redisMaxIdle);
        config.setMaxWaitMillis(redisMaxWait);
        config.setTestOnBorrow(redisTestOnBorrow);
        return new JedisPool(config,redisIp,redisPort,redisTimeout);
    }



    private JedisCluster getJedisCluster(){
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(redisMaxActive);
        config.setMaxIdle(redisMaxIdle);
        config.setMaxWaitMillis(redisMaxWait);
        config.setTestOnBorrow(redisTestOnBorrow);
        return new JedisCluster(parseHostAndPort(), redisTimeout, maxRedirections, config);
    }

    private Set<HostAndPort> parseHostAndPort() {
        final Pattern p = Pattern.compile("^.+[:]\\d{1,5}\\s*$");
        try {
            Set<HostAndPort> haps = new HashSet<>();
            if(null == address || address.length() <= 0){
                return null;
            }
            String[] adress = address.split(",");
            for (String cluster:
                    adress) {
                boolean isIpPort = p.matcher(cluster).matches();
                if (!isIpPort) {
                    throw new IllegalArgumentException("ip 或 port 不合法");
                }
                String[] ipAndPort = cluster.split(":");
                HostAndPort hap = new HostAndPort(ipAndPort[0].trim(), Integer.parseInt(ipAndPort[1].trim()));
                haps.add(hap);
            }
            return haps;
        } catch (IllegalArgumentException ex) {
            throw ex;
        }
    }

    private JedisSentinelPool getSentinelPool() {
        Set<String> sentinels = StringUtils.commaDelimitedListToSet(sentinelNodes);
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(redisMaxIdle);
        config.setMaxWaitMillis(redisMaxWait);
        config.setMaxTotal(redisMaxActive);
        config.setTestOnBorrow(redisTestOnBorrow);
        return new JedisSentinelPool(masterName, sentinels, config, redisTimeout);
    }

}
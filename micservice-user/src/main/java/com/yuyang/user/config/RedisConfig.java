package com.yuyang.user.config;

import com.yuyang.common.cache.RedisCache;
import com.yuyang.common.cache.RedisSingleCacheImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

import java.util.Set;

/**
 * RedisConfig.java
 *
 * @Description redis服务配置
 **/
@Configuration
public class RedisConfig {

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

    @Value("${redis.password}")
    private String redisPassword;
    /**
     * 注入Redis哨兵实例
     * @return
     */
    @Bean
    public RedisCache redisSentinelCache() {
        return new RedisSingleCacheImpl(getSentinelPool());
    }
    private JedisSentinelPool getSentinelPool() {
        Set<String> sentinels = StringUtils.commaDelimitedListToSet(sentinelNodes);
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(redisMaxIdle);
        config.setMaxWaitMillis(redisMaxWait);
        config.setMaxTotal(redisMaxActive);
        config.setTestOnBorrow(redisTestOnBorrow);
        return new JedisSentinelPool(masterName, sentinels, config, redisTimeout, redisPassword);
    }

}
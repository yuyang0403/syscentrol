package com.yuyang.common.cache;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.time.Duration;

/**
 * @author yuyang
 * @create 2018/7/24 16:04
 * @desc
 **/
public class CacheManager {
    private static RedisCache redisCache = null;

    /**
     * 获取redis
     *
     * @return
     */
    public synchronized static RedisCache getSingletonCache() {
        if (redisCache != null) {
            return redisCache;
        }
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName("192.168.198.129");
        redisStandaloneConfiguration.setPort(6379);
        JedisClientConfiguration.JedisClientConfigurationBuilder jedisClientConfiguration = JedisClientConfiguration.builder();
        jedisClientConfiguration.connectTimeout(Duration.ofMillis(10000));
        JedisConnectionFactory factory = new JedisConnectionFactory(redisStandaloneConfiguration,
                jedisClientConfiguration.build());
        StringRedisTemplate srt = new StringRedisTemplate(factory);
        RedisCache rc = new RedisCacheImpl(srt);
        redisCache = rc;
        return redisCache;
    }
}

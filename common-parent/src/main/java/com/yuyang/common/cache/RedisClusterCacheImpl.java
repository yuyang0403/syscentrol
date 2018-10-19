package com.yuyang.common.cache;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.*;

import java.io.Closeable;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yuyang
 * @create 2018/6/21 15:49
 * @desc
 **/
public class RedisClusterCacheImpl implements RedisCache<String, Object> {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static AtomicInteger count = new AtomicInteger(0);
    private JedisCluster jedisCluster;

    public RedisClusterCacheImpl(JedisCluster jedisCluster) {
        this.jedisCluster = jedisCluster;
    }

    @Override
    public Set getKeys(String keyClue, String db) {
        logger.debug("Start getting keys...");
        Set<String> keys = new TreeSet<>();
        Map<String, JedisPool> clusterNodes = jedisCluster.getClusterNodes();
        for (Map.Entry<String, JedisPool> k : clusterNodes.entrySet()) {
            logger.debug("Getting keys from: " + k.getKey());
            JedisPool jp = k.getValue();
            Jedis connection = jp.getResource();
            try {
                keys.addAll(connection.keys(keyClue));
            } catch (Exception e) {
                logger.error("Getting keys error: {}", e);
            } finally {
                logger.debug("Connection closed.");
                connection.close();
            }
        }
        logger.debug("Keys gotten!");
        return keys;
    }

    @Override
    public Boolean exists(String key) {
        return jedisCluster.exists(key);
    }

    @Override
    public Boolean exists(String key, String db) {
        return jedisCluster.exists(key);
    }

    @Override
    public Long del(String key) {
        return jedisCluster.del(key);
    }

    @Override
    public void del(String key, String db) {
        jedisCluster.del(key);
    }

    @Override
    public void delByte(String key) {
        jedisCluster.del(key.getBytes());
    }

    @Override
    public void delByte(String key, String db) {
        delByte(key);
    }

    @Override
    public void set(String key, String value) {
        jedisCluster.set(key, value);
    }

    @Override
    public void set(String key, String value, Integer expiration) {
        jedisCluster.set(key, value);
        jedisCluster.expire(key, expiration);
    }

    @Override
    public void set(String key, String value, String db) {
        set(key, value);
    }

    @Override
    public void setByte(String key, byte[] value) {
        jedisCluster.set(key.getBytes(), value);
    }

    @Override
    public void setByte(String key, byte[] value, String db) {
        setByte(key, value);
    }

    @Override
    public String lpop(String key) {
        String ret = jedisCluster.lpop(key);
        if ("nil".equals(ret)) {
            ret = StringUtils.EMPTY;
        }
        return ret;
    }

    @Override
    public String get(String key) {
        return jedisCluster.get(key);
    }

    @Override
    public String get(String key, String db) {
        return get(key);
    }

    @Override
    public byte[] getByte(String key) {
        return jedisCluster.get(key.getBytes());
    }

    @Override
    public byte[] getByte(String key, String db) {
        return getByte(key);
    }

    @Override
    public void mset(String... keyvalues) {
        jedisCluster.mset(keyvalues);
    }

    @Override
    public List mget(String... keys) {
        return jedisCluster.mget(keys);
    }

    @Override
    public void expire(String key, int expire) {
        jedisCluster.expire(key, expire);
    }

    @Override
    public void expire(String key, String db, int timestamp) {
        expire(key, timestamp);
    }

    @Override
    public long ttl(String key, String db) {
        return jedisCluster.ttl(key);
    }

    @Override
    public void expireByte(String key, int expire) {
        jedisCluster.expire(key.getBytes(), expire);
    }

    @Override
    public void append(String key, String value) {
        jedisCluster.append(key, value);
    }

    @Override
    public void hset(String key, String field, String value) {
        jedisCluster.hset(key, field, value);
    }

    @Override
    public String hget(String key, String field) {
        return jedisCluster.hget(key, field);
    }

    @Override
    public void hmset(String key, Map map) {
        jedisCluster.hmset(key, map);
    }

    @Override
    public List hmget(String key, String... fields) {
        return jedisCluster.hmget(key, fields);
    }

    @Override
    public Map<String, String> hgetAll(String key) {
        return jedisCluster.hgetAll(key);
    }

    @Override
    public void lpush(String key, String value) {
        jedisCluster.lpush(key, value);
    }

    @Override
    public void lpush(String key, String value, String db) {
        lpush(key, value);
    }

    @Override
    public void rpush(String key, String value, String db) {
        jedisCluster.rpush(key, value);
    }

    @Override
    public void lpushByte(String key, byte[][] value) {
        jedisCluster.lpush(key.getBytes(), value);
    }

    @Override
    public void lpushByte(String key, byte[] value) {
        jedisCluster.lpush(key.getBytes(), value);
    }

    @Override
    public void lpushByte(String key, byte[] value, String db) {
        lpushByte(key, value);
    }

    @Override
    public List lrange(String key, int start, int stop) {
        return jedisCluster.lrange(key, start, stop);
    }

    @Override
    public List lrange(String key, int start, int stop, String db) {
        return jedisCluster.lrange(key, start, stop);
    }

    @Override
    public List lrangeByte(String key, int start, int stop) {
        return jedisCluster.lrange(key.getBytes(), start, stop);
    }

    @Override
    public List lrangeByte(String key, int start, int stop, String db) {
        return lrange(key, start, stop);
    }

    @Override
    public void sadd(String key, String value) {
        jedisCluster.sadd(key, value);
    }

    @Override
    public void srem(String key, String value) {
        jedisCluster.srem(key, value);
    }

    @Override
    public Set smembers(String key) {
        return jedisCluster.smembers(key);
    }

    @Override
    public void hmset(String key, Map map, String db) {
        jedisCluster.hmset(key, map);
    }

    @Override
    public List<String> brpop(int timeout, String db, String... keys) {
        return jedisCluster.brpop(timeout, keys);
    }

    @Override
    public List<byte[]> brpop(int timeout, byte[]... keys) {
        return jedisCluster.brpop(timeout, keys);
    }

    @Override
    public Long lrem(String key, long cnt, String value, String dbs) {
        return jedisCluster.lrem(key, cnt, value);
    }

    @Override
    public String getset(String key, String newValue, String db) {
        String oldValue = jedisCluster.getSet(key, newValue);
        return oldValue;
    }

    @Override
    public Long setnx(String key, String value, String db) {
        return jedisCluster.setnx(key, value);
    }

    @Override
    public Long hincrBy(String key, String field, Integer value, String db) {
        return jedisCluster.hincrBy(key, field, value);
    }

    @Override
    public Long incrBy(String key, Integer value, String db) {
        return jedisCluster.incrBy(key, value);
    }

    @Override
    public Boolean hexists(String key, String field, String db) {
        return jedisCluster.hexists(key, field);
    }

    @Override
    public void hset(String key, String field, String value, String db) {
        jedisCluster.hset(key, field, value);
    }

    @Override
    public void hdel(String key, String fields, String db) {
        jedisCluster.hdel(key, fields);
    }

    @Override
    public Long zadd(String key, double score, String member) {
        return jedisCluster.zadd(key, score, member);
    }

    @Override
    public Long zrem(String key, String... member) {
        return jedisCluster.zrem(key, member);
    }

    @Override
    public Double zscore(String key, String member) {
        return jedisCluster.zscore(key, member);
    }

    @Override
    public Long zrank(String key, String member) {
        return jedisCluster.zrank(key, member);
    }

    @Override
    public Long zrevrank(String key, String member) {
        return jedisCluster.zrevrank(key, member);
    }

    @Override
    public Set<Tuple> zrevrangeWithScores(String key, Long start, Long end) {
        return jedisCluster.zrevrangeWithScores(key, start, end);
    }

    @Override
    public Object eval(String script, List<String> keys, List<String> params) {
        return jedisCluster.eval(script, keys, params);
    }

    @Override
    public Long incrBy4Expire(String key, Integer value, int second, int db) {
        Long ret = jedisCluster.incrBy(key, value);
        jedisCluster.expire(key, second);
        return ret;
    }

    @Override
    public void hsetObject(String key, Object field, Object value) {
        jedisCluster.hset(SerializationUtils.serialize(key), SerializationUtils.serialize(field),
                SerializationUtils.serialize(value));
    }

    @Override
    public Object hgetObject(String key, Object field) {
        return SerializationUtils
                .deserialize(jedisCluster.hget(SerializationUtils.serialize(key), SerializationUtils.serialize(field)));

    }

    @Override
    public Map<Object, Object> hgetAllObject(String key) {

        Map<Object, Object> objectMap = null;
        Map<byte[], byte[]> byteMap = jedisCluster.hgetAll(SerializationUtils.serialize(key));
        if (null != byteMap && byteMap.size() > 0) {
            objectMap = new HashMap<Object, Object>();
            for (Map.Entry<byte[], byte[]> element : byteMap.entrySet()) {
                byte[] byteKey = element.getKey();
                byte[] byteValue = element.getValue();
                objectMap.put(SerializationUtils.deserialize(byteKey), SerializationUtils.deserialize(byteValue));
            }
        }
        return objectMap;
    }

    @Override
    public void hdelObject(String key, Object fields) {
        jedisCluster.hdel(SerializationUtils.serialize(key), SerializationUtils.serialize(fields));
    }

    @Override
    public void hsetEx(String key, String field, String value, int seconds) {

    }

    @Override
    public Long hincrBy(String key, String field, Integer value, int db, int expireTime) {
        Long ret = jedisCluster.hincrBy(key, field, value);
        jedisCluster.expire(key, expireTime);
        return ret;
    }

    @Override
    public Double zincrby(String key, double score, String member) {
        try {
            return jedisCluster.zincrby(key, score, member);
        } catch (Exception e) {
            logger.warn(e.getMessage());
        }
        return null;
    }

    @Override
    public void expireAt(String key, long timestamp) {
        jedisCluster.expireAt(key, timestamp);
    }

    @Override
    public Set<?> getKeys() {
        return null;
    }

    @Override
    public Set<?> getKeys(String db) {
        return null;
    }
}

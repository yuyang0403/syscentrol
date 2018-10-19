package com.yuyang.common.cache;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.BinaryJedis;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.jedis.Tuple;

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
public class RedisSentinelCacheImpl implements RedisCache<String, Object> {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    private static AtomicInteger count = new AtomicInteger(0);
    private JedisSentinelPool sentinelPool;

    public RedisSentinelCacheImpl(JedisSentinelPool sentinelPool) {
        this.sentinelPool = sentinelPool;
    }

    @Override
    public void hdel(String key, String fields, String db) {
        Jedis jedis = null;
        try {
            jedis = sentinelPool.getResource();
            jedis.hdel(key, fields);
            writeLog();
        } catch (Exception e) {
            log.warn(e.getMessage());
        } finally {
            close(jedis);
        }

    }

    @Override
    public void hset(String key, String field, String value, String db) {
        Jedis jedis = null;
        try {
            jedis = sentinelPool.getResource();
            jedis.hset(key, field, value);
            writeLog();
        } catch (Exception e) {
            log.warn(e.getMessage());
        } finally {
            close(jedis);
        }
    }

    @Override
    public Boolean hexists(String key, String field, String db) {
        Jedis jedis = null;
        try {
            jedis = sentinelPool.getResource();
            return jedis.hexists(key, field);
        } catch (Exception e) {
            log.warn(e.getMessage());
        } finally {
            close(jedis);
        }
        return false;
    }

    /**
     * 计数器
     */
    @Override
    public Long hincrBy(String key, String field, Integer value, String db) {
        Jedis jedis = null;
        try {
            jedis = sentinelPool.getResource();
            writeLog();
            return jedis.hincrBy(key, field, value);
        } catch (Exception e) {
            log.warn(e.getMessage());
        } finally {
            close(jedis);
        }
        return null;
    }


    @Override
    public Long hincrBy(String key, String field, Integer value, int db, int expireTime) {
        Jedis jedis = null;
        Long rval = null;
        try {
            jedis = sentinelPool.getResource();
            jedis.select(db);
            rval = jedis.hincrBy(key, field, value);
            jedis.expire(key, expireTime);
            writeLog();
            return rval;
        } catch (Exception e) {
            log.warn(e.getMessage());
        } finally {
            close(jedis);
        }
        return rval;
    }

    /**
     * 设置key锁
     */
    @Override
    public Long setnx(String key, String value, String db) {
        Jedis jedis = null;
        try {
            jedis = sentinelPool.getResource();
            writeLog();
            return jedis.setnx(key, value);
        } catch (Exception e) {
            log.warn(e.getMessage());
        } finally {
            close(jedis);
        }
        return null;
    }

    /**
     * 列出所有的key
     *
     * @return
     */
    @Override
    public Set<String> getKeys() {
        Jedis jedis = null;
        try {
            jedis = sentinelPool.getResource();
            return jedis.keys("*");
        } catch (Exception e) {
            log.warn(e.getMessage());
        } finally {
            close(jedis);
        }
        return null;
    }

    /**
     * 检查给定key是否存在
     *
     * @param key
     * @return
     */
    @Override
    public Boolean exists(String key) {
        Jedis jedis = null;
        try {
            jedis = sentinelPool.getResource();
            return jedis.exists(key);
        } catch (Exception e) {
            log.warn(e.getMessage());
        } finally {
            close(jedis);
        }
        return false;
    }

    @Override
    public void delByte(String key) {
        Jedis jedis = null;
        try {
            jedis = sentinelPool.getResource();
            jedis.del(key.getBytes());
            writeLog();
        } catch (Exception e) {
            log.warn(e.getMessage());
        } finally {
            close(jedis);
        }

    }

    /**
     * 简单的字符串设置
     *
     * @param key
     * @param value
     */
    @Override
    public void set(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = sentinelPool.getResource();
            jedis.set(key, value);
            writeLog();
        } catch (Exception e) {
            log.warn(e.getMessage());
        } finally {
            close(jedis);
        }

    }

    @Override
    public void setByte(String key, byte[] value) {
        Jedis jedis = null;
        try {
            jedis = sentinelPool.getResource();
            jedis.set(key.getBytes(), value);
            writeLog();
        } catch (Exception e) {
            log.warn(e.getMessage());
        } finally {
            close(jedis);
        }

    }

    /**
     * 返回key所关联的字符串值
     *
     * @param key
     * @return
     */
    @Override
    public String get(String key) {
        Jedis jedis = null;
        try {
            jedis = sentinelPool.getResource();
            return jedis.get(key);
        } catch (Exception e) {
            log.warn(e.getMessage());
        } finally {
            close(jedis);
        }
        return StringUtils.EMPTY;
    }

    @Override
    public byte[] getByte(String key) {
        Jedis jedis = null;
        try {
            jedis = sentinelPool.getResource();
            return jedis.get(key.getBytes());
        } catch (Exception e) {
            log.warn(e.getMessage());
        } finally {
            close(jedis);
        }
        return null;
    }

    /**
     * 同时设置一个或多个key-value对 ("haha", "111", "xixi", "222")
     *
     * @param keyvalues
     */
    @Override
    public void mset(String... keyvalues) {
        Jedis jedis = null;
        try {
            jedis = sentinelPool.getResource();
            jedis.mset(keyvalues);
            writeLog();
        } catch (Exception e) {
            log.warn(e.getMessage());
        } finally {
            close(jedis);
        }

    }

    /**
     * 返回所有(一个或多个)给定key的值
     *
     * @param keys
     * @return
     */
    @Override
    public List<String> mget(String... keys) {
        Jedis jedis = null;
        try {
            jedis = sentinelPool.getResource();
            return jedis.mget(keys);
        } catch (Exception e) {
            log.warn(e.getMessage());
        } finally {
            close(jedis);
        }
        return new ArrayList<>();
    }

    /**
     * key seconds 为给定key设置生存时间。当key过期时，它会被自动删除。
     *
     * @param key
     * @param expire
     */
    @Override
    public void expire(String key, int expire) {
        Jedis jedis = null;
        try {
            jedis = sentinelPool.getResource();
            jedis.expire(key, expire);
        } catch (Exception e) {
            log.warn(e.getMessage());
        } finally {
            close(jedis);
        }
    }

    @Override
    public void expireByte(String key, int expire) {
        Jedis jedis = null;
        try {
            jedis = sentinelPool.getResource();
            jedis.expire(key.getBytes(), expire);
        } catch (Exception e) {
            log.warn(e.getMessage());
        } finally {
            close(jedis);
        }
    }

    /**
     * 如果key已经存在并且是一个字符串，APPEND命令将value追加到key原来的值之后。
     *
     * @param key
     * @param value
     */
    @Override
    public void append(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = sentinelPool.getResource();
            jedis.append(key, value);
            writeLog();
        } catch (Exception e) {
            log.warn(e.getMessage());
        } finally {
            close(jedis);
        }
    }

    /**
     * HASH操作 将哈希表key中的域field的值设为value。
     *
     * @param key
     * @param field
     * @param value
     */
    @Override
    public void hset(String key, String field, String value) {
        Jedis jedis = null;
        try {
            jedis = sentinelPool.getResource();
            jedis.hset(key, field, value);
            writeLog();
        } catch (Exception e) {
            log.warn(e.getMessage());
        } finally {
            close(jedis);
        }
    }

    /**
     * HASH操作 将哈希表key中的域field的值设为value,并设置自动失效时间。
     *
     * @param key
     * @param field
     * @param value
     */
    @Override
    public void hsetEx(String key, String field, String value, int seconds) {
        Jedis jedis = null;
        try {
            jedis = sentinelPool.getResource();
            jedis.hset(key, field, value);
            jedis.expire(key, seconds);
            writeLog();
        } catch (Exception e) {
            log.warn(e.getMessage());
        } finally {
            close(jedis);
        }
    }

    /**
     * HASH操作 返回哈希表key中给定域field的值。
     *
     * @param key
     * @param field
     * @return
     */
    @Override
    public String hget(String key, String field) {
        Jedis jedis = null;
        try {
            jedis = sentinelPool.getResource();
            return jedis.hget(key, field);
        } catch (Exception e) {
            log.warn(e.getMessage());
        } finally {
            close(jedis);
        }
        return StringUtils.EMPTY;
    }

    /**
     * HASH操作 value(域-值)对设置到哈希表key中。
     *
     * @param key
     * @param map
     */
    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void hmset(String key, Map map) {
        Jedis jedis = null;
        try {
            jedis = sentinelPool.getResource();
            jedis.hmset(key, map);
            writeLog();
        } catch (Exception e) {
            log.warn(e.getMessage());
            close(jedis);
        } finally {
            close(jedis);
        }
    }

    /**
     * HASH操作 返回哈希表key中，一个或多个给定域的值。
     *
     * @param key
     * @param fields
     * @return
     */
    @Override
    public List<String> hmget(String key, String... fields) {
        Jedis jedis = null;
        try {
            jedis = sentinelPool.getResource();
            return jedis.hmget(key, fields);
        } catch (Exception e) {
            log.warn(e.getMessage());
        } finally {
            close(jedis);
        }
        return new ArrayList<>();
    }

    /**
     * HASH操作 返回哈希表key中，所有的域和值。
     *
     * @param key
     * @return
     */
    @Override
    public Map<String, String> hgetAll(String key) {
        Jedis jedis = null;
        try {
            jedis = sentinelPool.getResource();
            return jedis.hgetAll(key);
        } catch (Exception e) {
            log.warn(e.getMessage());
        } finally {
            close(jedis);
        }
        return null;
    }

    /**
     * LIST操作 LPUSH key value [value ...]将值value插入到列表key的表头。
     *
     * @param key
     * @param value
     */
    @Override
    public void lpush(String key, String value, String db) {
        Jedis jedis = null;
        try {
            jedis = sentinelPool.getResource();
            jedis.lpush(key, value);
            writeLog();
        } catch (Exception e) {
            log.warn(e.getMessage());
        } finally {
            close(jedis);
        }
    }

    /**
     * LIST操作 LPUSH key value [value ...]将值value插入到列表key的表头。
     *
     * @param key
     * @param value
     */
    @Override
    public void lpush(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = sentinelPool.getResource();
            jedis.lpush(key, value);
            writeLog();
        } catch (Exception e) {
            log.warn(e.getMessage());
        } finally {
            close(jedis);
        }
    }

    @Override
    public void rpush(String key, String value, String db) {
        Jedis jedis = null;
        try {
            jedis = sentinelPool.getResource();
            jedis.rpush(key, value);
            writeLog();
        } catch (Exception e) {
            log.warn(e.getMessage());
        } finally {
            close(jedis);
        }
    }

    @Override
    public void lpushByte(String key, byte[] value) {
        Jedis jedis = null;
        try {
            jedis = sentinelPool.getResource();
            jedis.lpush(key.getBytes(), value);
            writeLog();
        } catch (Exception e) {
            log.warn(e.getMessage());
        } finally {
            close(jedis);
        }

    }

    @Override
    public void lpushByte(String key, byte[] value, String db) {
        Jedis jedis = null;
        try {
            jedis = sentinelPool.getResource();
            jedis.lpush(key.getBytes(), value);
            writeLog();
        } catch (Exception e) {
            log.warn(e.getMessage());
        } finally {
            close(jedis);
        }

    }

    /**
     * LIST操作 top返回列表key中指定区间内的元素，区间以偏移量start和stop指定。 下标(index)参数start和stop都以0为底，也就是说，以0表示列表的第一个元素，以1表示列表的第二个元素，以此类推。
     * 你也可以使用负数下标，以-1表示列表的最后一个元素，-2表示列表的倒数第二个元素，以此类推。
     *
     * @param key
     * @param start
     * @param stop
     * @return
     */
    @Override
    public List<String> lrange(String key, int start, int stop, String db) {
        Jedis jedis = null;
        try {
            jedis = sentinelPool.getResource();
            return jedis.lrange(key, start, stop);
        } catch (Exception e) {
            log.warn(e.getMessage());
        } finally {
            close(jedis);
        }
        return new ArrayList<>();
    }

    @Override
    public List<String> lrange(String key, int start, int stop) {
        Jedis jedis = null;
        try {
            jedis = sentinelPool.getResource();
            return jedis.lrange(key, start, stop);
        } catch (Exception e) {
            log.warn(e.getMessage());
        } finally {
            close(jedis);
        }
        return new ArrayList<>();
    }

    @Override
    public String lpop(String key) {
        Jedis jedis = null;
        String ret = StringUtils.EMPTY;
        try {
            jedis = sentinelPool.getResource();
            ret = jedis.lpop(key);
            if (ret.equals("nil")) {
                ret = StringUtils.EMPTY;
            }
            writeLog();
        } catch (Exception e) {
            log.warn(e.getMessage());
            return StringUtils.EMPTY;
        } finally {
            close(jedis);
        }
        return ret;
    }

    @Override
    public List<String> brpop(int timeout, String db, String... key) {
        Jedis jedis = null;
        List<String> ret = new ArrayList<>();
        try {
            jedis = sentinelPool.getResource();
            ret = jedis.brpop(timeout, key);
            writeLog();
        } catch (Exception e) {
            log.warn(e.getMessage());
            return new ArrayList<>();
        } finally {
            close(jedis);
        }
        return ret;
    }

    @Override
    public List<byte[]> brpop(int timeout, byte[]... keys) {
        Jedis jedis = null;
        List<byte[]> ret = new ArrayList<>();
        try {
            jedis = sentinelPool.getResource();
            ret = jedis.brpop(timeout, keys);
            writeLog();
        } catch (Exception e) {
            log.warn(e.getMessage());
            return new ArrayList<>();
        } finally {
            close(jedis);
        }
        return ret;
    }

    @Override
    public List<byte[]> lrangeByte(String key, int start, int stop) {
        Jedis jedis = null;
        try {
            jedis = sentinelPool.getResource();
            return jedis.lrange(key.getBytes(), start, stop);
        } catch (Exception e) {
            log.warn(e.getMessage());
        } finally {
            close(jedis);
        }
        return new ArrayList<>();
    }

    @Override
    public List<byte[]> lrangeByte(String key, int start, int stop, String db) {
        Jedis jedis = null;
        try {
            jedis = sentinelPool.getResource();
            return jedis.lrange(key.getBytes(), start, stop);
        } catch (Exception e) {
            log.warn(e.getMessage());
        } finally {
            close(jedis);
        }
        return new ArrayList<>();
    }

    /**
     * SET 操作 将member元素加入到集合key当中。
     *
     * @param key
     * @param value
     */
    @Override
    public void sadd(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = sentinelPool.getResource();
            jedis.sadd(key, value);
            writeLog();
        } catch (Exception e) {
            log.warn(e.getMessage());
        } finally {
            close(jedis);
        }

    }

    /**
     * SET 操作 SREM key member移除集合中的member元素。
     *
     * @param key
     * @param value
     */
    @Override
    public void srem(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = sentinelPool.getResource();
            jedis.srem(key, value);
            writeLog();
        } catch (Exception e) {
            log.warn(e.getMessage());
        } finally {
            close(jedis);
        }

    }

    /**
     * SET 操作 SMEMBERS key返回集合key中的所有成员。
     *
     * @param key
     * @return
     */
    @Override
    public Set<String> smembers(String key) {
        Jedis jedis = null;
        try {
            jedis = sentinelPool.getResource();
            return jedis.smembers(key);
        } catch (Exception e) {
            log.warn(e.getMessage());
        } finally {
            close(jedis);
        }
        return new HashSet<>();
    }

    @Override
    public Set<String> getKeys(String db) {
        Jedis jedis = null;
        try {
            jedis = sentinelPool.getResource();
            return jedis.keys("*");
        } catch (Exception e) {
            log.warn(e.getMessage());
        } finally {
            close(jedis);
        }
        return new HashSet<>();
    }

    @Override
    public Boolean exists(String key, String db) {
        Jedis jedis = null;
        try {
            jedis = sentinelPool.getResource();
            return jedis.exists(key);
        } catch (Exception e) {
            log.warn(e.getMessage());
        } finally {
            close(jedis);
        }
        return false;
    }

    @Override
    public void set(String key, String value, String db) {
        Jedis jedis = null;
        try {
            jedis = sentinelPool.getResource();
            jedis.set(key, value);
            writeLog();
        } catch (Exception e) {
            log.warn(e.getMessage());
        } finally {
            close(jedis);
        }
    }

    @Override
    public String get(String key, String db) {
        Jedis jedis = null;
        try {
            jedis = sentinelPool.getResource();
            return jedis.get(key);
        } catch (Exception e) {
            log.warn(e.getMessage());
        } finally {
            close(jedis);
        }
        return StringUtils.EMPTY;
    }

    @Override
    public byte[] getByte(String key, String db) {
        Jedis jedis = null;
        try {
            jedis = sentinelPool.getResource();
            return jedis.get(key.getBytes());
        } catch (Exception e) {
            log.warn(e.getMessage());
            return null;
        } finally {
            close(jedis);
        }
    }

    @Override
    public void del(String key, String db) {
        Jedis jedis = null;
        try {
            jedis = sentinelPool.getResource();
            jedis.del(key);
            writeLog();
        } catch (Exception e) {
            log.warn(e.getMessage());
        } finally {
            close(jedis);
        }
    }

    @Override
    public void delByte(String key, String db) {
        Jedis jedis = null;
        try {
            jedis = sentinelPool.getResource();
            jedis.del(key.getBytes());
            writeLog();
        } catch (Exception e) {
            log.warn(e.getMessage());
        } finally {
            close(jedis);
        }

    }

    @Override
    public void setByte(String key, byte[] value, String db) {
        Jedis jedis = null;
        try {
            jedis = sentinelPool.getResource();
            jedis.set(key.getBytes(), value);
            writeLog();
        } catch (Exception e) {
            log.warn(e.getMessage());
        } finally {
            close(jedis);
        }
    }

    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void hmset(String key, Map map, String db) {
        Jedis jedis = null;
        try {
            jedis = sentinelPool.getResource();
            jedis.hmset(key, map);
            writeLog();
        } catch (Exception e) {
            log.warn(e.getMessage());
        } finally {
            close(jedis);
        }

    }

    @Override
    public void expire(String key, String db, int timestamp) {
        Jedis jedis = null;
        try {
            jedis = sentinelPool.getResource();
            jedis.expire(key, timestamp);
        } catch (Exception e) {
            log.warn(e.getMessage());
        } finally {
            close(jedis);
        }
    }

    @Override
    public long ttl(String key, String db) {
        Jedis jedis = null;
        try {
            jedis = sentinelPool.getResource();
            return jedis.ttl(key);
        } catch (Exception e) {
            log.warn(e.getMessage());
        } finally {
            close(jedis);
        }
        return -1;
    }

    @Override
    public Set<String> getKeys(String keyClue, String db) {
        Jedis jedis = null;
        try {
            jedis = sentinelPool.getResource();
            return jedis.keys(keyClue);
        } catch (Exception e) {
            log.warn(e.getMessage());
        } finally {
            close(jedis);
        }
        return new HashSet<>();
    }

    /**
     * 根据参数 count 的值，移除列表中与参数 value 相等的元素。
     *
     * @param key
     * @param cnt > 0 : 从表头开始向表尾搜索，移除与 value 相等的元素，数量为 count 。 < 0 : 从表尾开始向表头搜索，移除与 value 相等的元素，数量为 count 的绝对值。 = 0 : 移除表中所有与 value 相等的值。
     * @param value
     */
    @Override
    public Long lrem(String key, long cnt, String value, String db) {
        Jedis jedis = null;
        try {
            jedis = sentinelPool.getResource();
            writeLog();
            return jedis.lrem(key, cnt, value);
        } catch (Exception e) {
            log.warn(e.getMessage());
        } finally {
            close(jedis);
        }
        return null;
    }

    @Override
    public String getset(String key, String newValue, String db) {
        Jedis jedis = null;
        try {
            jedis = sentinelPool.getResource();
            String value = jedis.getSet(key, newValue);
            writeLog();
            return value;
        } catch (Exception e) {
            log.warn(e.getMessage());
        } finally {
            close(jedis);
        }
        return null;
    }

    @Override
    public Long incrBy(String key, Integer value, String db) {
        Jedis jedis = null;
        try {
            jedis = sentinelPool.getResource();
            writeLog();
            return jedis.incrBy(key, value);
        } catch (Exception e) {
            log.warn(e.getMessage());
        } finally {
            close(jedis);
        }
        return null;
    }

    @Override
    public Double zincrby(String key, double score, String member) {
        Jedis jedis = null;
        try {
            jedis = sentinelPool.getResource();
            writeLog();
            return jedis.zincrby(key, score, member);
        } catch (Exception e) {
            log.warn(e.getMessage());
        } finally {
            close(jedis);
        }
        return null;
    }

    @Override
    public void lpushByte(String key, byte[][] value) {
        BinaryJedis jedis = null;
        try {
            jedis = sentinelPool.getResource();
            jedis.lpush(key.getBytes(), value);
            writeLog();
        } catch (Exception e) {
            log.warn(e.getMessage());
        } finally {
            close(jedis);
        }
    }

    @Override
    public Long zadd(String key, double score, String member) {
        Jedis jedis = null;
        try {
            jedis = sentinelPool.getResource();
            writeLog();
            return jedis.zadd(key, score, member);
        } catch (Exception e) {
            log.warn(e.getMessage());
        } finally {
            close(jedis);
        }
        return null;
    }

    @Override
    public Long zrem(String key, String... members) {
        Jedis jedis = null;
        try {
            jedis = sentinelPool.getResource();
            writeLog();
            return jedis.zrem(key, members);
        } catch (Exception e) {
            log.warn(e.getMessage());
        } finally {
            close(jedis);
        }
        return null;
    }

    @Override
    public Double zscore(String key, String members) {
        Jedis jedis = null;
        try {
            jedis = sentinelPool.getResource();
            return jedis.zscore(key, members);
        } catch (Exception e) {
            log.warn(e.getMessage());
        } finally {
            close(jedis);
        }
        return null;
    }

    @Override
    public Long zrank(String key, String members) {
        Jedis jedis = null;
        try {
            jedis = sentinelPool.getResource();
            return jedis.zrank(key, members);
        } catch (Exception e) {
            log.warn(e.getMessage());
        } finally {
            close(jedis);
        }
        return null;
    }

    @Override
    public Long zrevrank(String key, String members) {
        Jedis jedis = null;
        try {
            jedis = sentinelPool.getResource();
            return jedis.zrevrank(key, members);
        } catch (Exception e) {
            log.warn(e.getMessage());
        } finally {
            close(jedis);
        }
        return null;
    }

    @Override
    public Set<Tuple> zrevrangeWithScores(String key, Long start, Long end) {
        Jedis jedis = null;
        try {
            jedis = sentinelPool.getResource();
            return jedis.zrevrangeWithScores(key, start, end);
        } catch (Exception e) {
            log.warn(e.getMessage());
            close(jedis);
        } finally {
            close(jedis);
        }
        return null;
    }

    /**
     * wwj 2016-09-14 设置缓存失效时间
     *
     * @param key
     * @param value
     * @param expiration
     */
    @Override
    public void set(String key, String value, Integer expiration) {
        Jedis jedis = null;
        try {
            jedis = sentinelPool.getResource();
            jedis.set(key, value);
            jedis.expire(key, expiration);
            writeLog();
        } catch (Exception e) {
            log.warn(e.getMessage());
        } finally {
            close(jedis);
        }
    }

    @Override
    public Object eval(String script, List<String> keys, List<String> params) {
        Jedis jedis = null;
        try {
            jedis = sentinelPool.getResource();
            return jedis.eval(script, keys, params);
        } catch (Exception e) {
            log.warn(e.getMessage());
        } finally {
            close(jedis);
        }
        return null;
    }

    /**
     * @author 魏冰
     * @date 2017年3月6日 下午2:43:01
     * @Description 释放Jedis资源
     * @param jedis
     */
    private void close(Closeable jedis) {
        if (jedis != null) {
            try {
                jedis.close();
            } catch (IOException e) {
                log.warn(e.getMessage());
            }
        }
    }

    private void writeLog() {
        if ((count.addAndGet(1)) % 100 == 0) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            if (stackTrace.length > 4) {
                String msg = MessageFormat.format("Redis stack: {0}:{1},{2}:{3},index {4}", stackTrace[2].getClassName(), stackTrace[2].getMethodName(), stackTrace[3].getClassName(),
                        stackTrace[3].getMethodName(), count.get());
                log.info(msg);
            }
            count.set(0);
        }
    }

    /**
     * 带过期时间的incrby
     *
     * @author hjw
     * @date 2017年3月24日 上午11:06:47
     * @param key
     * @param value
     * @param db
     * @param second
     * @return
     */
    public Long incrBy4Expire(String key, Integer value, int second, int db) {
        Jedis jedis = null;
        try {
            jedis = sentinelPool.getResource();
            jedis.select(db);
            writeLog();
            Long ret = jedis.incrBy(key, value);
            jedis.expire(key, second);
            return ret;
        } catch (Exception e) {
            log.warn(e.getMessage());
        } finally {
            close(jedis);
        }
        return null;
    }

    @Override
    public Long del(String key) {
        Long result = null;
        Jedis jedis = null;
        try {
            jedis = sentinelPool.getResource();
            result = jedis.del(key);
            writeLog();
        } catch (Exception e) {
            log.warn(e.getMessage());
        } finally {
            close(jedis);
        }
        return result;
    }

    @Override
    public void hdelObject(String key, Object fields) {
        Jedis jedis = null;
        try {
            jedis = sentinelPool.getResource();
            jedis.hdel(SerializationUtils.serialize(key), SerializationUtils.serialize(fields));
            writeLog();
        } catch (Exception e) {
            log.warn(e.getMessage());
        } finally {
            close(jedis);
        }
    }

    @Override
    public void hsetObject(String key, Object field, Object value) {
        Jedis jedis = null;
        try {
            jedis = sentinelPool.getResource();
            jedis.hset(SerializationUtils.serialize(key), SerializationUtils.serialize(field), SerializationUtils.serialize(value));
            writeLog();
        } catch (Exception e) {
            log.warn(e.getMessage());
        } finally {
            close(jedis);
        }
    }

    @Override
    public Object hgetObject(String key, Object field) {
        Jedis jedis = null;
        try {
            jedis = sentinelPool.getResource();
            byte[] objectBytes = jedis.hget(SerializationUtils.serialize(key), SerializationUtils.serialize(field));
            if (null == objectBytes || objectBytes.length <= 0) {
                return null;
            }
            return SerializationUtils.deserialize(objectBytes);
        } catch (Exception e) {
            log.warn(e.getMessage());
        } finally {
            close(jedis);
        }
        return null;
    }

    @Override
    public Map<Object, Object> hgetAllObject(String key) {
        Jedis jedis = null;
        try {
            jedis = sentinelPool.getResource();
            Map<Object, Object> objectMap = null;
            Map<byte[], byte[]> byteMap = jedis.hgetAll(SerializationUtils.serialize(key));
            if (null != byteMap && byteMap.size() > 0) {
                objectMap = new HashMap<Object, Object>();
                for (Map.Entry<byte[], byte[]> element : byteMap.entrySet()) {
                    byte[] byteKey = element.getKey();
                    byte[] byteValue = element.getValue();
                    objectMap.put(SerializationUtils.deserialize(byteKey), SerializationUtils.deserialize(byteValue));
                }
            }
            return objectMap;
        } catch (Exception e) {
            log.warn(e.getMessage());
        } finally {
            close(jedis);
        }
        return null;
    }

    @Override
    public void expireAt(String key, long timestamp) {
        boolean borrowOrOprSuccess = true;
        Jedis jedis = null;
        try {
            jedis = sentinelPool.getResource();
            jedis.expireAt(key, timestamp);
        } catch (Exception e) {
            log.warn(e.getMessage());
            borrowOrOprSuccess = false;
            if (jedis != null) {
                sentinelPool.returnBrokenResource(jedis);
            }
        } finally {
            if (borrowOrOprSuccess) {
                sentinelPool.returnResource(jedis);
            }
        }
    }
}

package org.service.Impl.redis;

import org.service.RedisService;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import redis.clients.jedis.params.SetParams;

/**
 * @author zsw
 * @date 2019/4/18 20:15
 */
public class RedisServiceImpl implements RedisService {

    @Override
    public boolean set(String key, String value) {
        Jedis jedis = MyJedisPool.getJedis();
        jedis.select(2);
        if (jedis.exists(key)) {
            jedis.del(key);
        }
        jedis.set(key, value);
        MyJedisPool.releaseJedis(jedis);
        return true;
    }

    @Override
    public boolean keyExists(String key) {
        Jedis jedis = MyJedisPool.getJedis();
        boolean b = jedis.exists(key);
        MyJedisPool.releaseJedis(jedis);
        return b;
    }

    @Override
    public boolean set(String key, String value, Long expire) {
        Jedis jedis = MyJedisPool.getJedis();
        jedis.select(2);
        if (jedis.exists(key)) {
            jedis.del(key);
        }
        jedis.set(key, value,new SetParams().nx().px(expire));
        MyJedisPool.releaseJedis(jedis);
        return true;
    }
}
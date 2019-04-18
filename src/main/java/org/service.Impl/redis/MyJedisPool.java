package org.service.Impl.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author zsw
 * @date 2019/4/18 20:17
 */
public class MyJedisPool {

    private static volatile JedisPool jedisPool = null;
    private MyJedisPool(){

    }

    private static JedisPool getJedisPoolInstance(){
        if(jedisPool == null){
            synchronized (MyJedisPool.class){
                if(jedisPool == null){
                    JedisPoolConfig poolConfig = new JedisPoolConfig();
                    poolConfig.setMaxTotal(1024);
                    poolConfig.setMaxIdle(32);
                    poolConfig.setMaxWaitMillis(100 * 1000);
                    poolConfig.setTestOnBorrow(true);
                    jedisPool = new JedisPool(poolConfig,"47.106.159.165",6380,0,"zsw990807");

                }
            }
        }
        return jedisPool;
    }
    public static Jedis getJedis(){
        JedisPool jedisPool = MyJedisPool.getJedisPoolInstance();
        return jedisPool.getResource();
    }
    public static void releaseJedis(Jedis jedis){
        if(jedis != null){
            jedis.close();
        }
    }
}

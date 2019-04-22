package org.service;


/**
 * @author zsw
 * @date 2019/4/18 20:13
 */
public interface RedisService {

    /**
     *
     * @param key
     * @param value
     * @return
     */
     boolean set(final String key, String value);


    /**
     * key是否存在
     * @param key
     * @return
     */
     boolean keyExists(final String key);

    /**
     *
     * @param key
     * @param value
     * @param expire 过期时间，毫秒为单位
     * @return
     */
     boolean set(final String key,String value,Long expire);

    /**
     * 从redis取出数据
     * @param key
     * @return
     */
     String get(final String key);

     void removeKey(final String key);
}

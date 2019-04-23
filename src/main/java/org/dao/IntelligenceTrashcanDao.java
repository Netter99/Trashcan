package org.dao;

import org.model.CanInfo;

import java.util.List;

/**
 * @author zsw
 * @date 2019/4/22 22:23
 */
public interface IntelligenceTrashcanDao {

    /**
     *
     * @param ip ip
     * @param longitude 经度
     * @param latitude 纬度
     * @param geoHash geoHash值
     */
    void addTrashcan(String ip,double longitude,double latitude,String geoHash);

    /**
     * ip是否存在
     * @param ip ip
     */
    boolean isIpExists(String ip);

    /**
     * 获取查询点附近的垃圾桶
     * @param geoHashStr 查询点
     * @return
     */
    List<CanInfo> getNearByCanGeoHashStr(String[] geoHashStr);

    /**
     * 根据Ip查询信息
     * @param ip
     * @return
     */
    CanInfo getCanByIp(String ip);

}

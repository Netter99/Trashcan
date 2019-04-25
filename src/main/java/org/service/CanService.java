package org.service;

import org.model.CanInfo;
import org.vo.TrashCan;

import java.util.List;

/**
 * @author zsw
 * @date 2019/4/23 15:47
 */
public interface CanService {

    /**
     * ip是否存在
     * @param ip
     * @return
     */
    boolean ipExists(String ip);

    /**
     * 根据Ip查询垃圾桶信息
     * @param ip
     * @return
     */
    CanInfo getCanInfoByIp(String ip);

    /**
     * 获取附近的垃圾桶
     * @param ip
     * @return
     */
    List<TrashCan> getNearByTrashCan(String ip);

    /**
     * 根据经纬度获取附近的垃圾桶
     * @param longitude
     * @param latitude
     * @return
     */
    List<TrashCan> getNearByTrashCan(double longitude,double latitude);

}

package org.service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zsw
 * @date 2019/4/18 22:08
 */
public class UserIpStoreMapService {

    private static volatile Map<String,Integer> ipMap = null;
    private UserIpStoreMapService(){

    }

    /**
     * 保存用户ip
     * @param ip
     * @param userId
     */
    public static void addUserIp(String ip,int userId){
        if(ipMap == null){
            synchronized (UserIpStoreMapService.class){
                if(ipMap == null){
                    ipMap = new HashMap<>(64);
                }
            }
        }
        ipMap.put(ip,userId);
    }

    /**
     * 移除用户ip
     * @param ip
     */
    public static void removeUserIp(String ip){
        if(ipMap != null){
            ipMap.remove(ip);
        }
    }

}

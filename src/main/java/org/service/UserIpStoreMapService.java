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
     * ip是否已保存
     * @param ip
     * @return
     */
    public static boolean isIpExist(String ip){
        if(ipMap == null){
            return false;
        }
        return ipMap.containsKey(ip);
    }

    /**
     * 根据ip查询用户id
     * @param ip
     * @return
     */
    public static int getUserIdByIP(String ip){
        return ipMap.get(ip);
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

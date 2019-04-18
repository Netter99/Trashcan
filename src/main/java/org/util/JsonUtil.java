package org.util;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * @author zsw
 * @date 2019/4/18 21:25
 */
public class JsonUtil {

    public static String mapToJson(Map<String,Object> map){
        JSONObject jsonObject = new JSONObject(map);
        return jsonObject.toJSONString();
    }

}

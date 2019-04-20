package org.test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zsw
 * @date 2019/4/17 22:58
 */
public class Main {

    public static void main(String[] args) {
        Map<String,Integer> map = new HashMap<>();
        int id = map.get("hello");
        System.out.println(id);
    }

}

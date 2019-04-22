package org.websocket;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author zsw
 * @date 2019/4/21 19:26
 */
public class WebSocketServerManager {

    private static Map<Integer,WebSocketServer> servers = new HashMap<>(64);

    public static void add(int userId,WebSocketServer server){
        servers.put(userId,server);
        System.out.println("有新连接加入！当前连接数：" + servers.size());
    }
    public static int getTotal(){
        return servers.size();
    }
    public static void remove(int userId){
        System.out.println("有新连接退出！当前连接总数是:" + servers.size() );
        servers.remove(userId);
    }
    public static boolean sendMsgToUser(int userId,String msg){
        if(!servers.containsKey(userId)){
            return false;
        }
        else{
            WebSocketServer server = servers.get(userId);
            try {
                server.sendMessage(msg);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
    }

    public static void broadCast(String msg){
        Set<Map.Entry<Integer, WebSocketServer>> entrySet = servers.entrySet();
        Iterator<Map.Entry<Integer, WebSocketServer>> iterator = entrySet.iterator();
        while (iterator.hasNext()){
            Map.Entry<Integer, WebSocketServer> entry = iterator.next();
            WebSocketServer server = entry.getValue();
            try {
                server.sendMessage(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

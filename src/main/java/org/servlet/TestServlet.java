package org.servlet;

import org.service.CanService;
import org.service.Impl.CanServiceImpl;
import org.service.Impl.UserIpStoreMapService;
import org.util.JsonUtil;
import org.vo.TrashCan;
import org.websocket.WebSocketServer;
import org.websocket.WebSocketServerManager;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zsw
 * @date 2019/4/19 9:57
 */
public class TestServlet extends HttpServlet {
    CanService canService = new CanServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        Map<String,Object> map = new HashMap<>();
        List<TrashCan> nearByTrashCan = canService.getNearByTrashCan("0.0.0.0");
        map.put("trashcans",nearByTrashCan);
        String toJson = JsonUtil.mapToJson(map);
        writer.write(toJson);
        return;
//        WebSocketServerManager.broadCast("广播一下");
//        HttpSession reqSession = req.getSession();
//        WebSocketServerManager.sendMsgToUser((Integer) reqSession.getAttribute("userId"),"这是发给你的");
    }
}

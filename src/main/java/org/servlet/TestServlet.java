package org.servlet;

import org.service.Impl.UserIpStoreMapService;
import org.websocket.WebSocketServer;
import org.websocket.WebSocketServerManager;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author zsw
 * @date 2019/4/19 9:57
 */
public class TestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebSocketServerManager.broadCast("广播一下");
        HttpSession reqSession = req.getSession();
        WebSocketServerManager.sendMsgToUser((Integer) reqSession.getAttribute("userId"),"这是发给你的");
    }
}

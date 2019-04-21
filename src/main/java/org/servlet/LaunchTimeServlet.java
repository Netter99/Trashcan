package org.servlet;

import org.constant.ResponseCode;
import org.service.Impl.LaunchTimeServiceImpl;
import org.service.LaunchTimeService;
import org.util.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "LaunchTimeServlet")
public class LaunchTimeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("utf-8");

        LaunchTimeService launchTimeService = new LaunchTimeServiceImpl();
        HttpSession session = request.getSession();
        int id = (int) session.getAttribute("id");
        System.out.println(id + "----");

        if (id >= 0) {
            boolean result = launchTimeService.addAccountThrowtime(id);
            int times = launchTimeService.getAccountThrowtime(id);
            Map<String, Object> map = new HashMap<>();

            if (result) {
                map.put("request_result", ResponseCode.REQUEST_SUCCEED.getValue());
                String json = JsonUtil.mapToJson(map);
                response.getWriter().write(json);
            } else {
                map.put("request_result", ResponseCode.ERROR_CODE.getValue());
                String json = JsonUtil.mapToJson(map);
                response.getWriter().write(json);
            }

            if (times >= 0) {
                map.put("launch_time", times);
                map.put("code", ResponseCode.REQUEST_SUCCEED.getValue());
                String json = JsonUtil.mapToJson(map);
                response.getWriter().write(json);
            } else {
                map.put("request_result", ResponseCode.ERROR_CODE.getValue());
                String json = JsonUtil.mapToJson(map);
                response.getWriter().write(json);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}

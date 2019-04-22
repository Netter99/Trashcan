package org.servlet;

import org.constant.ResponseCode;
import org.service.Impl.redis.RedisServiceImpl;
import org.service.RedisService;
import org.util.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zsw
 * @date 2019/4/22 19:28
 */
@WebServlet(name = "WebLogoutServlet",
urlPatterns = "/webLogout")
public class WebLogoutServlet extends HttpServlet {
    private static final long serialVersionUID = -2340077955434183314L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        RedisService redisService = new RedisServiceImpl();
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        int userId = (int) session.getAttribute("userId");
        session.removeAttribute("userId");
        session.removeAttribute("ip");
        redisService.removeKey(WebLoginServlet.USER_LOGIN_PREFIX+userId);
        Map<String,Object> map = new HashMap<>();
        map.put("code", ResponseCode.REQUEST_SUCCEED.getValue());
        String json = JsonUtil.mapToJson(map);
        PrintWriter writer = response.getWriter();
        writer.write(json);
    }

}

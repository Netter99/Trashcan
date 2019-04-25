package org.servlet;

import org.constant.ResponseCode;
import org.service.Impl.LaunchTimeServiceImpl;
import org.service.Impl.LoginServiceImpl;
import org.service.Impl.WebUserServiceImpl;
import org.service.LaunchTimeService;
import org.service.LoginService;
import org.service.WebUserService;
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


@WebServlet(name = "ChangeAccountPwdServlet")
public class ChangeAccountPwdServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("utf-8");

        LoginServiceImpl loginService = new LoginServiceImpl();
        HttpSession session = request.getSession();
        Map<String, Object> map = new HashMap<>();
        int id = (int) session.getAttribute("id");
        String password = request.getParameter("password");

        if (id >= 0) {//用户是否登录
            //旧密码是否正确 并 新密码是否相同
            WebUserService webUserService = new WebUserServiceImpl();
            boolean result = webUserService.changPwdById(id, password);
            if (result) {
                map.put("request_result", ResponseCode.REQUEST_SUCCEED.getValue());
                String json = JsonUtil.mapToJson(map);
                response.getWriter().write(json);
                return;
            } else {
                map.put("request_result", ResponseCode.SERVER_ERROR.getValue());
                String json = JsonUtil.mapToJson(map);
                response.getWriter().write(json);
                return;
            }

        } else {
            map.put("request_result", ResponseCode.NOT_LOGIN.getValue());
            String json = JsonUtil.mapToJson(map);
            response.getWriter().write(json);
            return;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}

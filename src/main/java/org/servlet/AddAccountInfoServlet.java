package org.servlet;

import org.constant.ResponseCode;
import org.service.Impl.LoginServiceImpl;
import org.service.Impl.WebUserServiceImpl;
import org.service.LoginService;
import org.service.WebUserService;
import org.util.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//设置用户名和密码的servlet
public class AddAccountInfoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("utf-8");

        WebUserService webUserService = new WebUserServiceImpl();
        HttpSession session = req.getSession();

        Object nameFlag = session.getAttribute("nameFlag");

        String json = null;
        System.out.println("nameFlag:" + nameFlag);
        if (nameFlag != null) {//已经验证过code是有效的，并且用户没有设置过用户名和密码
            String name = req.getParameter("username");
            String pwd = req.getParameter("password");
            Map<String, Object> map = new HashMap<>();
            int id = webUserService.getMaxId();

            //设置用户名和密码
            if (webUserService.isUsernameExisted(name) == false) {//用户名不存在

                if (webUserService.setUser(id,name,pwd) == true) {
                    session.removeAttribute("nameFlag");
                    map.put("code", ResponseCode.REQUEST_SUCCEED.getValue());
                    json = JsonUtil.mapToJson(map);
                    resp.getWriter().write(json);
                    session.removeAttribute("nameFlag");

                    session.setAttribute("id",id);
                    return;
                } else {
                    map.put("code", ResponseCode.SERVER_ERROR.getValue());
                    json = JsonUtil.mapToJson(map);
                    resp.getWriter().write(json);
                    return;
                }

            } else {
                map.put("code", ResponseCode.PARAM_ILEGALL.getValue());
                json = JsonUtil.mapToJson(map);
                resp.getWriter().write(json);
                return;
            }
        } else {
            Map<String, Object> map = new HashMap<>();
            map.put("code", ResponseCode.NOT_LOGIN.getValue());
            json = JsonUtil.mapToJson(map);
            resp.getWriter().write(json);
            return;
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}

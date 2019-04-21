package org.servlet;

import org.constant.ResponseCode;
import org.service.Impl.LoginServiceImpl;
import org.service.LoginService;
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

        LoginService loginService = new LoginServiceImpl();
        HttpSession session = req.getSession();
        Object nameFlag = session.getAttribute("nameFlag");

//        int id = (int)session.getAttribute("id");
        String json = null;
        if (nameFlag != null) {//已经验证过code是有效的，并且用户没有设置过用户名和密码
            String name = req.getParameter("username");
            String pwd = req.getParameter("password");
            Map<String, Object> map = new HashMap<>();

            //设置用户名和密码
            if(loginService.iUserNameExist(name) == false){//用户名不存在
                int id = Integer.parseInt(session.getAttribute("id").toString());
                boolean result = loginService.Register(id, name, pwd);

                if(result == true){
                    session.removeAttribute("nameFlag");
                    map.put("request_result", ResponseCode.REQUEST_SUCCEED.getValue());
                    json = JsonUtil.mapToJson(map);
                    resp.getWriter().write(json);
                }else {
                    map.put("request_result", ResponseCode.PARAM_ILEGALL.getValue());
                    json = JsonUtil.mapToJson(map);
                    resp.getWriter().write(json);
                }

            }else {
                map.put("request_result", ResponseCode.PARAM_ILEGALL.getValue());
                json = JsonUtil.mapToJson(map);
                resp.getWriter().write(json);
            }
        } else {
            Map<String, Object> map = new HashMap<>();
            map.put("error", ResponseCode.SERVER_ERROR.getValue());
            json = JsonUtil.mapToJson(map);
            resp.getWriter().write(json);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}

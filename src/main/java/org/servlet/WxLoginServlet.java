package org.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.constant.ResponseCode;
import org.service.Impl.LoginServiceImpl;
import org.service.LoginService;
import org.util.JsonUtil;

import static org.constant.ResponseCode.ERROR_CODE;

public class WxLoginServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("utf-8");

        String code = request.getParameter("code");
        LoginService loginService = new LoginServiceImpl();

//        String openid = loginService.getOpenId(code);
        String openid = "a";
        boolean result1 = false;
        Map<String,Object> map = new HashMap<String,Object>();
        if (openid != null) {//openid 获取成功 --- 有效
            result1 = loginService.isOpenIdExist(openid);
            if(result1){//openid已存在
                HttpSession session = request.getSession();
                session.setAttribute("id",loginService.getId(openid));//已存在用户id
//                return 登陆成功的json字符串;
            }else{
                HttpSession session = request.getSession();
                //id与openid信息注入
                int id = loginService.getMaxId()+1;
                loginService.addOpenId(openid);
                System.out.println("newid:"+id);

                session.setAttribute("id",id);//新用户id
                map.put("code", ResponseCode.REQUEST_SUCCEED.getValue());
                String json = JsonUtil.mapToJson(map);
                response.getWriter().write(json);

                //没有设置过用户名和密码的状态码
                session.setAttribute("nameFlag","0");
            }
        } else {
            map.put("code",ResponseCode.ERROR_CODE.getValue());
            String json = JsonUtil.mapToJson(map);
            response.getWriter().write(json);
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}

package org.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONPObject;
import org.apache.commons.collections.map.HashedMap;
import org.constant.ResponseCode;
import org.entity.Account;
import org.service.ITrashcanService;
import org.service.Impl.LoginServiceImpl;
import org.service.Impl.TrashcanServiceImpl;
import org.service.LoginService;
import org.util.JsonUtil;

import static org.constant.ResponseCode.ERROR_CODE;
import static org.constant.ResponseCode.REQUEST_SUCCEED;

public class FirstServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("utf-8");

        String code = request.getParameter("code");
        LoginService loginService = new LoginServiceImpl();

        String openid = loginService.getOpenId(code);
        boolean result1 = false;
        boolean result2 = false;
        Map<String,Object> map = new HashMap<String,Object>();
        if (openid != null) {//openid 获取成功 --- 有效
            if(loginService.isOpenIdExist(openid)){//openid已存在
                HttpSession session = request.getSession();
                session.setAttribute("id",loginService.getId(openid));
//                return 登陆成功的json字符串;
            }else{
                HttpSession session = request.getSession();
                //id与openid信息注入
                int id = loginService.getMaxId()+1;
                loginService.addOpenId(id,openid);
                System.out.println(id);

                session.setAttribute("id",id);
                map.put("request_result", ResponseCode.REQUEST_SUCCEED.getValue());
                String json = JsonUtil.mapToJson(map);
                response.getWriter().write(json);


                //没有设置过用户名和密码的状态码

                session.setAttribute("nameFlag","0");
                map .put("code", ResponseCode.NOT_SET_USERNAME.getValue());
                json = JsonUtil.mapToJson(map);
                response.getWriter().write(json);
            }
        } else {
            map.put("code",ERROR_CODE);
            String json = JsonUtil.mapToJson(map);
            response.getWriter().write(json);
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}

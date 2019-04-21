package org.servlet;

import org.constant.ResponseCode;
import org.service.Impl.WebUserServiceImpl;
import org.service.Impl.redis.RedisServiceImpl;
import org.service.RedisService;
import org.service.UserIpStoreMapService;
import org.service.WebUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.util.JsonUtil;

import javax.servlet.ServletException;
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
 * @date 2019/4/18 21:21
 */
public class WebLoginSerlvet extends HttpServlet {

    private static Logger logger = LoggerFactory.getLogger(WebLoginSerlvet.class);

    private static final long serialVersionUID = -8752875050765355803L;
    private static final String USER_LOGIN_PREFIX = "login:user:id:";
    /**
     * session过期时间为2小时
     */
    private static final long SESSION_EXPIRE_TIME = 1000 * 60 * 60 * 2;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        Map<String,Object> map = new HashMap<>(16);
        String username =req.getParameter("username");
        System.out.println("username" + username);
        String password = req.getParameter("password");
        System.out.println(password);
        WebUserService webUserService = new WebUserServiceImpl();
        RedisService redisService = new RedisServiceImpl();
        if(!webUserService.isUsernameExisted(username)){
            System.out.println("用户名不存在");
            map.put("code", ResponseCode.PARAM_ILEGALL.getValue());
            map.put("msg","用户名或密码错误");
            String json = JsonUtil.mapToJson(map);
            PrintWriter writer = resp.getWriter();
            writer.write(json);
            return;
        }
        boolean b = webUserService.verifyAcoount(username, password);
        if(!b){
            System.out.println("密码错误");
            map.put("code", ResponseCode.PARAM_ILEGALL.getValue());
            map.put("msg","用户名或密码错误");
            String json = JsonUtil.mapToJson(map);
            PrintWriter writer = resp.getWriter();
            writer.write(json);
            return;
        }else{
            int userId = webUserService.getUserIdByUsername(username);
            HttpSession session = req.getSession();
            String ip = req.getParameter("ip");

            //sesssion保存用户Id
            session.setAttribute("userId",userId);
            //session保存用户ip
            session.setAttribute("ip",ip);
            //redis以用户id做键保存session
            redisService.set(USER_LOGIN_PREFIX + userId,session.getId(),SESSION_EXPIRE_TIME);
            //将用户ip和id保存
            UserIpStoreMapService.addUserIp(ip,userId);
            map.put("code",ResponseCode.REQUEST_SUCCEED.getValue());
            String json = JsonUtil.mapToJson(map);
            PrintWriter writer = resp.getWriter();
            writer.write(json);
            return;
        }

    }
}

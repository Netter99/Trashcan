package org.filter;

import org.constant.ResponseCode;
import org.service.Impl.redis.RedisServiceImpl;
import org.service.RedisService;
import org.servlet.WebLoginServlet;
import org.util.JsonUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zsw
 * @date 2019/4/21 0:20
 */
@WebFilter(filterName = "LoginFilter",
        urlPatterns = "/*",
        initParams = {
            @WebInitParam(name="unCheckUrls",value = "/weblogin,/websocketTest,/test,/websocket,/ws/bitcoinServer,/websocketTest"),
        })
public class LoginFilter implements Filter {

    private FilterConfig filterConfig;

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("LoginFilter");
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        System.out.println("请求路径"+request.getRequestURL());
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");

        //获取请求路径
        String requestPath = request.getServletPath();
        //获取不需要登录的url
        String unCheckUrls = filterConfig.getInitParameter("unCheckUrls");
        List<String> urls = Arrays.asList(unCheckUrls.split(","));
        //如果请求的路径不需要登录
        System.out.println("path:"+requestPath);
        if(urls.contains(requestPath) || requestPath.matches(".*(.html|.css|.jsp|.ico)$") || requestPath.matches("^wx.*")){
            chain.doFilter(req, resp);
        }else {
            HttpSession session = request.getSession();
            Integer userId = (Integer) session.getAttribute("userId");
            if(userId == null){
                //如果用户未登录，拒绝本次请求
                Map<String,Object> map = new HashMap<>(16);
                map.put("code",ResponseCode.NOT_LOGIN.getValue());
                String json = JsonUtil.mapToJson(map);
                PrintWriter writer = response.getWriter();
                writer.write(json);
                return;
            }else{
                RedisService redisService = new RedisServiceImpl();
                System.out.println(WebLoginServlet.USER_LOGIN_PREFIX + userId);
                if(!redisService.keyExists(WebLoginServlet.USER_LOGIN_PREFIX + userId)){
                    Map<String,Object> map = new HashMap<>(16);
                    map.put("code",ResponseCode.PARAM_ILEGALL.getValue());
                    map.put("msg","请求不合法");
                    String json = JsonUtil.mapToJson(map);
                    PrintWriter writer = response.getWriter();
                    writer.write(json);
                    return;
                }
                String sessionId = redisService.get(WebLoginServlet.USER_LOGIN_PREFIX + userId);
                if(!session.getId().equals(sessionId)){
                    Map<String,Object> map = new HashMap<>(16);
                    map.put("code",ResponseCode.LOGIN_OTHER.getValue());
                    map.put("msg","账号在其他地方登陆，你的登陆已失效");
                    String json = JsonUtil.mapToJson(map);
                    PrintWriter writer = response.getWriter();
                    writer.write(json);
                    return;
                }
                chain.doFilter(request,response);
            }
        }
    }
    @Override
    public void init(FilterConfig config) throws ServletException {
        this.filterConfig = config;
    }

}

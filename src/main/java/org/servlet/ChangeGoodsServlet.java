package org.servlet;

import org.constant.ResponseCode;
import org.service.Impl.ChangeGoodsService;
import org.service.Impl.IntegralServiceImpl;
import org.service.IntegralService;
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

@WebServlet(name = "ChangeGoodsServlet")
public class ChangeGoodsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("utf-8");

        HttpSession session = request.getSession();
        int id = (int) session.getAttribute("id");
        Map<String, Object> map = new HashMap<>();
        IntegralService integralService = new IntegralServiceImpl();

        if(id >= 0){//用户处于登录态
            int ascore  = integralService.getUserIntegral(id);
            int nscore = Integer.parseInt(request.getParameter("score"));
            String gname = request.getParameter("gname");//----------暂时不确定传入值

            if(ascore > nscore){
                ChangeGoodsService changeGoodsService = new ChangeGoodsService();
                boolean result = changeGoodsService.changeGoods(id, nscore);
                if(result){//---------需要返回什么参数暂未确定

                    map.put("request_result", ResponseCode.REQUEST_SUCCEED.getValue());
                    String json = JsonUtil.mapToJson(map);
                    response.getWriter().write(json);
                }else {
                    map.put("request_result", ResponseCode.SERVER_ERROR.getValue());
                    String json = JsonUtil.mapToJson(map);
                    response.getWriter().write(json);
                }
            }else {
                map.put("request_result", ResponseCode.PARAM_ILEGALL.getValue());
                String json = JsonUtil.mapToJson(map);
                response.getWriter().write(json);
            }
        }else {
            map.put("request_result", ResponseCode.SERVER_ERROR.getValue());
            String json = JsonUtil.mapToJson(map);
            response.getWriter().write(json);
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}

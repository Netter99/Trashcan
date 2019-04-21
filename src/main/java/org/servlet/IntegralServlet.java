package org.servlet;

import org.constant.ResponseCode;
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
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zsw
 * @date 2019/4/21 15:00
 */
@WebServlet(name = "IntegralServlet",
        urlPatterns = "/queryIntegral"
)
public class IntegralServlet extends HttpServlet {
    private static final long serialVersionUID = 3808376765078818797L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IntegralService integralService = new IntegralServiceImpl();
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        int userIntegral = integralService.getUserIntegral(userId);
        Map<String,Object> map = new HashMap<>(16);
        map.put("code", ResponseCode.REQUEST_SUCCEED.getValue());
        map.put("integral",userIntegral);
        String json = JsonUtil.mapToJson(map);
        PrintWriter writer = response.getWriter();
        writer.write(json);
        return;
    }
}

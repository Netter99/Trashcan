package org.servlet;

import org.constant.ResponseCode;
import org.service.CanService;
import org.service.Impl.CanServiceImpl;
import org.service.RedisService;
import org.util.JsonUtil;
import org.vo.TrashCan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zsw
 * @date 2019/4/22 22:17
 * <p>
 * 获取用户附近的智能垃圾桶
 */
@WebServlet(name = "GetNearbyTrashCanServlet")
public class GetNearbyTrashCanServlet extends HttpServlet {
    private static final long serialVersionUID = 7790820323056287922L;
    CanService canService = new CanServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        Map<String,Object> map = new HashMap<>(16);
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("userId");
        String ip = (String) session.getAttribute("ip");
        if(!canService.ipExists(ip)){
            map.put("code", ResponseCode.PARAM_ILEGALL.getValue());
            map.put("msg","ip不存在");
            String json = JsonUtil.mapToJson(map);
            PrintWriter writer = response.getWriter();
            writer.write(json);
            return;
        }
        List<TrashCan> nearByTrashCan = canService.getNearByTrashCan(ip);
        map.put("trashCans",nearByTrashCan);
        String json = JsonUtil.mapToJson(map);
        PrintWriter writer = response.getWriter();
        writer.write(json);
        return;
    }
}

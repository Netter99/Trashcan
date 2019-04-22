package org.servlet;

import org.constant.ResponseCode;
import org.entity.Location;
import org.service.Impl.TrashcanServiceImpl;
import org.util.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "GetLocationServlet")
public class GetLocationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("utf-8");

        TrashcanServiceImpl trashcanService = new TrashcanServiceImpl();
        List<Location> locations = trashcanService.getAllLocation();
        Map<String, Object> map = new HashMap<>();
        if(locations != null){
            for(int i=0; i<locations.size(); i++){
                Location location = locations.get(i);
                map.put("longitude",location.getLongitude());
                map.put("latitude",location.getLatitude());
            }
            map.put("request_result", ResponseCode.REQUEST_SUCCEED.getValue());
            String json = JsonUtil.mapToJson(map);
            response.getWriter().write(json);
        }else{
            map.put("request_result", ResponseCode.ERROR_CODE.getValue());
            String json = JsonUtil.mapToJson(map);
            response.getWriter().write(json);
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}

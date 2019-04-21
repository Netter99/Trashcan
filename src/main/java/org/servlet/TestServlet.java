package org.servlet;


import org.service.UserIpStoreMapService;


import org.service.Impl.UserIpStoreMapService;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zsw
 * @date 2019/4/19 9:57
 */
public class TestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(!UserIpStoreMapService.isIpExist("0.0.0.0")){
            System.out.println("ip不存在");
            return;
        }
        int id  = UserIpStoreMapService.getUserIdByIP("0.0.0.0");
        System.out.println("IP:0.0.0.0 userId:" + UserIpStoreMapService.getUserIdByIP("0.0.0.0"));
    }
}

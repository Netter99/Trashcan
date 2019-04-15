package org.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.entity.Location_noneedatnow;
import org.service.ITrashcanService;
import org.service.Impl.TrashcanServiceImpl;

public class LocationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LocationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ITrashcanService trashcanService = new TrashcanServiceImpl();
		List<Location_noneedatnow> locations = trashcanService.queryAllLocation();
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

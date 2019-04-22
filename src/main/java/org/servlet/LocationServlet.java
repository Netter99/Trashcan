package org.servlet;

import org.entity.Location;
import org.service.ITrashcanService;
import org.service.Impl.TrashcanServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class LocationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LocationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ITrashcanService trashcanService = new TrashcanServiceImpl();
		List<Location> locations = trashcanService.getAllLocation();
		
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

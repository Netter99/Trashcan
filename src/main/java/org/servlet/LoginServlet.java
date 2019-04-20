package org.servlet;

import org.entity.Account;
import org.service.ITrashcanService;
import org.service.Impl.TrashcanServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		Account account = null;
		if(session.getAttribute("account")==null) {
			String name = request.getParameter("name");
			String pwd = request.getParameter("pwd");
			account = new Account(name, pwd);
		}else {//����Ǵ�ע��ҳ����ת���������������acount
			 account = (Account)session.getAttribute("account");
		}
		session.setAttribute("account", account);//���ȫ��account����
		
		ITrashcanService trashcanService = new TrashcanServiceImpl();
		int result = trashcanService.Login(account);//��½����
		
		if(result == 1) {
			response.sendRedirect("home.html");
		}else if(result == 0){
			
		}else {
			
		}
//		request.setAttribute("result", result);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

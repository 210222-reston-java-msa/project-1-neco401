package com.revature.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.util.RequestHelper;

public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				final String URI = request.getRequestURI().replace("/maven-unit-test/", "");
				
				switch(URI) {
				case "login":
					RequestHelper.processLogin(request, response);
					break;
				case "logout":
					RequestHelper.processLogout(request, response);
					break;
				case "users":
					RequestHelper.processUsers(request, response);
					break;
				case "employee":
					RequestHelper.processEmployee(request, response);
					break;
				case "ticket":
					RequestHelper.processTicket(request, response);
					break;
				case "error":
					RequestHelper.processError(request, response);
					break;
				} 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

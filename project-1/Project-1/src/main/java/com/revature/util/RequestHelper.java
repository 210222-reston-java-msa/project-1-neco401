package com.revature.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Employee;
import com.revature.models.LoginTemplate;
import com.revature.models.Manager;
import com.revature.services.EmployeeService;
import com.revature.services.ManagerService;

public class RequestHelper {

	private static Logger log = Logger.getLogger(RequestHelper.class);
	private static ObjectMapper om = new ObjectMapper();

	public static void processLogin(HttpServletRequest req, HttpServletResponse res) throws IOException {

		// We want to turn whatever we recieve as the request into a string to process
		BufferedReader reader = req.getReader();
		StringBuilder s = new StringBuilder();

		// logic to transfer everything from our reader to our string builder
		String line = reader.readLine();
		while (line != null) {
			s.append(line);
			line = reader.readLine();
		}

		String body = s.toString();
		log.info(body);

		// I'm going to build a model called LoginTemplate which holds a username and
		// passwrod
		LoginTemplate loginAttempt = om.readValue(body, LoginTemplate.class); // from JSON --> Java Object

		String username = loginAttempt.getUsername();
		String password = loginAttempt.getPassword();

		log.info("User attempted to login with username: " + username);
		Employee e = EmployeeService.confirmLogin(username, password);

		if (e != null) {
			HttpSession session = req.getSession();
			session.setAttribute("username", username);

			PrintWriter pw = res.getWriter();
			res.setContentType("application/json");

			pw.println(om.writeValueAsString(e));

			log.info(username + " has successfully logged in");
		}else {
			res.setStatus(204); // this means that we still have a connection, but no user is found
		}

	}

	public static void processLogout(HttpServletRequest req, HttpServletResponse res) throws IOException {
		HttpSession session = req.getSession(false); // I'm capturing the session, but if there ISN'T one, I don't
														// create a new one.

		log.info("Processing logout");

		if (session != null) {

			String username = (String) session.getAttribute("username");
			log.info(username + " has logged out");

			session.invalidate();
		}

		res.setStatus(200);

	}

	public static void processError(HttpServletRequest req, HttpServletResponse res) throws IOException {

		try {
			req.getRequestDispatcher("error.html").forward(req, res);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}

	}

}

package io.openliberty.guides.jaas;

import java.io.IOException;

import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pruebasjaas.PersonalCallbackHandler;

@WebServlet("/MyLoginController")
public class MyLoginController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public MyLoginController() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String strUser = request.getParameter("j_username");
		String strPassword = request.getParameter("j_password");
		String captcha = request.getParameter("captcha");
		
		if (null != strUser && null != strPassword) {
			try {
//				if (!captcha.equals("1234")) {
//					throw new LoginException("Captcha no valido");
//				}
		        final LoginContext loginContext = new LoginContext("system.WEB_INBOUND", new PersonalCallbackHandler(strUser, strPassword, request));
				loginContext.login();
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/home");
				rd.forward(request, response);
			} catch (LoginException e) {
				System.out.println("#Error# " + e.getMessage());
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/error.html");
				rd.forward(request, response);
			}
		}
	}
}

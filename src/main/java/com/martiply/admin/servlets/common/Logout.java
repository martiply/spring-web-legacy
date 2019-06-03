package com.martiply.admin.servlets.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Logout", description = "Logout", urlPatterns = { BaseServlet.SERVLET_LOGOUT })
public class Logout extends BaseServlet {
	private static final long serialVersionUID = 5769763480048235867L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null){
			session.invalidate();
		}		
		response.sendRedirect(getServletContext().getContextPath() + SERVLET_LOGIN);
	}
	
}

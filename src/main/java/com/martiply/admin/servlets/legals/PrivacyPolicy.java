package com.martiply.admin.servlets.legals;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "PrivacyPolicy", description = "privacy policy", urlPatterns = { "/pp" })

public class PrivacyPolicy extends HttpServlet {

	private static final long serialVersionUID = -2322288041739762885L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/pp.jsp").forward(request, response); 

	}

}

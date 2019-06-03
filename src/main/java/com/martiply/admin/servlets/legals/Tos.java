package com.martiply.admin.servlets.legals;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "TOS", description = "Terms of Services", urlPatterns = { "/tos" })
public class Tos extends HttpServlet{

	private static final long serialVersionUID = -8714747900143802717L;
		
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/tos.jsp").forward(request, response); 
	}
}

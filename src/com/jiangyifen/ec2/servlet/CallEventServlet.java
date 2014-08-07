package com.jiangyifen.ec2.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CallEventServlet extends HttpServlet {

	private static final long serialVersionUID = 6598237102556192856L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String msg = request.getParameter("msg");
		msg=new String(msg.getBytes("ISO-8859-1"),"UTF-8");
		
		response.reset();
		response.setContentType("text/plain;charset=utf-8");

		response.getWriter().print(msg);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doGet(request,response);
		
	}

}

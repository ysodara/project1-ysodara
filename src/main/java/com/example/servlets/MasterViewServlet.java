package com.example.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MasterViewServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		System.out.println("Started with MasterView doGet method");
		req.getRequestDispatcher(RequestViewHelper.process(req)).forward(req,res);
	}
	
	@Override 
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
		System.out.println("Now Master doPost method");
		doGet(req,res);
	}
}

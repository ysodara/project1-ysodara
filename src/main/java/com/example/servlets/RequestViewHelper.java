package com.example.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import com.example.controllers.*;


public class RequestViewHelper {

	public static String process(HttpServletRequest req) throws ServletException, IOException {
		
		String url = req.getRequestURI();
		switch (url) {
		
			case "/Project1/home": {
				int userId = 0;
				String role = "";
				
				if (req.getSession() != null) {
					
					if ((req.getSession().getAttribute("id")) != null) {
						userId = (int)req.getSession().getAttribute("id");
					}
					
					if ((req.getSession().getAttribute("role")) != null) {
						role = (String)req.getSession().getAttribute("role");
					}
					
				} 
				
				
				System.out.println("URL AFTER FROM API: " + req.getRequestURI());
						
				if (userId == 0) {
					System.out.println(userId);
					System.out.println("Role is empty.");
					return RequestViewController.fetchHomePage(req);
				} else {
					System.out.println(userId);
					System.out.println(role);
					if (role.equals("EMPLOYEE")) {
						return RequestViewController.fetchEmployeePage(req);
					}
					else if (role.equals("MANAGER")) {
						return RequestViewController.fetchManagerPage(req);
					}
					else {return RequestViewController.fetchHomePage(req);}
				}
			}
			
			case "/Project1/logout": {
				return RequestViewController.fetchLogoutPage(req);
				}
		
		}
		return RequestViewController.fetchHomePage(req);
		
	}
}

	


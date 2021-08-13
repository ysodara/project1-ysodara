package com.example.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutController {
	
	public static void logout(HttpServletRequest req, HttpServletResponse res) throws IOException {
		//Need to delete user session to log out
		req.getSession().invalidate();
		res.setStatus(200);
		res.getWriter().println("User logged out");
	}
}

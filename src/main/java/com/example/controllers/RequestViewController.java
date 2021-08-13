package com.example.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;

import com.example.utils.HibernateUtil;

public class RequestViewController {
	
	public static String fetchHomePage (HttpServletRequest req) throws ServletException, IOException {
		return "resources/html/index.html";
	
	}
	
	public static String fetchLoginPage (HttpServletRequest req) throws ServletException, IOException {
		return "resources/html/login.html";
	
	}

	public static String fetchLogoutPage(HttpServletRequest req) {
		return "resources/html/logout.html";
	}

	public static String fetchEmployeePage(HttpServletRequest req) {
		return "resources/html/employee.html";
	}

	public static String fetchManagerPage(HttpServletRequest req) {
		return "resources/html/manager.html";
	}
}

package com.example.servlets;

import com.example.controllers.*;
import com.example.exceptions.InvalidCredentialsException;
import com.example.exceptions.UserDoesNotExistException;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;

public class ServeletJSONHelper {

	public static void process(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException, ServletException{
		System.out.println("In the ServletJSONHelper with URI: " + req.getRequestURI());
		String uri = req.getRequestURI();
		switch (uri) {
			case "/Project1/api/login": {
				LoginController.login(req,res);
				break;
			}
			case "/Project1/api/signup": {
				SignUpController.signup(req, res);
				break;
			}
			case "/Project1/api/employee/submit": {
				EmployeeController.submit(req, res);
				break;
			}
			case "/Project1/api/employee/viewpending": {
				EmployeeController.viewPending(req,res);
				break;
			}
			
			case "/Project1/api/employee/viewresolved": {
				EmployeeController.viewResolved(req,res);
				break;
			}
			case "/Project1/api/employee/viewaccount": {
				EmployeeController.viewAccount(req, res);
				break;
			}
			case "/Project1/api/employee/updateaccount": {
				EmployeeController.updateAccount(req, res);
				break;
			}
			//ManagerController
			
			case "/Project1/api/manager/approve": {
				ManagerController.approveOrDeny(req, res);
				break;
			}
			
			case "/Project1/api/manager/pending": {
				ManagerController.pendingEmployees(req, res);
				break;
			}
			
			case "/Project1/api/manager/resolved": {
				ManagerController.resolvedEmployees(req, res);
				break;
			}
			case "/Project1/api/manager/specific": {
				ManagerController.specificEmployees(req, res);
				break;
			}
			
			case "/Project1/api/manager/allemployees": {
				ManagerController.allEmployees(req, res);
				break;
			}
			
			case "/Project1/api/getsession": {
				SessionController.getSession(req, res);
				break;
			}
			
			
			case "/Project1/api/logout": {
				LogoutController.logout(req,res);
				break;
			}
		
		}
		
	}

}

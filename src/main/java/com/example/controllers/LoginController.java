package com.example.controllers;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import com.example.dao.UserDao;
import com.example.exceptions.InvalidCredentialsException;
import com.example.exceptions.UserDoesNotExistException;
import com.example.models.User;
import com.example.models.UserRole;
import com.example.services.UserService;
import com.example.utils.HibernateUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LoginController {
	
	private static UserDao uDao = new UserDao();
	private static UserService uServ = new UserService(uDao);
	
	public static void login(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException{
		
		//To read in stringified JSON data is a bit more complicated,
		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = req.getReader();
		
		String line;
		while((line = reader.readLine()) != null) {
			buffer.append(line);
			buffer.append(System.lineSeparator());
		}
		String data = buffer.toString();
		System.out.println(data);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode parsedObj = mapper.readTree(data);
		
		String username = parsedObj.get("username").asText();
		String password = parsedObj.get("password").asText();
		
		try {
			System.out.println("In the post handler");
			User u = uServ.signIn(username, password);
			//We will keep track of if the user is logged in by storing their id in the session
			if(u != null) {
				req.getSession().setAttribute("id", u.getId());
				res.setStatus(HttpServletResponse.SC_OK);
				req.getSession().setAttribute("role", u.getUserRole().getUserRole());
				res.getWriter().write(new ObjectMapper().writeValueAsString(u));
				Session ses = HibernateUtil.getSession();
				ses.clear();
			}
			else {
				res.setStatus(HttpServletResponse.SC_FORBIDDEN);
				res.getWriter().println("Username or password incorrect from Login Controller");
				System.out.println("User not signed in.");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			res.setStatus(HttpServletResponse.SC_FORBIDDEN);
			res.getWriter().println("Username or password incorrect from Login Controller");
		}
	
	}

}

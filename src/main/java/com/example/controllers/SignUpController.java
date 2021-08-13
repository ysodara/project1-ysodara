package com.example.controllers;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import com.example.dao.UserDao;
import com.example.models.User;
import com.example.services.UserService;
import com.example.utils.HibernateUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SignUpController {
	private static UserDao uDao = new UserDao();
	private static UserService uServ = new UserService(uDao);
	
	public static void signup(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException{
		
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
		String first = parsedObj.get("first").asText();
		String last = parsedObj.get("last").asText();
		String email = parsedObj.get("email").asText();
		String password = parsedObj.get("password").asText();
		
		try {
			System.out.println("In the post handler");
			User u = uServ.signUp(first, last, email, password);
			//We will keep track of if the user is logged in by storing their id in the session
			req.getSession().setAttribute("id", u.getId());
			req.getSession().setAttribute("role", "EMPLOYEE");
			res.setStatus(HttpServletResponse.SC_OK);
			res.getWriter().write(new ObjectMapper().writeValueAsString(u));
			Session ses = HibernateUtil.getSession();
			ses.clear();
		}
		catch(Exception e) {
			e.printStackTrace();
			res.setStatus(HttpServletResponse.SC_FORBIDDEN);
			res.getWriter().println("Email already exists");
		}
	
	}

}

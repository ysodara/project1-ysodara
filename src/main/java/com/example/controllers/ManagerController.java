package com.example.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;

import com.example.dao.ReimburstmentDao;
import com.example.dao.UserDao;
import com.example.models.Reimburstment;
import com.example.models.User;
import com.example.services.ManagerService;
import com.example.utils.HibernateUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ManagerController {
	private static UserDao uDao = new UserDao();
	private static ReimburstmentDao rDao = new ReimburstmentDao();
	private static ManagerService mServ = new ManagerService(uDao, rDao);

	public static void approveOrDeny(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		// Reimburstment approveRequest(int rId, int mId)
		// Reimburstment denyRequest(int rId, int mId)
		
		HttpSession session = req.getSession(true);
		
		int mId = (int)session.getAttribute("id");
		String data = dataHelper(req);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode parsedObj = mapper.readTree(data);
		
		//int mId = parsedObj.get("mId").asInt();
		int option = parsedObj.get("option").asInt();
		int rbId = parsedObj.get("rbId").asInt();
		
		
		
		try {
			Reimburstment r = new Reimburstment();
			if (option == 1) {
				r = mServ.approveRequest(rbId,mId);
			} else if (option == 2){
				r = mServ.denyRequest(rbId,mId);
			}
			res.setStatus(HttpServletResponse.SC_OK);
			
			res.getWriter().write(new ObjectMapper().writeValueAsString("Reiemburstment successfully Update."));
			Session ses = HibernateUtil.getSession();
			ses.clear();
		}
		catch(Exception e) {
			e.printStackTrace();
			res.setStatus(HttpServletResponse.SC_FORBIDDEN);
			res.getWriter().println("Add failed");
		}
		
	}

	public static void pendingEmployees(HttpServletRequest req, HttpServletResponse res) throws IOException {
		// viewAllPendingAllEmploye
		try {
			List<Reimburstment> rList = mServ.viewAllPendingAllEmployee();
			
			res.setStatus(HttpServletResponse.SC_OK);
			
			res.getWriter().write(new ObjectMapper().writeValueAsString(rList));
			Session ses = HibernateUtil.getSession();
			ses.clear();
		}
		catch(Exception e) {
			e.printStackTrace();
			res.setStatus(HttpServletResponse.SC_FORBIDDEN);
			res.getWriter().println("Add failed");
		}
		
	}

	public static void resolvedEmployees(HttpServletRequest req, HttpServletResponse res) throws IOException {
		// viewAllResovledAllEmployee
		try {
			List<Reimburstment> rList = mServ.viewAllResovledAllEmployee();
			
			res.setStatus(HttpServletResponse.SC_OK);
			
			res.getWriter().write(new ObjectMapper().writeValueAsString(rList));
			Session ses = HibernateUtil.getSession();
			ses.clear();
		}
		catch(Exception e) {
			e.printStackTrace();
			res.setStatus(HttpServletResponse.SC_FORBIDDEN);
			res.getWriter().println("Add failed");
		}
		
	}

	public static void specificEmployees(HttpServletRequest req, HttpServletResponse res) throws IOException {
		// viewAllRequestOfEmployee(int id) Employee ID
		String data = dataHelper(req);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode parsedObj = mapper.readTree(data);
		
		int employeeId = parsedObj.get("employeeId").asInt();
		
		try {
			List<Reimburstment> rList = mServ.viewAllRequestOfEmployee(employeeId);
			
			res.setStatus(HttpServletResponse.SC_OK);
			
			res.getWriter().write(new ObjectMapper().writeValueAsString(rList));
			Session ses = HibernateUtil.getSession();
			ses.clear();
		}
		catch(Exception e) {
			e.printStackTrace();
			res.setStatus(HttpServletResponse.SC_FORBIDDEN);
			res.getWriter().println("Add failed");
		}
		
	}
	public static void allEmployees(HttpServletRequest req, HttpServletResponse res) throws IOException {
		// viewAllEmployee()
		try {
			List<User> uList = mServ.viewAllEmployee();
			
			res.setStatus(HttpServletResponse.SC_OK);
			
			res.getWriter().write(new ObjectMapper().writeValueAsString(uList));
			Session ses = HibernateUtil.getSession();
			ses.clear();
		}
		catch(Exception e) {
			e.printStackTrace();
			res.setStatus(HttpServletResponse.SC_FORBIDDEN);
			res.getWriter().println("Request all employee failed");
		}
		
	}
	
	public static String dataHelper (HttpServletRequest req) throws IOException {
		//To read in stringified JSON data is a bit more complicated,
		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = req.getReader();
		
		String line;
		while((line = reader.readLine()) != null) {
			buffer.append(line);
			buffer.append(System.lineSeparator());
		}
		String data = buffer.toString();

		return data;
	}

}

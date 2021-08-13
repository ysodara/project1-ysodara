package com.example.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.example.dao.ReimburstmentDao;
import com.example.dao.UserDao;
import com.example.models.ReimBStatus;
import com.example.models.ReimBType;
import com.example.models.Reimburstment;
import com.example.models.User;
import com.example.models.UserRole;

public class EmployeeService {
	
	private UserDao uDao;
	private ReimburstmentDao rDao;
	
	public EmployeeService(){
		
	}
	
	public EmployeeService(UserDao udao, ReimburstmentDao rDao) {
		this.uDao = udao;
		this.rDao = rDao;
	}
	
	public Reimburstment submit (double amount, String description, int employeeId, int typeId, String type) {
		//manullay for now:
		
		ReimBStatus s = new ReimBStatus(2,"PENDING");
		ReimBType t = new ReimBType(typeId,type);
		User u = uDao.selectUser(employeeId);
		LocalDateTime currentTime = LocalDateTime.now();
		//DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");  
		//String formatDateTime = currentTime.format(format); 
		//double amount, String description, String reciept, User submittedBy, ReimBType type, LocalDateTime submitted,  ReimBStatus status
		Reimburstment r = new Reimburstment(amount, description, null, u, t, currentTime, s);
		
		rDao.insert(r);
		
		return r;
	}
	
	public List<Reimburstment> viewPendingRequests (int employeeId) {
		List<Reimburstment> r = new ArrayList<>();
		r = rDao.selectPendingTickets(employeeId);
		return r;
	}
	
	public List<Reimburstment> viewResolvedRequests (int employeeId) {
		List<Reimburstment> r = new ArrayList<>();
		r = rDao.selectResovledTickets(employeeId);
		return r;
	}
	
	public User viewAccountInfo (int id) {
		User u = uDao.selectUser(id);
		return u;
	}
	
	public User updateAccountInfo (int id, String email, String firstName, String LastName, String password, String username) {
		User currentUser = uDao.selectUser(id);
		
		if (email == null | email == "") {
			
		} else {
			currentUser.setEmail(email);
		}
		
		if (firstName==null | firstName == "") {
			
		} else {
			currentUser.setFirstName(firstName);
		}
		
		if (LastName==null | LastName == "") {
			
		} else {
			currentUser.setLastName(LastName);
		}
		
		if (password==null | password == "") {
			
		} else {
			currentUser.setPassword(password);
		}
		
		if (username==null | username == "") {
			
		} else {
			currentUser.setUsername(username);
		}
		
		uDao.update(currentUser);
		
		return currentUser;
	}
	
	
}

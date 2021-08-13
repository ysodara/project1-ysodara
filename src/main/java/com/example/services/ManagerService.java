package com.example.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.dao.ReimburstmentDao;
import com.example.dao.UserDao;
import com.example.models.ReimBStatus;
import com.example.models.ReimBType;
import com.example.models.Reimburstment;
import com.example.models.User;

public class ManagerService {
	private UserDao uDao;
	private ReimburstmentDao rDao;
	
	public ManagerService () {
		
	}
	
	public ManagerService (UserDao uDao, ReimburstmentDao rDao) {
		this.uDao = uDao;
		this.rDao = rDao;
	}
	
	public Reimburstment approveRequest(int rId, int mId) {
		Reimburstment currentRB = rDao.selectRB(rId);
		User m = uDao.selectUser(mId);
		ReimBStatus s = new ReimBStatus(1, "APPROVED");
		currentRB.setResovledBy(m);
		currentRB.setStatus(s);
		LocalDateTime currentTime = LocalDateTime.now();
		currentRB.setResovled(currentTime);
		
		rDao.update(currentRB);
		return currentRB;
	}
	
	public Reimburstment denyRequest(int rId, int mId) {
		Reimburstment currentRB = rDao.selectRB(rId);
		User m = uDao.selectUser(mId);
		ReimBStatus s = new ReimBStatus(3, "DENIED");
		currentRB.setResovledBy(m);
		currentRB.setStatus(s);
		LocalDateTime currentTime = LocalDateTime.now();
		currentRB.setResovled(currentTime);
		
		rDao.update(currentRB);
		return currentRB;
	}
	
	public List<Reimburstment> viewAllPendingAllEmployee() {
		List<Reimburstment> currentRB = rDao.selectEmployeePendingTickets();
		return currentRB;
	}
	
	public List<Reimburstment> viewAllResovledAllEmployee() {
		List<Reimburstment> currentRB = rDao.selectEmployeeResovledTickets();
		return currentRB;
	}
	
	public List<Reimburstment> viewAllRequestOfEmployee(int id) {
		List<Reimburstment> currentRB = rDao.selectEmployeeAllTickets(id);
		return currentRB;
	}
	
	public List<User> viewAllEmployee() {
		List<User> allEmployee = uDao.selectAllEmployee();
		return allEmployee;
	}
	
}

package com.example.userTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import com.example.dao.ReimburstmentDao;
import com.example.dao.UserDao;
import com.example.models.ReimBStatus;
import com.example.models.ReimBType;
import com.example.models.Reimburstment;
import com.example.models.User;
import com.example.models.UserRole;
import com.example.services.EmployeeService;
import com.example.services.ManagerService;

public class ManagerServiceTest {
	@InjectMocks
	public ManagerService mServ;
	
	@Mock
	public UserDao uDao;
	@Mock
	private ReimburstmentDao rDao;
	
	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test 
	public void testValidApproveRequest() {
		UserRole role = new UserRole(1,"EMPLOYEE");
		UserRole role1 = new UserRole(2,"MANAGER");
		User submittedBy = new User(1,"firstName", "lastName", "email", "username", "password", role);
		User manager = new User(1,"firstName", "lastName", "emails", "manager", "password", role1);
		ReimBStatus s = new ReimBStatus(2,"PENDING");
		ReimBType type = new ReimBType(1,"LODGING");
		
		LocalDateTime someTime = LocalDateTime.of(2021, Month.AUGUST, 2, 19, 30, 40);
		
		Reimburstment newRB = new Reimburstment(1,200, "description", null, submittedBy, type, someTime, s);
		when(rDao.selectRB(1)).thenReturn(newRB);
		when(uDao.selectUser(1)).thenReturn(manager);
		
		Mockito.doNothing().when(rDao).update((newRB));
		
		newRB = mServ.approveRequest(1, 1);
		
		assertEquals(newRB.getStatus().getReimBStatus(), "APPROVED");
		assertEquals(newRB.getResovledBy().getUserRole().getUserRole(), "MANAGER");
	
	}
	
	@Test 
	public void testValidDenyRequest() {
		UserRole role = new UserRole(1,"EMPLOYEE");
		UserRole role1 = new UserRole(2,"MANAGER");
		User submittedBy = new User(1,"firstName", "lastName", "email", "username", "password", role);
		User manager = new User(1,"firstName", "lastName", "emails", "manager", "password", role1);
		ReimBStatus s = new ReimBStatus(2,"PENDING");
		ReimBType type = new ReimBType(1,"LODGING");
		
		LocalDateTime someTime = LocalDateTime.of(2021, Month.AUGUST, 2, 19, 30, 40);
		
		Reimburstment newRB = new Reimburstment(1,200, "description", null, submittedBy, type, someTime, s);
		when(rDao.selectRB(1)).thenReturn(newRB);
		when(uDao.selectUser(1)).thenReturn(manager);
		
		Mockito.doNothing().when(rDao).update((newRB));
		
		newRB = mServ.denyRequest(1, 1);
		
		assertEquals(newRB.getStatus().getReimBStatus(), "DENIED");
		assertEquals(newRB.getResovledBy().getUserRole().getUserRole(), "MANAGER");
	
	}
	
	@Test
	public void testViewAllPendingAllEmployee() {
		UserRole role = new UserRole(1,"EMPLOYEE");
		UserRole role1 = new UserRole(2,"MANAGER");
		User submittedBy = new User(1,"firstName", "lastName", "email", "username", "password", role);
		User manager = new User(1,"firstName", "lastName", "emails", "manager", "password", role1);
		ReimBStatus s = new ReimBStatus(1,"Pending");
		ReimBType type = new ReimBType(1,"LODGING");
		
		LocalDateTime someTime = LocalDateTime.of(2021, Month.AUGUST, 2, 19, 30, 40);
		Reimburstment newRB = new Reimburstment(1,200, "description", null, submittedBy,manager, type, someTime, s);
		List<Reimburstment> currentRB = new ArrayList<>();
		currentRB.add(newRB);
		
		when(rDao.selectEmployeePendingTickets()).thenReturn(currentRB);
		
		
		List<Reimburstment> viewPending = mServ.viewAllPendingAllEmployee();
				
		assertEquals(newRB.getStatus().getReimBStatus(), viewPending.get(0).getStatus().getReimBStatus());
		assertEquals(newRB.getSubmittedBy(), viewPending.get(0).getSubmittedBy());
		assertEquals(newRB.getResovledBy(), viewPending.get(0).getResovledBy());
		
	}
	
	@Test
	public void testViewAllResolvedAllEmployee() {
		UserRole role = new UserRole(1,"EMPLOYEE");
		UserRole role1 = new UserRole(2,"MANAGER");
		User submittedBy = new User(1,"firstName", "lastName", "email", "username", "password", role);
		User manager = new User(1,"firstName", "lastName", "emails", "manager", "password", role1);
		ReimBStatus s = new ReimBStatus(1,"APPROVED");
		ReimBType type = new ReimBType(1,"LODGING");
		LocalDateTime someTime = LocalDateTime.of(2021, Month.AUGUST, 2, 19, 30, 40);
		Reimburstment newRB = new Reimburstment(1,200, "description", null, submittedBy,manager, type, someTime, s);
		List<Reimburstment> currentRB = new ArrayList<>();
		currentRB.add(newRB);
		
		when(rDao.selectEmployeeResovledTickets()).thenReturn(currentRB);
		
		
		List<Reimburstment> viewPending = mServ.viewAllResovledAllEmployee();
				
		assertEquals(newRB.getStatus().getReimBStatus(), viewPending.get(0).getStatus().getReimBStatus());
		assertEquals(newRB.getSubmittedBy(), viewPending.get(0).getSubmittedBy());
		assertEquals(newRB.getResovledBy(), viewPending.get(0).getResovledBy());
		
	}
	
	@Test
	public void testviewAllRequestOfEmployee() {
		UserRole role = new UserRole(1,"EMPLOYEE");
		UserRole role1 = new UserRole(2,"MANAGER");
		User submittedBy = new User(1,"firstName", "lastName", "email", "username", "password", role);
		User manager = new User(1,"firstName", "lastName", "emails", "manager", "password", role1);
		ReimBStatus s = new ReimBStatus(1,"APPROVED");
		ReimBType type = new ReimBType(1,"LODGING");
		LocalDateTime someTime = LocalDateTime.of(2021, Month.AUGUST, 2, 19, 30, 40);
		Reimburstment newRB = new Reimburstment(1,200, "description", null, submittedBy,manager, type, someTime, s);
		Reimburstment newRB2 = new Reimburstment(2,2002, "description", null, submittedBy,manager, type, someTime, s);
		List<Reimburstment> currentRB = new ArrayList<>();
		currentRB.add(newRB);
		currentRB.add(newRB2);
		
		when(rDao.selectEmployeeAllTickets(1)).thenReturn(currentRB);
		
		
		List<Reimburstment> viewPending = mServ.viewAllRequestOfEmployee(submittedBy.getId());
				
		assertEquals(viewPending.get(0).getReimbId(), 1);
		assertEquals(viewPending.get(1).getReimbId(), 2);
		assertEquals(viewPending.size(), 2);
	
	}
	
	@Test 
	public void viewAllEmployee() {
		UserRole role = new UserRole(1,"EMPLOYEE");
		User user1 = new User(1,"firstName", "lastName", "email", "username", "password", role);
		User user2 = new User(2,"firstName", "lastName", "email", "username", "password", role);
		
		List<User> uList = new ArrayList<>();
		uList.add(user2);
		uList.add(user1);
		when(uDao.selectAllEmployee()).thenReturn(uList);
		
		List<User> testList = mServ.viewAllEmployee();
		
		assertEquals(testList.size(), 2);
		assertEquals(testList.get(0), user2);
	}
	
	@Test
	public void testDefaultConstructor () {
		ManagerService dMServ = new ManagerService();;
		assertFalse(dMServ == mServ);
	}
}

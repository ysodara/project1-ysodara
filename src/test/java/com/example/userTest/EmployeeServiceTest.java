package com.example.userTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.example.dao.ReimburstmentDao;
import com.example.dao.UserDao;
import com.example.exceptions.InvalidCredentialsException;
import com.example.exceptions.UserDoesNotExistException;
import com.example.exceptions.UserNameAlreadyExistsException;
import com.example.models.ReimBStatus;
import com.example.models.ReimBType;
import com.example.models.Reimburstment;
import com.example.models.User;
import com.example.models.UserRole;
import com.example.services.EmployeeService;
import com.example.services.UserService;


public class EmployeeServiceTest {

	@InjectMocks
	public EmployeeService eServ;
	
	@Mock
	public UserDao uDao;
	@Mock
	private ReimburstmentDao rDao;
	
	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testValidSubmit() {
		UserRole role = new UserRole(1,"EMPLOYEE");
		User submittedBy = new User(1,"firstName", "lastName", "email", "username", "password", role);
		ReimBStatus s = new ReimBStatus(2,"PENDING");
		ReimBType type = new ReimBType(1,"LODGING");
		when(uDao.selectUser(submittedBy.getId())).thenReturn(submittedBy);
		LocalDateTime someTime = LocalDateTime.of(2021, Month.AUGUST, 2, 19, 30, 40);
		
		Reimburstment newRB = new Reimburstment(200, "description", null, submittedBy, type, someTime, s);
		
		Mockito.doNothing().when(rDao).insert((newRB));
		
		Reimburstment submitRB = eServ.submit(200, "description", 1, 1, "LODGING");

		assertEquals(newRB.getSubmittedBy(), submitRB.getSubmittedBy());
	}
	
	@Test
	public void testValidViewPendingRequests () {
		UserRole role = new UserRole(1,"EMPLOYEE");
		User employee = new User(1,"firstName", "lastName", "email", "username", "password", role);
		ReimBStatus s = new ReimBStatus(2,"PENDING");
		ReimBType type = new ReimBType(1,"LODGING");
		LocalDateTime someTime = LocalDateTime.of(2021, Month.AUGUST, 2, 19, 30, 40);
		
		Reimburstment newRB = new Reimburstment(200, "description", null, employee, type, someTime, s);
		
		List<Reimburstment> rList = new ArrayList<>();
		rList.add(newRB);
		
		when(rDao.selectPendingTickets(employee.getId())).thenReturn(rList);
		
		List<Reimburstment> rbList = eServ.viewPendingRequests(employee.getId());
		
		
		assertEquals(rList.get(0).getSubmittedBy(), rbList.get(0).getSubmittedBy());
		
		
	}
	
	@Test
	public void testValidViewResolvedRequests () {
		UserRole role = new UserRole(1,"EMPLOYEE");
		User employee = new User(1,"firstName", "lastName", "email", "username", "password", role);
		ReimBStatus s = new ReimBStatus(2,"PENDING");
		ReimBType type = new ReimBType(1,"LODGING");
		LocalDateTime someTime = LocalDateTime.of(2021, Month.AUGUST, 2, 19, 30, 40);
		
		Reimburstment newRB = new Reimburstment(200, "description", null, employee, type, someTime, s);
		
		List<Reimburstment> rList = new ArrayList<>();
		rList.add(newRB);
		
		when(rDao.selectResovledTickets(employee.getId())).thenReturn(rList);
		
		List<Reimburstment> rbList = eServ.viewResolvedRequests(employee.getId());
		
		
		assertEquals(rList.get(0).getSubmittedBy(), rbList.get(0).getSubmittedBy());
		
		
	}
	
	@Test 
	public void testViewAccountInfo() {
		UserRole role = new UserRole(1,"EMPLOYEE");
		User user = new User(1,"firstName", "lastName", "email", "username", "password", role);
		
		when(uDao.selectUser(user.getId())).thenReturn(user);
		
		User test = eServ.viewAccountInfo(user.getId());
		
		assertEquals(user.getId(),test.getId());
		
	}
	
	@Test
	public void testUpdateAccount() {
		UserRole role = new UserRole(1,"EMPLOYEE");
		User user = new User(1,"firstName", "lastName", "email", "username", "password", role);
		
		String firstNameToUpdate = "John";
		
		when(uDao.selectUser(user.getId())).thenReturn(user);
		
		User updatedUser = eServ.updateAccountInfo(user.getId(), user.getEmail(), firstNameToUpdate, user.getLastName(), user.getPassword(), user.getUsername());
		
		assertEquals(firstNameToUpdate, updatedUser.getFirstName());
		
	}
	
	@Test
	public void testNullUpdateAccount() {
		UserRole role = new UserRole(1,"EMPLOYEE");
		User user = new User(1,"firstName", "lastName", "email", "username", "password", role);
		User not = new User(1,"", "", "", "", "", role);
		
		String firstNameToUpdate = "John";
		
		when(uDao.selectUser(user.getId())).thenReturn(not);
		
		User updatedUser = eServ.updateAccountInfo(1,null, null, null, null, null);
		
		assertEquals(user.getId(), updatedUser.getId());
		
	}
	
	@Test
	public void testEmptyUpdateAccount() {
		UserRole role = new UserRole(1,"EMPLOYEE");
		User user = new User(1,"firstName", "lastName", "email", "username", "password", role);
		User not = new User(1,"", "", "", "", "", role);
		
		String firstNameToUpdate = "John";
		
		when(uDao.selectUser(user.getId())).thenReturn(not);
		
		User updatedUser = eServ.updateAccountInfo(1,"", "",  "", "", "");
		
		assertEquals(user.getId(), updatedUser.getId());
		
	}
	
	@Test
	public void testDefaultConstructor () {
		EmployeeService dEServ = new EmployeeService();
		assertFalse(dEServ == eServ);
	}
}

	


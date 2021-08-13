import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;

import com.example.controllers.LoginController;
import com.example.dao.ReimburstmentDao;
import com.example.dao.UserDao;
import com.example.exceptions.InvalidCredentialsException;
import com.example.exceptions.UserDoesNotExistException;
import com.example.exceptions.UserNameAlreadyExistsException;
import com.example.models.ReimBType;
import com.example.models.Reimburstment;
import com.example.models.User;
import com.example.models.UserRole;
import com.example.services.EmployeeService;
import com.example.services.ManagerService;
import com.example.services.UserService;

import jdk.javadoc.internal.doclets.formats.html.markup.HtmlAttr.Role;
import com.example.models.UserRole;
import com.example.utils.HibernateUtil;
import com.fasterxml.jackson.core.JsonProcessingException;


public class ReimBDriver {
	
	public static void main(String[] args) throws JsonProcessingException, IOException, UserDoesNotExistException, InvalidCredentialsException, UserNameAlreadyExistsException, SQLException {
		UserDao uDao = new UserDao();
		ReimburstmentDao rDao = new ReimburstmentDao();
		UserService uServ = new UserService(uDao);
		
		//User u = uServ.signUp("Dean", "Winchester", "DW@", "password");
		
		
//		EmployeeService eServ = new EmployeeService(uDao, rDao);
//		ManagerService mServ = new ManagerService(uDao, rDao);
//		User u1= new User();
		
		
		
		//Reimburstment r = mServ.approveRequest(2, 2);
//		Reimburstment r = mServ.denyRequest(2, 2);
//		System.out.println(r);
//		List <Reimburstment> rbl = mServ.viewAllPendingAllEmployee();
//		rbl = mServ.viewAllResovledAllEmployee();
//		rbl = mServ.viewAllRequestOfEmployee(1);
//		List<User> ul = mServ.viewAllEmployee();
//		
//		public List<User> viewAllEmployee() {
//			List<User> allEmployee = uDao.selectAllEmployee();
//			return allEmployee;
//		}
		
		//User updatedUser = eServ.updateAccountInfo(3, null, "Trying Update", null, null, null);
		//System.out.println(updatedUser);
		//uDao.update(u1);
		//User u = userv.signIn("JJ12", "passwords");
		//System.out.println(u.toString());
//		UserRole employeeRole = new UserRole();
//		employeeRole.setUserRole("MANAGER");
//		employeeRole.setUserRoleId(2);
		//String firstName, String lastName, String email, String password, UserRole userRole
		//User u1= new User("firstname","lastname", "email12@", "password", employeeRole);
		//double amount, String description, int employeeId, int typeId, String type, int statusId, String Status
		//Reimburstment r = eServ.submit(100, "Lodgin", 1, 1 , "LODGING");
		
//		System.out.println(r.getReimbId());
//		System.out.println(r.getDescription());
//		System.out.println(r.getStatus().getReimBStatus());
		
		//uDao.insert(u1);
		
//		List<User> ulist = uDao.selectAll();
//		
//		User u = uDao.selectUserByUsername("s");
//		
//		u = uDao.selectUser(1);
//		
//		
//		List<Reimburstment> r = rDao.selectEmployeePendingTickets();
//		
//		
//		List<Reimburstment> r1 = rDao.selectEmployeeResovledTickets();
//		
//		
//		List<Reimburstment> r2 = rDao.selectEmployeeTickets(1);
//		
//		
//		List<Reimburstment> r3 = rDao.selectPendingTickets(1);
//		
//		
//		List<Reimburstment> r4 = rDao.selectResovledTickets(1);
		
		
		
//		System.out.println("-------------------------------------------------------------");
//		System.out.println("User: ");
//		System.out.println(u.toString());
//		System.out.println("-------------------------------------------------------------");
//		
//		
//		System.out.println("-------------------------------------------------------------");
//		System.out.println("View all pending requests of all employees");
//		System.out.println(r.toString());
//		System.out.println("-------------------------------------------------------------");
//		
//		System.out.println("-------------------------------------------------------------");
//		System.out.println("View all resolved requests of all employees");
//		System.out.println(r1.toString());
//		System.out.println();
//		
//		System.out.println("View reimbursement requests of a specific employee");
//		System.out.println(r2.toString());
//		System.out.println("-------------------------------------------------------------");
//		
//		System.out.println("View pending reimbursement requests for employee");
//		System.out.println(r3.toString());
//		System.out.println("-------------------------------------------------------------");
//		System.out.println("View resolved reimbursement requests for employee");
//		System.out.println(r4.toString());
//		System.out.println("-------------------------------------------------------------");
		
		Session ses = HibernateUtil.getSession();
		
	}

}

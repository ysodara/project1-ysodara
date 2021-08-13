package com.example.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.example.models.Reimburstment;
import com.example.utils.HibernateUtil;

public class ReimburstmentDao {
	
	
	public ReimburstmentDao() {}
	
	public Reimburstment selectRB(int id) {
		Session ses = HibernateUtil.getSession();
		//If you are using ses.get(), you must use the id
		Reimburstment rb = ses.get(Reimburstment.class, id);
		//ses.close();
		return rb;
	}
	
	public void insert(Reimburstment rb) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.getTransaction();
		
		if(!tx.isActive()) {
			tx = ses.beginTransaction();
		} 
		
		ses.save(rb);
		tx.commit();
		ses.clear();
	}
	
	public void update(Reimburstment rb) {
		Reimburstment rToUpdate = rb;
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.getTransaction();
		
		if(!tx.isActive()) {
			tx = ses.beginTransaction();
		} 
		ses.update(rToUpdate);
		tx.commit();
		ses.evict(rToUpdate);
	}
	
	public List<Reimburstment> selectPendingTickets(int employeeId) {
		Session ses = HibernateUtil.getSession();
		//If you are using ses.get(), you must use the id
		List<Reimburstment> reimbList = ses.createQuery("from Reimburstment where status_fk = 2 and employee_submit_fk=" + employeeId, Reimburstment.class).list();
		if(reimbList.size() == 0) {
			return null;
		}
		return reimbList;
	}
	
	public List<Reimburstment> selectResovledTickets(int employeeId) {
		Session ses = HibernateUtil.getSession();
		//If you are using ses.get(), you must use the id
		List<Reimburstment> reimbList = ses.createQuery("from Reimburstment where status_fk = 1 and employee_submit_fk=" + employeeId, Reimburstment.class).list();
		if(reimbList.size() == 0) {
			return null;
		}
		return reimbList;
	}
	
	public List<Reimburstment> selectEmployeeResovledTickets() {
		Session ses = HibernateUtil.getSession();
		//If you are using ses.get(), you must use the id
		List<Reimburstment> reimbList = ses.createQuery("from Reimburstment where status_fk = 1", Reimburstment.class).list();
		if(reimbList.size() == 0) {
			return null;
		}
		return reimbList;
	}
	
	public List<Reimburstment> selectEmployeePendingTickets() {
		Session ses = HibernateUtil.getSession();
		//If you are using ses.get(), you must use the id
		List<Reimburstment> reimbList = ses.createQuery("from Reimburstment where status_fk = 2", Reimburstment.class).list();
		if(reimbList.size() == 0) {
			return null;
		}
		return reimbList;
	}
	
	public List<Reimburstment> selectEmployeeAllTickets(int employeeId) {
		Session ses = HibernateUtil.getSession();
		//If you are using ses.get(), you must use the id
		List<Reimburstment> reimbList = ses.createQuery("from Reimburstment where employee_submit_fk=" + employeeId, Reimburstment.class).list();
		if(reimbList.size() == 0) {
			return null;
		}
		return reimbList;
	}
	
}

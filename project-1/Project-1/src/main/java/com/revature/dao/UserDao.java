package com.revature.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Employee;
import com.revature.models.Manager;
import com.revature.models.User;
import com.revature.util.HibernateUtil;

public class UserDao {
	
	private static Logger log = Logger.getLogger(UserDao.class);
	
	public void insert(User u) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		
		ses.save(u);
		tx.commit();
		
		log.info("User Inserted");
	}

	public List<Employee> findAllEmp(){
		Session ses = HibernateUtil.getSession();
		
		List<Employee> empList = ses.createQuery("from User where roleId = 2", Employee.class).list();
		
		return empList;
	}
	
	public Employee findById(int id) {
		
		Session ses = HibernateUtil.getSession();
		
		Employee e = ses.get(Employee.class, id);
		
		return e;
	}
	
	public Employee findEbyUsername(String username) {
		
		Session ses = HibernateUtil.getSession(); 
		
		//Note: It is querying thru the Java classes for java data types, not SQL
		List<Employee> empList = ses.createQuery("from User where username='" + username + "'", Employee.class).list();
		
		
		if(empList.size() == 0) {
			System.out.println("Unable to find the Employee by this Username");
			return null;
		}
		
		return empList.get(0);
	}
	
	public Manager findMbyUsername(String username) {
		
		Session ses = HibernateUtil.getSession(); 
		
		//Note: It is querying thru the Java classes for java data types, not SQL
		List<Manager> maList = ses.createQuery("from User where username='" + username + "'", Manager.class).list();
		
		
		if(maList.size() == 0) {
			System.out.println("Unable to find the Employee by this Username");
			return null;
		}
		
		return maList.get(0);
	}
	
	public void update(User u) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		
		ses.update(u);
		tx.commit();
		
		log.info("Updated the Employee");
	}
	
}

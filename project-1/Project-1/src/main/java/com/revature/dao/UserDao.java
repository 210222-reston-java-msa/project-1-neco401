package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Employee;
import com.revature.models.Manager;
import com.revature.util.ConnectionUtil;
import com.revature.util.HibernateUtil;

public class UserDao {
	
	private static Logger log = Logger.getLogger(UserDao.class);
	
	public void insertEmployee(Employee e) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		
		ses.save(e);
		tx.commit();
	}
	
	public void insertManager(Manager m) {
		
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		
		ses.save(m);
		tx.commit();
	}

	public List<Employee> findAllEmp(){
		List<Employee> empList = new ArrayList<Employee>();
		
		try {
			Connection conn = ConnectionUtil.getConnection();
			String sql = "SELECT * FROM employee";
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String username = rs.getString("username");
				String password = rs.getString("passcode");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String email = rs.getString("email");
				
				Employee e = new Employee(id, username, password, firstName, lastName, email);
				empList.add(e);
			}
			
			
		}catch(SQLException ex) {
			log.warn("Unable to return all users, " + ex);
		}
		
		return empList;
	}
	
	public Employee findById(int id) {
		Employee e;
		try {
			Connection conn = ConnectionUtil.getConnection();
			String sql = "SELECT * FROM users WHERE id = " + id;
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				String username = rs.getString("username");
				String password = rs.getString("passcode");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String email = rs.getString("email");
				
				e = new Employee(id, username, password, firstName, lastName, email);
				return e;
			}
			}catch(SQLException ex) {
			log.warn("Unable to find users by id, " + ex);
		}
		return null;
	}
	
	public Employee findbyUsername(String username) {
		
		return null;
	}
	
	public boolean updateEmployee(Employee e) {
			try {
				Connection conn = ConnectionUtil.getConnection();
				int id = e.getId();
				String firstName = e.getFirstName();
				String lastName = e.getLastName();
				String email = e.getEmail();
				String username = e.getUsername();
				String passcode = e.getPassword();
				
				//(username, passcode, first_name, last_name, email, role_id)
				String sql = "UPDATE users SET  username = " + username + ", passcode = " + passcode + ", first_name = " + firstName + ", last_name = " + lastName + ", email = " + email +  " WHERE id = " + id + " ";
				Statement stmt = conn.createStatement();
				
				if ( stmt.executeUpdate(sql) == 1) {
					log.info("Updated DB");
					return true;
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			
			log.info("Unable to update Employee object");
			return false;
	}
	
	public boolean updateManager(Manager m) {
		try {
			Connection conn = ConnectionUtil.getConnection();
			int id = m.getId();
			String firstName = m.getFirstName();
			String lastName = m.getLastName();
			String email = m.getEmail();
			String username = m.getUsername();
			String passcode = m.getPassword();
			
			//(username, passcode, first_name, last_name, email, role_id)
			String sql = "UPDATE users SET  username = " + username + ", passcode = " + passcode + ", first_name = " + firstName + ", last_name = " + lastName + ", email = " + email +  " WHERE id = " + id + " ";
			Statement stmt = conn.createStatement();
			
			if ( stmt.executeUpdate(sql) == 1) {
				log.info("Update Successful");
				return true;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		log.info("Update failed");
		return false;
	}
	
	public boolean deleteEmployee(Employee e) {
		try {
			Connection conn = ConnectionUtil.getConnection();
			
			int id = e.getId();
			String sql = "DELETE FROM users WHERE id = " + id + "";
			Statement stmt = conn.createStatement();
			if (stmt.executeUpdate(sql) == 1) {
				log.info("Employee Deleted");
				return true;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		log.info("Deletion failed");
		return false;
	}
	
	public boolean deleteManager(Manager m) {
		try {
			Connection conn = ConnectionUtil.getConnection();
			
			int id = m.getId();
			String sql = "DELETE FROM users WHERE id = " + id + "";
			Statement stmt = conn.createStatement();
			if (stmt.executeUpdate(sql) == 1) {
				log.info("Manager Deleted");
				return true;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		log.info("Deletion failed");
		return false;
	}
	
	
}

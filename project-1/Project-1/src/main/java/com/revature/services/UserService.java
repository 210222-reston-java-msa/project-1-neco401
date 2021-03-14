package com.revature.services;

import java.util.List;

import com.revature.daos.TicketDao;
import com.revature.daos.UserDao;
import com.revature.models.ReimburseTicket;
import com.revature.models.User;

public class UserService {
	public static UserDao uDao = new UserDao();
	public static TicketDao tDao = new TicketDao();
	
	public void insertUser(User u) {
		uDao.insert(u);
	}
	
	public void insertTicket(ReimburseTicket t) {
		tDao.insert(t);
	}
	
	public void updateUser(User u) {
		uDao.update(u);
	}
	
	public void updateTicket(ReimburseTicket t) {
		tDao.update(t);
	}
	
	public static User findById(int id) {
		return uDao.findById(id);
	}
	
	public static List<User> findAll(){
		return uDao.findAll();
	}
	
	public static User findByUsername(String username) {
		List<User> all = findAll();
		
		for(User u :all) {
			if (u.getUsername().equals(username)) {
				return u;
			}
		}
		return null;
	}
	
	public static User confirmLogin(String username, String password) {
		User u = findByUsername(username);
		
		if (u == null) {
			return null;
		}
		if(u.getPassword().equals(password)) {
			return u;
		}else {
			return null;
		}
	}

	public static ReimburseTicket findTicketById(int id) {
		return tDao.findById(id);
	}
	
	public static List<ReimburseTicket> findAllTickets(){
		return tDao.findAll();
	}
	
	public static List<ReimburseTicket> findAllByAuthor(int authorId){
		return tDao.findAllByAuthor(authorId);
	}
}

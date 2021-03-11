package com.revature.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.ReimburseTicket;
import com.revature.util.HibernateUtil;

public class TicketDao {
	
	public void insert(ReimburseTicket t) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		
		ses.save(t);
		tx.commit();
	}
	
	public List<ReimburseTicket> selectAll(){
		Session ses = HibernateUtil.getSession();
		
		List<ReimburseTicket> tList = ses.createQuery("from ReimburseTicket", ReimburseTicket.class).list();
		
		return tList;
	}
	
	public ReimburseTicket findByID(int id) {
		Session ses = HibernateUtil.getSession();
		
		ReimburseTicket t = ses.get(ReimburseTicket.class, id);
		
		return t;
	}
	
	public List<ReimburseTicket> findByPending(){
		Session ses = HibernateUtil.getSession();
		
		List<ReimburseTicket> tList = ses.createQuery("from ReimburseTicket where type = 3", ReimburseTicket.class).list();
		
		return tList;
	}
	
	public List<ReimburseTicket> findByResolved(){
		Session ses = HibernateUtil.getSession();
		
		List<ReimburseTicket> tList = ses.createQuery("from ReimburseTicket where type = 1", ReimburseTicket.class).list();
		
		return tList;
	}
	
	public List<ReimburseTicket> findAllTicketsByEmployee(int empId){
		Session ses = HibernateUtil.getSession();
		
		List<ReimburseTicket> tList = ses.createQuery("from ReimburseTicket where authorId = " + empId, ReimburseTicket.class).list();
		
		return tList;
	}
	
	public void update(ReimburseTicket t) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		
		ses.update(t);
		tx.commit();
	}
}

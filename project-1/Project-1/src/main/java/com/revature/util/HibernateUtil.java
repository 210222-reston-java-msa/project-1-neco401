package com.revature.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static Session ses;
	private static SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	
	//Start a session with the DB
	public static Session getSession() {
		if(ses == null) {
			ses = sf.openSession();
		}
		return ses;
	}
	
	//Clean Up
	public static void closeSession() {
		ses.close();
		sf.close();
	}
	
}

package com.revature.tests;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.revature.daos.TicketDao;
import com.revature.daos.UserDao;
import com.revature.models.ReimburseTicket;
import com.revature.models.User;
import com.revature.services.UserService;

public class ServiceTester {
	private UserService userv;
	
	//Mockito Dao
	private UserDao uDao;
	private TicketDao tDao;
	
	//Dao objects to test actual hits to the DB
	private UserDao userDao = new UserDao();
	private TicketDao ticketDao = new TicketDao();
	
	User president = new User(4, "classPrez", "kaguyaDaisuke", "Miyuki", "Shirogane", "SAPresident@shuchiinmail.com", 1, "Manager");
	User vicePresident = new User(2,"IceQueen11", "I<3Shirogane", "Kaguya", "Shinomiya", "SAVicePresident@shuchiinmail.com", 2, "Employee");
	User treasurer = new User(1,"lonelyboi", "makedemsmonies", "Yu", "Ishigami", "SATreasurer@shuchiinmail.com", 1, "Manager");
	User secretary = new User(3, "Ai_detective", "leaveit2me", "Chika", "Fujiwara", "SASecretary@shuchiinmail.com", 2 ,"Employee");
	
	//The first Timestamp will have to updated to the current time the DB script is ran, otherwise there will be errors
	Timestamp timestamp1 = Timestamp.valueOf("2021-03-13 18:36:49.589165");
	Timestamp timestamp2 = Timestamp.valueOf("2016-06-22 19:10:25.0");
	
	ReimburseTicket tick1 = new ReimburseTicket(1, 2.50, timestamp1, "Bus fares", 2, 1, 2);
	ReimburseTicket tick2 = new ReimburseTicket(2, 5377.93, timestamp2, "Disney Lodging", 3, 3, 1);
	ReimburseTicket tick3 = new ReimburseTicket(3, 50, timestamp1, "Stockpile for Culture Festival", 2, 2, 3);
	
	@Before
	public void setup() {
		userv = new UserService();
		
		uDao = mock(UserDao.class);
		tDao = mock(TicketDao.class);
		
		userv.uDao = uDao;
		userv.tDao = tDao;
		
	}
	
	@Test
	public void happyPathScenario() {
		
		User sample = new User(1,"uName", "pass", "first", "last", "email", 2, "Employee");
		
		///create a fake list
		List<User> list = new ArrayList<User>();
		list.add(sample);
		
		when(uDao.findAll()).thenReturn(list);
		
		User foundByUsername = userv.findByUsername(sample.getUsername());
		
		assertEquals(sample, foundByUsername);
	}
	
	@Test
	public void employeeIsNotPresentInDB() {
		List<User> emptylist = new ArrayList<User>();
		
		when(uDao.findAll()).thenReturn(emptylist);
		
		//We are passing thru data that doesnt exist
		User empFoundByUsername = userv.findByUsername("tester");
		
		assertEquals(null, empFoundByUsername);
	}
	
	@Test
	public void findUserById(){
		User presClone = userDao.findById(4);
		
		assertEquals(president, presClone);
	}
	
	@Test
	public void findUserByUsername() {
		User vicePresident = new User(2,"IceQueen11", "I<3Shirogane", "Kaguya", "Shinomiya", "SAVicePresident@shuchiinmail.com", 2, "Employee");
		User vpClone = userDao.findByUsername("IceQueen11");
		
		assertEquals(vicePresident, vpClone);
	}
	
	@Test
	public void findAllUsers() {
		List<User> userList = new ArrayList<User>();
		userList.add(treasurer);
		userList.add(vicePresident);
		userList.add(secretary);
		userList.add(president);
		
		List<User> cloneList = userDao.findAll();
		
		assertEquals(userList, cloneList);
	}
	
	@Test
	public void findTicketById() {
		ReimburseTicket firstTickClone = ticketDao.findById(3);
		
		assertEquals(firstTickClone, tick3);
	}
	
	@Test
	public void findTicketByAuthor() {
		List<ReimburseTicket> cloneList = ticketDao.findAllByAuthor(2);
		
		List<ReimburseTicket> tickList = new ArrayList<ReimburseTicket>();
		tickList.add(tick1);
		tickList.add(tick3);
		
		assertEquals(cloneList, tickList);
	}
	
	@Test
	public void findPendingTickets() {
		List<ReimburseTicket> cloneList = ticketDao.findAllByPending();
		
		List<ReimburseTicket> tickList = new ArrayList<ReimburseTicket>();
		tickList.add(tick1);
		
		assertEquals(cloneList, tickList);
	}
	
	@Test
	public void findAllTickets() {
		List<ReimburseTicket> cloneList = ticketDao.findAll();
		
		List<ReimburseTicket> tickList = new ArrayList<ReimburseTicket>();
		tickList.add(tick1);
		tickList.add(tick2);
		tickList.add(tick3);
		
		assertEquals(cloneList, tickList);
	}
}

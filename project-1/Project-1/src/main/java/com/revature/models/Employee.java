package com.revature.models;

import java.util.ArrayList;
import java.util.List;

public class Employee extends User{
	
	private List<ReimburseTicket> listOfTicks = new ArrayList<ReimburseTicket>();
	
	public Employee() {
		super.setRoleId(2);
	}

	public Employee(int id, String username, String password, String firstName, String lastName, String email) {
		super(id, username, password, firstName, lastName, email, 2);
	}

	public Employee(String username, String password, String firstName, String lastName, String email) {
		super(username, password, firstName, lastName, email, 2);
	}
	
	
}

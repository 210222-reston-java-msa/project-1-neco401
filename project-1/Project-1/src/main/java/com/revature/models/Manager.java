package com.revature.models;


public class Manager extends User{
	
	public Manager() {
		super.setRoleId(1);
	}

	public Manager(int id, String username, String password, String firstName, String lastName, String email) {
		super(id, username, password, firstName, lastName, email, 1);
	}

	public Manager(String username, String password, String firstName, String lastName, String email) {
		super(username, password, firstName, lastName, email, 1);
	}

	
}

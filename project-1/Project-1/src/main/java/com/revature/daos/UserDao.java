package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.User;
import com.revature.util.ConnectionUtil;

public class UserDao {
	private static Logger log = Logger.getLogger(UserDao.class);

	public boolean insert(User u) {

		PreparedStatement stmt = null;

		try {

			Connection conn = ConnectionUtil.getConnection();
			String sql = "INSERT INTO users (username, passcode, first_name, last_name, email, role_id) VALUES (?,?,?, ?, ?, ?)";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, u.getUsername());
			stmt.setString(2, u.getPassword());
			stmt.setString(3, u.getFirstName());
			stmt.setString(4, u.getLastName());
			stmt.setString(5, u.getEmail());
			stmt.setInt(6, u.getRoleId());

			if (!stmt.execute()) {
				return false;
			}

		} catch (SQLException ex) {
			log.warn("Unable to insert user", ex);
			return false;
		}
		// If everything is successful, we return true
		return true;
	}

	public boolean update(User u) {
		PreparedStatement stmt = null;
		int userId = u.getId();
		
		try {
		Connection conn = ConnectionUtil.getConnection();
		String sql = "UPDATE user SET username = " + u.getUsername() + ", passcode = " + u.getPassword() + ", first_name = " + u.getFirstName() +
				 ", last_name = "+ u.getLastName() +", email = " + u.getEmail() + ", roleId = " + u.getRoleId() + " WHERE id = " + userId + " ";
		
			stmt = conn.prepareStatement(sql);
			if (!stmt.execute()) {
				return false;
			}
		} catch (SQLException e) {
			log.warn("Unable to update user " + e);
			e.printStackTrace();
		}
		
		return true;
	}
	
	public User findById(int id) {
		User u = new User();

		Connection conn = ConnectionUtil.getConnection();
		String sql = "SELECT * FROM users INNER JOIN roles ON users.role_id = roles.id WHERE users.id = " + id + "";

		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				u.setId(rs.getInt("id"));
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("passcode"));
				u.setFirstName(rs.getString("first_name"));
				u.setLastName(rs.getString("last_name"));
				u.setEmail(rs.getString("email"));
				u.setRoleId(rs.getInt("role_id"));
			}
			
			if(u.getRoleId() == 1) {
				u.setRoleName("Manager");
			}else if(u.getRoleId() == 2) {
				u.setRoleName("Employee");
			}
			
		} catch (SQLException e) {
			log.warn("Unable to find User by Id" + e);
			e.printStackTrace();
			return null;
		}

		return u;
	}
	
	public User findByUsername (String username) {
		User u = new User();

		Connection conn = ConnectionUtil.getConnection();
		
		String sql = "SELECT * FROM users INNER JOIN roles ON users.role_id = roles.id WHERE users.username = ? ";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				u.setId(rs.getInt(1));
				u.setUsername(username);
				u.setPassword(rs.getString(3));
				u.setFirstName(rs.getString(4));
				u.setLastName(rs.getString(5));
				u.setEmail(rs.getString(6));
				u.setRoleId(rs.getInt("role_id")); 
			}
			
			if(u.getRoleId() == 1) {
				u.setRoleName("Manager");
			}else if(u.getRoleId() == 2) {
				u.setRoleName("Employee");
			}

		} catch (SQLException e) {
			log.warn("Unable to find User by username " + e);
			e.printStackTrace();
			return null;
		}

		return u;
	}

	public List<User> findAll() {

		List<User> list = new ArrayList<User>();

		try {
			
			Connection conn = ConnectionUtil.getConnection();
			
			String sql = "SELECT * FROM users";
			
			PreparedStatement stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String username = rs.getString("username");
				String password = rs.getString("passcode");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String email = rs.getString("email");
				int roleId = rs.getInt("role_id");
				String roleName = "";
				
				if(roleId == 1) {
					roleName = "Manager";
				}else if(roleId == 2) {
					roleName = "Employee";
				}
				
				
				User u = new User(id, username, password, firstName, lastName, email, roleId, roleName);
				list.add(u);
			}

		} catch (SQLException ex) {
			log.warn("Unable to retrieve all users", ex);

		}

		return list;
	}

}

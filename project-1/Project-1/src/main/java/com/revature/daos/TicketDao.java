package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.ReimburseTicket;
import com.revature.util.ConnectionUtil;

public class TicketDao {
	private static Logger log = Logger.getLogger(TicketDao.class);
	
	public boolean insert(ReimburseTicket t) {
		PreparedStatement stmt = null;

		try {

			Connection conn = ConnectionUtil.getConnection();
			String sql = "INSERT INTO reimbursement (amount, submitted, resolved, description, author, resolver, status_id, type_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

			stmt = conn.prepareStatement(sql);
			stmt.setDouble(1, t.getAmount());
			stmt.setTimestamp(2, t.getDateSubmitted());
			stmt.setTimestamp(3, t.getDateResolved());
			stmt.setString(4, t.getDescription());
			stmt.setInt(5, t.getAuthor());
			stmt.setInt(6, t.getResolver());
			stmt.setInt(7, t.getStatus());
			stmt.setInt(8, t.getType());

			if (!stmt.execute()) {
				return false;
			}

		} catch (SQLException ex) {
			log.warn("Unable to insert reimbursement ticket", ex);
			return false;
		}
		
		
		return true;
	}
	
	public boolean update(ReimburseTicket t) {
		PreparedStatement stmt = null;
		int ticketId = t.getId();
		
		try {
		Connection conn = ConnectionUtil.getConnection();
		String sql = "UPDATE reimbursement SET amount = " + t.getAmount() + ", submitted = " + t.getDateSubmitted() + ", resolved = " + t.getDateResolved() +
				 ", description = "+ t.getDescription() +", author = " + t.getAuthor() + ", resolver = " + t.getResolver() + ", status_id = " + t.getStatus() +
				", type_id =" + t.getType() + " WHERE id = " + ticketId + " ";
		
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
	
	public ReimburseTicket findById(int id) {
		ReimburseTicket t = new ReimburseTicket();
		
		Connection conn = ConnectionUtil.getConnection();
		String sql = "SELECT * FROM reimbursement WHERE reimbursement.id = " + id + " ";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				t.setId(id);
				t.setAmount(rs.getDouble("amount"));
				t.setDateSubmitted(rs.getTimestamp("submitted"));
				t.setDateResolved(rs.getTimestamp("resolved"));
				t.setDescription(rs.getString("description"));
				t.setAuthor(rs.getInt("author"));
				t.setResolver(rs.getInt("resolver"));
				t.setStatus(rs.getInt("status_id"));
				t.setType(rs.getInt("type_id"));
			}
			
		} catch (SQLException e) {
			log.warn("Unable to find User by Id" + e);
			e.printStackTrace();
			return null;
		}
		return t;
	}
	
	public List<ReimburseTicket> findAllByAuthor(int authorId) {
		List<ReimburseTicket> ticList = new ArrayList<ReimburseTicket>();
		
		Connection conn = ConnectionUtil.getConnection();
		String sql = "SELECT * FROM reimbursement WHERE reimbursement.author = " + authorId + " ";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				double amount = rs.getDouble("amount");
				java.sql.Timestamp dateSub = rs.getTimestamp("submitted");
				java.sql.Timestamp dateRes = rs.getTimestamp("resolved");
				String description = rs.getString("description");
				int author = rs.getInt("author");
				int resolver = rs.getInt("resolver");
				int status = rs.getInt("status_id");
				int type = rs.getInt("type_id");
				
				ReimburseTicket t = new ReimburseTicket(id, amount, dateSub, dateRes, description, author, resolver, status, type);
				ticList.add(t);
			}
			
		} catch (SQLException e) {
			log.warn("Unable to find User by Id" + e);
			e.printStackTrace();
			return null;
		}
		return ticList;
	}
	
	public List<ReimburseTicket> findAllByPending() {
		List<ReimburseTicket> ticList = new ArrayList<ReimburseTicket>();
		
		Connection conn = ConnectionUtil.getConnection();
		String sql = "SELECT * FROM reimbursement WHERE reimbursement.status_id = 1 ";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				double amount = rs.getDouble("amount");
				java.sql.Timestamp dateSub = rs.getTimestamp("submitted");
				java.sql.Timestamp dateRes = rs.getTimestamp("resolved");
				String description = rs.getString("description");
				int author = rs.getInt("author");
				int resolver = rs.getInt("resolver");
				int status = rs.getInt("status_id");
				int type = rs.getInt("type_id");
				
				ReimburseTicket t = new ReimburseTicket(id, amount, dateSub, dateRes, description, author, resolver, status, type);
				ticList.add(t);
			}
			
		} catch (SQLException e) {
			log.warn("Unable to find User by Id" + e);
			e.printStackTrace();
			return null;
		}
		return ticList;
	}
	
	public List<ReimburseTicket> findAllByAccepted() {
		List<ReimburseTicket> ticList = new ArrayList<ReimburseTicket>();
		
		Connection conn = ConnectionUtil.getConnection();
		String sql = "SELECT * FROM reimbursement WHERE reimbursement.status_id = 2 ";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				double amount = rs.getDouble("amount");
				java.sql.Timestamp dateSub = rs.getTimestamp("submitted");
				java.sql.Timestamp dateRes = rs.getTimestamp("resolved");
				String description = rs.getString("description");
				int author = rs.getInt("author");
				int resolver = rs.getInt("resolver");
				int status = rs.getInt("status_id");
				int type = rs.getInt("type_id");
				
				ReimburseTicket t = new ReimburseTicket(id, amount, dateSub, dateRes, description, author, resolver, status, type);
				ticList.add(t);
			}
			
		} catch (SQLException e) {
			log.warn("Unable to find User by Id" + e);
			e.printStackTrace();
			return null;
		}
		return ticList;
	}
	
	public List<ReimburseTicket> findAllByDenied() {
		List<ReimburseTicket> ticList = new ArrayList<ReimburseTicket>();
		
		Connection conn = ConnectionUtil.getConnection();
		String sql = "SELECT * FROM reimbursement WHERE reimbursement.status_id = 3 ";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				double amount = rs.getDouble("amount");
				java.sql.Timestamp dateSub = rs.getTimestamp("submitted");
				java.sql.Timestamp dateRes = rs.getTimestamp("resolved");
				String description = rs.getString("description");
				int author = rs.getInt("author");
				int resolver = rs.getInt("resolver");
				int status = rs.getInt("status_id");
				int type = rs.getInt("type_id");
				
				ReimburseTicket t = new ReimburseTicket(id, amount, dateSub, dateRes, description, author, resolver, status, type);
				ticList.add(t);
			}
			
		} catch (SQLException e) {
			log.warn("Unable to find User by Id" + e);
			e.printStackTrace();
			return null;
		}
		return ticList;
	}
	
	public List<ReimburseTicket> findAllByResolved() {
		List<ReimburseTicket> ticList = new ArrayList<ReimburseTicket>();
		List<ReimburseTicket> accepted = findAllByAccepted();
		List<ReimburseTicket> denied = findAllByDenied();
		
		for(ReimburseTicket t : accepted) {
			ticList.add(t);
		}
		
		for(ReimburseTicket t : denied) {
			ticList.add(t);
		}
		
		return ticList;
	}

	public List<ReimburseTicket> findAll() {
		List<ReimburseTicket> ticList = new ArrayList<ReimburseTicket>();
		
		Connection conn = ConnectionUtil.getConnection();
		String sql = "SELECT * FROM reimbursement";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				double amount = rs.getDouble("amount");
				java.sql.Timestamp dateSub = rs.getTimestamp("submitted");
				java.sql.Timestamp dateRes = rs.getTimestamp("resolved");
				String description = rs.getString("description");
				int author = rs.getInt("author");
				int resolver = rs.getInt("resolver");
				int status = rs.getInt("status_id");
				int type = rs.getInt("type_id");
				
				ReimburseTicket t = new ReimburseTicket(id, amount, dateSub, dateRes, description, author, resolver, status, type);
				ticList.add(t);
			}
			
		} catch (SQLException e) {
			log.warn("Unable to find User by Id" + e);
			e.printStackTrace();
			return null;
		}
		
		return ticList;
	}
}

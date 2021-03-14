package com.revature.models;

import java.sql.Timestamp;

public class ReimburseTicket {
	private int id;
	private double amount;
	private java.sql.Timestamp dateSubmitted;
	private java.sql.Timestamp  dateResolved;
	private String description;
	private int author;
	private int resolver;
	private int status;
	private int type;
	
	public ReimburseTicket() {
		
	}

	public ReimburseTicket(double amount, java.sql.Timestamp  dateSubmitted, String description, int author, int status,
			int type) {
		super();
		this.amount = amount;
		this.dateSubmitted = dateSubmitted;
		this.description = description;
		this.author = author;
		this.status = status;
		this.type = type;
	}
	
	public ReimburseTicket(int id, double amount, Timestamp dateSubmitted, String description, int author, int status,
			int type) {
		super();
		this.id = id;
		this.amount = amount;
		this.dateSubmitted = dateSubmitted;
		this.description = description;
		this.author = author;
		this.status = status;
		this.type = type;
	}

	public ReimburseTicket(int id, double amount, java.sql.Timestamp  dateSubmitted, java.sql.Timestamp  dateResolved, String description,
			int author, int resolver, int status, int type) {
		super();
		this.id = id;
		this.amount = amount;
		this.dateSubmitted = dateSubmitted;
		this.dateResolved = dateResolved;
		this.description = description;
		this.author = author;
		this.resolver = resolver;
		this.status = status;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public java.sql.Timestamp getDateSubmitted() {
		return dateSubmitted;
	}

	public void setDateSubmitted(java.sql.Timestamp  dateSubmitted) {
		this.dateSubmitted = dateSubmitted;
	}

	public java.sql.Timestamp  getDateResolved() {
		return dateResolved;
	}

	public void setDateResolved(java.sql.Timestamp  dateResolved) {
		this.dateResolved = dateResolved;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getAuthor() {
		return author;
	}

	public void setAuthor(int author) {
		this.author = author;
	}

	public int getResolver() {
		return resolver;
	}

	public void setResolver(int resolver) {
		this.resolver = resolver;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + author;
		result = prime * result + ((dateResolved == null) ? 0 : dateResolved.hashCode());
		result = prime * result + ((dateSubmitted == null) ? 0 : dateSubmitted.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result + resolver;
		result = prime * result + status;
		result = prime * result + type;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReimburseTicket other = (ReimburseTicket) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (author != other.author)
			return false;
		if (dateResolved == null) {
			if (other.dateResolved != null)
				return false;
		} else if (!dateResolved.equals(other.dateResolved))
			return false;
		if (dateSubmitted == null) {
			if (other.dateSubmitted != null)
				return false;
		} else if (!dateSubmitted.equals(other.dateSubmitted))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (resolver != other.resolver)
			return false;
		if (status != other.status)
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ReimburseTicket [id=" + id + ", amount=" + amount + ", dateSubmitted=" + dateSubmitted
				+ ", dateResolved=" + dateResolved + ", desciption=" + description + ", author=" + author + ", resolver="
				+ resolver + ", status=" + status + ", type=" + type + "]";
	}
	
	
}

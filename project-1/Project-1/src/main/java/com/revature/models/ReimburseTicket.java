package com.revature.models;

import java.sql.Timestamp;
import javax.persistence.*;

@Entity
@Table(name="reimburse_ticket")
public class ReimburseTicket {
	@Id //Primary Key
	@Column(name="ticket_id")//column name
	@GeneratedValue(strategy=GenerationType.AUTO) //SERIAL
	private int id;
	@Column(name="amount")
	private double amount = 0.0;
	@Column(name="submitted")
	private Timestamp submitted;
	@Column(name="resolved")
	private Timestamp resolved;
	@Column(name="description")
	private String description;
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="Employee_FK")
	private int authorId;
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="Manager_FK")
	private int resolverId;
	@OneToOne 
	private int statusId;
	@OneToOne
	private int typeId;
	
	public ReimburseTicket() {
		
	}

	public ReimburseTicket(double amount, Timestamp submitted, Timestamp resolved, String description, int status, int type) {
		super();
		this.amount = amount;
		this.submitted = submitted;
		this.resolved = resolved;
		this.description = description;
		this.statusId = status;
		this.typeId = type;
	}

	public ReimburseTicket(int id, double amount, Timestamp submitted, Timestamp resolved, String description,
			int author, int resolver, int status, int type) {
		super();
		this.id = id;
		this.amount = amount;
		this.submitted = submitted;
		this.resolved = resolved;
		this.description = description;
		this.authorId = author;
		this.resolverId = resolver;
		this.statusId = status;
		this.typeId = type;
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

	public Timestamp getSubmitted() {
		return submitted;
	}

	public void setSubmitted(Timestamp submitted) {
		this.submitted = submitted;
	}

	public Timestamp getResolved() {
		return resolved;
	}

	public void setResolved(Timestamp resolved) {
		this.resolved = resolved;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public int getResolverId() {
		return resolverId;
	}

	public void setResolverId(int resolverId) {
		this.resolverId = resolverId;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public int isStatus() {
		return statusId;
	}

	public void setStatus(int status) {
		this.statusId = status;
	}

	public int getType() {
		return typeId;
	}

	public void setType(int type) {
		this.typeId = type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + authorId;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result + ((resolved == null) ? 0 : resolved.hashCode());
		result = prime * result + resolverId;
		result = prime * result + statusId;
		result = prime * result + ((submitted == null) ? 0 : submitted.hashCode());
		result = prime * result + typeId;
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
		if (authorId != other.authorId)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (resolved == null) {
			if (other.resolved != null)
				return false;
		} else if (!resolved.equals(other.resolved))
			return false;
		if (resolverId != other.resolverId)
			return false;
		if (statusId != other.statusId)
			return false;
		if (submitted == null) {
			if (other.submitted != null)
				return false;
		} else if (!submitted.equals(other.submitted))
			return false;
		if (typeId != other.typeId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ReimburseTicket [id=" + id + ", amount=" + amount + ", submitted=" + submitted + ", resolved="
				+ resolved + ", description=" + description + ", authorId=" + authorId + ", resolverId=" + resolverId
				+ ", statusId=" + statusId + ", typeId=" + typeId + "]";
	}

}
package com.revature.models;

import javax.persistence.*;

@Entity
@Table(name="ticket_status")
public class TicketStatus {
	@Id
	@Column(name="status_id")
	private int id;
	
	@Column(name="s_name")
	private String type;
	
	public TicketStatus(int id) {
		if(id==1) {
			this.id = 1;
			this.type= "Approved";
		}else if(id==2){
			this.id = 2;
			this.type = "Denied";
		}else {
			this.id = 3;
			this.type = "Pending";
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		TicketStatus other = (TicketStatus) obj;
		if (id != other.id)
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TicketStatus [id=" + id + ", type=" + type + "]";
	}
	
	
}

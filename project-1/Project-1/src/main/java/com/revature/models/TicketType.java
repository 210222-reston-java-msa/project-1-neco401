package com.revature.models;

import javax.persistence.*;

@Entity
@Table(name="ticket_type")
public class TicketType {
	@Id
	@Column(name="type_id")
	private int id;
	
	@Column(name="t_name")
	private String type;
	
	public TicketType(int id) {
		if(id==1) {
			this.id = 1;
			this.type = "Lodging";
		}else if(id==2) {
			this.id =2;
			this.type = "Travel";
		}else if(id==3) {
			this.id =3;
			this.type = "Food";
		}else {
			this.id =4;
			this.type = "Other";
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
		TicketType other = (TicketType) obj;
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
		return "TicketType [id=" + id + ", type=" + type + "]";
	}
	
	
}

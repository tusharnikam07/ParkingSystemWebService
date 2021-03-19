package com.parkingsystem.api.entities;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Gate implements Entity {
	private int id;
	private String type;
	private String status;

	public Gate(int id, String type, String status) {
		super();
		this.id = id;
		this.type = type;
		this.status = status;
	}

	@Override
	public String toString() {
		return "Gate [id=" + id + ", type=" + type + ", status=" + status + "]";
	}

	public Gate() {
		// TODO Auto-generated constructor stub
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}

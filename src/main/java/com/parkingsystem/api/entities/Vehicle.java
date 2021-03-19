package com.parkingsystem.api.entities;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Vehicle implements Entity {

	private int id;
	private String vehicleNo;
	private String type;
	private int personId;

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Vehicle [id=" + id + ", vehicleNo=" + vehicleNo + ", type=" + type + ", personId=" + personId + "]";
	}

	public Vehicle(int id, String vehicleNo, String type, int personId) {
		super();
		this.id = id;
		this.vehicleNo = vehicleNo;
		this.type = type;
		this.personId = personId;
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

	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	public Vehicle() {
		super();
	}

	public String getVehicleNo() {
		return vehicleNo;
	}

	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

}

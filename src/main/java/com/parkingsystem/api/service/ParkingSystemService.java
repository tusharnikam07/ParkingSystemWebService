package com.parkingsystem.api.service;

import java.util.List;

import com.parkingsystem.api.entities.Element;
import com.parkingsystem.api.entities.Floor;
import com.parkingsystem.api.entities.Gate;
import com.parkingsystem.api.entities.Person;
import com.parkingsystem.api.entities.Spot;
import com.parkingsystem.api.entities.Ticket;
import com.parkingsystem.api.entities.Vehicle;
import com.parkingsystem.api.exception.ParkingException;

public interface ParkingSystemService {

	List<Floor> getFloors() throws ParkingException;

	Floor getFloor(int id) throws ParkingException;

	Gate getGate(int id) throws ParkingException;

	List<Gate> getGates() throws ParkingException;

	List<Person> getPersons() throws ParkingException;

	Person getPerson(int id) throws ParkingException;

	List<Vehicle> getVehicles() throws ParkingException;

	Vehicle getVehicle(int id) throws ParkingException;

	List<Ticket> getTickets() throws ParkingException;

	Ticket getTicket(int id) throws ParkingException;

	int insertFloor(Floor floor) throws ParkingException;

	int insertVehicle(Vehicle vehicle) throws ParkingException;

	int insertPerson(Person person) throws ParkingException;

	int insertTicket(Ticket ticket) throws ParkingException;

	int insertGate(Gate gate) throws ParkingException;

	int insertSpot(Element<Spot> element) throws ParkingException;

	boolean deleteGate(Element<Gate> element) throws ParkingException;

	boolean deleteFloor(Element<Floor> element) throws ParkingException;

	boolean deleteVehicle(Element<Vehicle> element) throws ParkingException;

	boolean deletePerson(Element<Person> element) throws ParkingException;

	boolean deleteSpot(Element<Spot> element) throws ParkingException;

	Boolean updateStatusGate(int id, String Status) throws ParkingException;

	Boolean updateStatusFloor(int id, String status) throws ParkingException;

	Boolean updateStatusSpot(int id, int floorId, String Status) throws ParkingException;
	
	public Boolean updateVehicle(int vehicleId, int personId) throws ParkingException;
	
}

package com.parkingsystem.api.serviceimpl;

import static com.parkingsystem.api.constants.ParkingConstants.PassWord;
import static com.parkingsystem.api.constants.ParkingConstants.URL;
import static com.parkingsystem.api.constants.ParkingConstants.UserName;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.parkingsystem.api.entities.Element;
import com.parkingsystem.api.entities.Floor;
import com.parkingsystem.api.entities.Gate;
import com.parkingsystem.api.entities.Person;
import com.parkingsystem.api.entities.Spot;
import com.parkingsystem.api.entities.Ticket;
import com.parkingsystem.api.entities.Vehicle;
import com.parkingsystem.api.exception.ParkingException;
import com.parkingsystem.api.service.ParkingSystemService;

public class ParkingSystemServiceImpl implements ParkingSystemService {
	Connection connection;

	public ParkingSystemServiceImpl() throws ParkingException {
		try {
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			connection = DriverManager.getConnection(URL, UserName, PassWord);
		} catch (Exception e) {
			throw new ParkingException("Connection could not be created ! Please Check", e);
		}
	}

	private ResultSet getEntities(String DBtableName, int id) throws ParkingException {
		String query = "select * from " + DBtableName;
		if (id != 0) {
			query += " where id = ?";
		}
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			if (id != 0) {
				preparedStatement.setInt(1, id);
			}
			ResultSet resultSet = preparedStatement.executeQuery();
			return resultSet;
		} catch (SQLException e) {
			throw new ParkingException("SOME ERROR OCCURED WHILE FETCHING DATA PLEASE CHECK", e);
		}
	}

	@Override
	public List<Gate> getGates() throws ParkingException {
		List<Gate> gateList = new ArrayList<Gate>();
		ResultSet resultSet = getEntities("GATE", 0);
		try {
			while (resultSet.next()) {
				Gate gate = new Gate();
				gate.setId(resultSet.getInt("ID"));
				gate.setStatus(resultSet.getString("STATUS"));
				gate.setType(resultSet.getString("TYPE"));
				gateList.add(gate);
			}
		} catch (SQLException e) {
			throw new ParkingException("SOME ERROR OCCURED WHILE FETCHING DATA PLEASE CHECK", e);
		}
		return gateList;
	}

	@Override
	public Gate getGate(int id) throws ParkingException {
		if (id != 0) {
			Gate gate = new Gate();
			ResultSet resultSet = getEntities("GATE", id);
			try {
				if (resultSet.next()) {
					gate.setId(resultSet.getInt("ID"));
					gate.setStatus(resultSet.getString("STATUS"));
					gate.setType(resultSet.getString("TYPE"));
				}
			} catch (SQLException e) {
				throw new ParkingException("SOME ERROR OCCURED WHILE FETCHING DATA !! PLEASE CHECK", e);
			}
			return gate;
		}
		return null;
	}

	@Override
	public List<Vehicle> getVehicles() throws ParkingException {
		List<Vehicle> vehicleList = new ArrayList<Vehicle>();
		ResultSet resultSet = getEntities("VEHICLE", 0);

		try {
			while (resultSet.next()) {
				Vehicle vehicle = new Vehicle();
				vehicle.setId(resultSet.getInt("ID"));
				vehicle.setVehicleNo(resultSet.getString("VEHICLENO"));
				vehicle.setType(resultSet.getString("TYPE"));
				vehicle.setPersonId(resultSet.getInt("PERSONID"));
				vehicleList.add(vehicle);
			}
		} catch (SQLException e) {
			throw new ParkingException("SOME ERROR OCCURED WHILE FETCHING DATA PLEASE CHECK", e);
		}
		return vehicleList;
	}

	@Override
	public Vehicle getVehicle(int id) throws ParkingException {
		if (id != 0) {
			Vehicle vehicle = new Vehicle();
			ResultSet resultSet = getEntities("VEHICLE", id);
			try {
				if (resultSet.next()) {
					vehicle.setId(resultSet.getInt("ID"));
					vehicle.setVehicleNo(resultSet.getString("VEHICLENO"));
					vehicle.setType(resultSet.getString("TYPE"));
					vehicle.setPersonId(resultSet.getInt("PERSONID"));

				}
			} catch (SQLException e) {
				throw new ParkingException("SOME ERROR OCCURED WHILE FETCHING DATA !! PLEASE CHECK", e);
			}
			return vehicle;
		}
		return null;
	}

	@Override
	public List<Floor> getFloors() throws ParkingException {

		ResultSet resultSet = getEntities("FLOOR", 0);
		List<Floor> floorList = new ArrayList<Floor>();
		try {
			while (resultSet.next()) {
				Floor floor = new Floor();
				floor.setId(resultSet.getInt("ID"));
				floor.setHeavyCapacity(resultSet.getInt("HEAVYCAPACITY"));
				floor.setLargeCapacity(resultSet.getInt("LARGECAPACITY"));
				floor.setSmallCapacity(resultSet.getInt("SMALLCAPACITY"));
				floor.setStatus(resultSet.getString("STATUS"));
				floor.setTotalCapacity(resultSet.getInt("TOTALCAPACITY"));
				floor.setTwoWheeelerCapacity(resultSet.getInt("TOTALCAPACITY"));
				floorList.add(floor);
			}
		} catch (SQLException e) {
			throw new ParkingException("SOME ERROR OCCURED WHILE FETCHING DATA !! PLEASE CHECK", e);
		}
		return floorList;
	}

	@Override
	public Floor getFloor(int id) throws ParkingException {
		if (id != 0) {
			Floor floor = new Floor();
			ResultSet resultSet = getEntities("FLOOR", id);
			try {
				if (resultSet.next()) {
					floor.setId(resultSet.getInt("ID"));
					floor.setHeavyCapacity(resultSet.getInt("HEAVYCAPACITY"));
					floor.setLargeCapacity(resultSet.getInt("LARGECAPACITY"));
					floor.setSmallCapacity(resultSet.getInt("SMALLCAPACITY"));
					floor.setStatus(resultSet.getString("STATUS"));
					floor.setTotalCapacity(resultSet.getInt("TOTALCAPACITY"));
					floor.setTwoWheeelerCapacity(resultSet.getInt("TOTALCAPACITY"));
				}
			} catch (SQLException e) {
				throw new ParkingException("SOME ERROR OCCURED WHILE FETCHING DATA !! PLEASE CHECK", e);
			}
			return floor;
		}
		return null;
	}

	@Override
	public List<Person> getPersons() throws ParkingException {
		List<Person> personList = new ArrayList<Person>();
		ResultSet resultSet = getEntities("PERSON", 0);
		try {
			while (resultSet.next()) {
				Person person = new Person();
				person.setId(resultSet.getInt("ID"));
				person.setPersonId(resultSet.getString("PERSONID"));
				person.setIdType(resultSet.getString("IDTYPE"));
				person.setFirstName(resultSet.getString("FIRSTNAME"));
				person.setLastName(resultSet.getString("LASTNAME"));
				person.setMobileNo(resultSet.getLong("MOBILENO"));
				person.setAddress(resultSet.getString("ADDRESS"));

				personList.add(person);
			}
		} catch (SQLException e) {
			throw new ParkingException("SOME ERROR OCCURED WHILE FETCHING DATA PLEASE CHECK", e);
		}
		return personList;
	}

	@Override
	public Person getPerson(int id) throws ParkingException {
		if (id != 0) {
			Person person = new Person();
			ResultSet resultSet = getEntities("PERSON", id);
			try {
				if (resultSet.next()) {

					person.setId(resultSet.getInt("ID"));
					person.setPersonId(resultSet.getString("PERSONID"));
					person.setIdType(resultSet.getString("IDTYPE"));
					person.setFirstName(resultSet.getString("FIRSTNAME"));
					person.setLastName(resultSet.getString("LASTNAME"));
					person.setMobileNo(resultSet.getLong("MOBILENO"));
					person.setAddress(resultSet.getString("ADDRESS"));
				}
			} catch (SQLException e) {
				throw new ParkingException("SOME ERROR OCCURED WHILE FETCHING DATA !! PLEASE CHECK", e);
			}
			return person;
		}
		return null;
	}

	@Override
	public List<Ticket> getTickets() throws ParkingException {
		List<Ticket> ticketList = new ArrayList<Ticket>();
		ResultSet resultSet = getEntities("TICKET", 0);
		try {
			while (resultSet.next()) {
				Ticket ticket = new Ticket();
				ticket.setId(resultSet.getInt("ID"));
				ticket.setGateId(resultSet.getInt("GATEID"));
				ticket.setFloorId(resultSet.getInt("FLOORID"));
				ticket.setSpotid(resultSet.getInt("SPOTID"));
				ticket.setVehicleId(resultSet.getInt("VEHICLEID"));
				ticket.setIssueTime(resultSet.getTimestamp("ISSUETIME"));
				ticketList.add(ticket);
			}
		} catch (SQLException e) {
			throw new ParkingException("SOME ERROR OCCURED WHILE FETCHING DATA PLEASE CHECK", e);
		}
		return ticketList;
	}

	public Ticket getTicket(int id) throws ParkingException {
		if (id != 0) {
			Ticket ticket = new Ticket();
			ResultSet resultSet = getEntities("TICKET", id);
			try {
				if (resultSet.next()) {

					ticket.setId(resultSet.getInt("ID"));
					ticket.setGateId(resultSet.getInt("GATEID"));
					ticket.setFloorId(resultSet.getInt("FLOORID"));
					ticket.setSpotid(resultSet.getInt("SPOTID"));
					ticket.setVehicleId(resultSet.getInt("VEHICLEID"));
					ticket.setIssueTime(resultSet.getTimestamp("ISSUETIME"));
				}
			} catch (SQLException e) {
				throw new ParkingException("SOME ERROR OCCURED WHILE FETCHING DATA !! PLEASE CHECK", e);
			}
			return ticket;
		}
		return null;
	}

	private int insert(Element<?> element, String DBTableName) throws ParkingException {
		int generatedId = 0;
		String query = "insert into " + DBTableName + " values(";
		int length = element.getlength();
		for (int i = 0; i < length; i++) {
			if (i == length - 1)
				query += "?)";
			else
				query += "?,";
		}
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query, new String[] { "ID" });
			for (int i = 1; i <= length; i++) {
				Object object = element.getElement();
				if (object.getClass() == String.class)
					preparedStatement.setString(i, (String) object);
				else if (object.getClass() == Integer.class)
					preparedStatement.setInt(i, (Integer) object);
				else if (object.getClass() == Long.class)
					preparedStatement.setLong(i, (Long) object);
				else if (object.getClass() == Double.class)
					preparedStatement.setDouble(i, (Double) object);
				else if (object.getClass() == Time.class)
					preparedStatement.setTime(i, (Time) object);
				else if (object.getClass() == Boolean.class)
					preparedStatement.setBoolean(i, (Boolean) object);
				else if (object.getClass() == Date.class)
					preparedStatement.setDate(i, (Date) object);
				else if (object.getClass() == Timestamp.class) {
					preparedStatement.setTimestamp(i, (Timestamp) object);
				}
			}
			int rowsAffected = preparedStatement.executeUpdate();
			if (rowsAffected > 0) {
				ResultSet rs = preparedStatement.getGeneratedKeys();
				if (rs.next()) {
					System.out.println(rs.getInt(1));
					return rs.getInt(1);

				}
			}
			return generatedId;
		} catch (SQLException e) {
			throw new ParkingException("SOME ERROR OCCURED WHILE INSERTION PLEASE CHECK", e);
		}
	}

	public int insertFloor(Floor floor) throws ParkingException {

		Element<Floor> element = new Element<Floor>();
		element.addElement(floor.getId());
		element.addElement(floor.getTotalCapacity());
		element.addElement(floor.getTwoWheeelerCapacity());
		element.addElement(floor.getSmallCapacity());
		element.addElement(floor.getLargeCapacity());
		element.addElement(floor.getHeavyCapacity());
		element.addElement(floor.getStatus());
		String DBTableName = "floor";
		int id = insert(element, DBTableName);
		return id;
	}

	public int insertVehicle(Vehicle vehicle) throws ParkingException {
		String DBTableName = "vehicle(vehicleNo,type,personId)";
		Element<Vehicle> element = new Element<Vehicle>();
		element.addElement(vehicle.getVehicleNo());
		element.addElement(vehicle.getType());
		element.addElement(vehicle.getPersonId());
		int id = insert(element, DBTableName);
		return id;
	}

	public int insertPerson(Person person) throws ParkingException {
		String DBTableName = "person(personId,idType,firstName,lastName,mobileNo,address)";

		Element<Person> element = new Element<Person>();
		element.addElement(person.getPersonId());
		element.addElement(person.getIdType());
		element.addElement(person.getFirstName());
		element.addElement(person.getLastName());
		element.addElement(person.getMobileNo());
		element.addElement(person.getAddress());
		int id = insert(element, DBTableName);
		return id;
	}

	public int insertTicket(Ticket ticket) throws ParkingException {

		System.out.println("print Hello");
		Element<Ticket> element = new Element<Ticket>();
		element.addElement(ticket.getGateId());
		element.addElement(ticket.getVehicleId());
		element.addElement(ticket.getFloorId());
		element.addElement(ticket.getSpotid());

		element.addElement(new Timestamp(System.currentTimeMillis()));
		String DBTableName = "ticket (gateId, vehicleId, floorId, spotId , IssueTime)";
		int id = insert(element, DBTableName);
		return id;

	}

	public int insertGate(Gate gate) throws ParkingException {
		String DBTableName = "gate";
		Element<Gate> element = new Element<Gate>();
		element.addElement(gate.getId());
		element.addElement(gate.getType());
		element.addElement(gate.getStatus());

		int id = insert(element, DBTableName);
		return id;

	}

	public int insertSpot(Element<Spot> element) throws ParkingException {
		String DBTableName = "spot(id , floorId , type , availability,status)";
		int id = insert(element, DBTableName);
		return id;
	}

	public boolean delete(Element<?> element, String DBTableName) throws ParkingException {
		StringBuilder query = new StringBuilder();
		query.append("delete from ");
		query.append(DBTableName);
		query.append(" where ");
		List<Object> values = new ArrayList<Object>();

		int length = element.getlength();
		for (int i = 0; i < length; i++) {
			Object object = element.getElement();
			if (i % 2 == 0) {
				String parameterName = (String) object;
				query.append(parameterName + " =");
			} else {
				if (i == length - 1)
					query.append(" ? ");
				else
					query.append(" ? and ");
				values.add(object);
			}
		}
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query.toString());
			for (int i = 0; i < values.size(); i++) {
				Object object = values.get(i);
				if (object.getClass() == Integer.class)
					preparedStatement.setInt(i + 1, (Integer) object);
				else if (object.getClass() == String.class)
					preparedStatement.setString(i + 1, (String) object);
				else if (object.getClass() == Long.class)
					preparedStatement.setLong(i + 1, (Long) object);
				else if (object.getClass() == Double.class)
					preparedStatement.setDouble(i + 1, (Double) object);
				else if (object.getClass() == Time.class)
					preparedStatement.setTime(i + 1, (Time) object);
				else if (object.getClass() == Boolean.class)
					preparedStatement.setBoolean(i + 1, (Boolean) object);
			}
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			throw new ParkingException("SOME ERROR OCCURED WHILE REMOVING PLEASE CHECK", e);
		}

	}

	public boolean deleteGate(Element<Gate> element) throws ParkingException {

		String DBTableName = "gate";
		return delete(element, DBTableName);

	}

	public boolean deleteFloor(Element<Floor> element) throws ParkingException {
		String DBTableName = "Floor";
		return delete(element, DBTableName);

	}

	public boolean deleteVehicle(Element<Vehicle> element) throws ParkingException {
		String DBTableName = "Vehicle";
		return delete(element, DBTableName);

	}

	public boolean deletePerson(Element<Person> element) throws ParkingException {
		String DBTableName = "Person";
		return delete(element, DBTableName);

	}

	public boolean deleteSpot(Element<Spot> element) throws ParkingException {
		String DBTableName = "spot";
		boolean bool = delete(element, DBTableName);
		return bool;
	}

	private Boolean updateStatus(String DBtableName, String status, int id, String columnName, Object object)
			throws ParkingException {
		String query = "update " + DBtableName + " set status= ? where id = ?";
		if (!columnName.equals("")) {
			query += " and " + columnName + " = ?";
		}

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, status);
			preparedStatement.setInt(2, id);
			if (!columnName.equals("")) {
				if (object.getClass() == String.class)
					preparedStatement.setString(3, (String) object);
				else if (object.getClass() == Integer.class)
					preparedStatement.setInt(3, (Integer) object);
				else if (object.getClass() == Long.class)
					preparedStatement.setLong(3, (Long) object);
				else if (object.getClass() == Double.class)
					preparedStatement.setDouble(3, (Double) object);
				else if (object.getClass() == Time.class)
					preparedStatement.setTime(3, (Time) object);
				else if (object.getClass() == Boolean.class)
					preparedStatement.setBoolean(3, (Boolean) object);
			}

			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			throw new ParkingException("SOME ERROR OCCURED WHILE INSERTION PLEASE CHECK", e);

		}
	}

	public Boolean updateStatusGate(int id, String Status) throws ParkingException {
		String DBTableName = "gate";
		return updateStatus(DBTableName, Status, id, "", null);
	}

	public Boolean updateStatusFloor(int id, String status) throws ParkingException {
		String DBTableName = "floor";
		boolean bool = updateStatus(DBTableName, status, id, "", null);

		String query = "update spot set status = ? where floorId = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, status);
			preparedStatement.setInt(2, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return bool;

	}

	public Boolean updateStatusSpot(int id, int floorId, String Status) throws ParkingException {
		String DBTableName = "spot";
		return updateStatus(DBTableName, Status, id, "floorId", floorId);
	}

	public Boolean updateVehicle(int vehicleId, int personId) throws ParkingException {
		String query = "update vehicle set personId = ? where id = ?";

		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, personId);
			preparedStatement.setInt(2, vehicleId);
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			throw new ParkingException("SOME ERROR OCCURED WHILE INSERTION PLEASE CHECK", e);
		}

	}

}

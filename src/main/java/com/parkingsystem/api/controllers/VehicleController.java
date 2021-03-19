package com.parkingsystem.api.controllers;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.parkingsystem.api.entities.Element;
import com.parkingsystem.api.entities.Vehicle;
import com.parkingsystem.api.exception.ParkingException;
import com.parkingsystem.api.service.ParkingSystemService;
import com.parkingsystem.api.serviceimpl.ParkingSystemServiceImpl;

@Path("vehicles")
public class VehicleController {
	ParkingSystemService parkingSystemService;

	public VehicleController() throws ParkingException {
		parkingSystemService = new ParkingSystemServiceImpl();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getVehicles() throws ParkingException {
		List<Vehicle> vehicleList = parkingSystemService.getVehicles();
		return Response.ok().entity("{\"vehicles\":\"" + vehicleList + "\"}").build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getVehicle(@PathParam("id") int id) throws ParkingException {
		Vehicle vehicle = parkingSystemService.getVehicle(id);
		return Response.ok().entity(vehicle).build();

	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response addVehicle(Vehicle vehicle) throws ParkingException {

		int id = parkingSystemService.insertVehicle(vehicle);
		return Response.created(null).entity(String.valueOf(id)).build();
	}

	@DELETE
	@Path("/{id}")
	@Produces("text/plain")
	public Response removeVehicle(@PathParam("id") int id) throws ParkingException {
		Element<Vehicle> element = new Element<Vehicle>();
		element.addElement("id");
		element.addElement(id);
		if (parkingSystemService.deleteVehicle(element)) {
			return Response.ok().entity("person " + id + " deleted succesfully").build();
		} else {
			return Response.serverError().entity("Some error while deletion , Please Check").build();
		}
	}

	@PUT
	@Path("/{id}")
	@Produces("text/plain")
	public Response updateVehicle(@PathParam("id") int id, Vehicle vehicle) throws ParkingException {
		if (parkingSystemService.updateVehicle(id, vehicle.getPersonId())) {
			return Response.ok().entity("Status of Vehicle Updated Successfully").build();
		} else {
			return Response.serverError().entity("Error while updating vehicle").build();
		}
	}
}

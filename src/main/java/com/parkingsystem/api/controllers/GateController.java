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
import com.parkingsystem.api.entities.Gate;
import com.parkingsystem.api.exception.ParkingException;
import com.parkingsystem.api.service.ParkingSystemService;
import com.parkingsystem.api.serviceimpl.ParkingSystemServiceImpl;

@Path("gates")
public class GateController {

	ParkingSystemService parkingSystemService;

	public GateController() throws ParkingException {
		parkingSystemService = new ParkingSystemServiceImpl();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getGates() throws ParkingException {
		List<Gate> gateList = parkingSystemService.getGates();
		return Response.ok().entity("{\"gates\":\"" + gateList + "\"}").build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getGate(@PathParam("id") int id) throws ParkingException {
		Gate gate = parkingSystemService.getGate(id);
		return Response.ok().entity(gate).build();

	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response addGate(Gate gate) throws ParkingException {

		int id = parkingSystemService.insertGate(gate);
		return Response.created(null).entity(String.valueOf(id)).build();
	}

	@DELETE
	@Path("/{id}")
	@Produces("text/plain")
	public Response removeGate(@PathParam("id") int id) throws ParkingException {
		Element<Gate> element = new Element<Gate>();
		element.addElement("id");
		element.addElement(id);
		if (parkingSystemService.deleteGate(element)) {
			return Response.ok().entity("gate " + id + " deleted succesfully").build();
		} else {
			return Response.serverError().entity("Some error while deletion , Please Check").build();
		}
	}

	@PUT
	@Path("/{id}")
	@Produces("text/plain")
	public Response updateGateStatus(@PathParam("id") int id, Gate gate) throws ParkingException {

		if (parkingSystemService.updateStatusGate(id, gate.getStatus())) {
			return Response.ok().entity("Status of Gate Updated Successfully").build();
		} else {
			return Response.serverError().entity("Error while updating Gate").build();
		}
	}

}

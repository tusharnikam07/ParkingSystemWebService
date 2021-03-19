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
import com.parkingsystem.api.entities.Floor;
import com.parkingsystem.api.exception.ParkingException;
import com.parkingsystem.api.service.ParkingSystemService;
import com.parkingsystem.api.serviceimpl.ParkingSystemServiceImpl;

@Path("/floors")
public class FloorController {

	ParkingSystemService parkingSystemService;

	public FloorController() throws ParkingException {
		parkingSystemService = new ParkingSystemServiceImpl();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getFloors() throws ParkingException {
		List<Floor> floorList = parkingSystemService.getFloors();
		return Response.ok().entity("{\"floorList\":\"" + floorList + "\"}").build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getFloor(@PathParam("id") int id) throws ParkingException {
		Floor floor = parkingSystemService.getFloor(id);
		return Response.ok().entity(floor).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response addFloor(Floor floor) throws ParkingException {

		int id = parkingSystemService.insertFloor(floor);
		return Response.created(null).entity(String.valueOf(id)).build();
	}

	@DELETE
	@Path("/{id}")
	@Produces("text/plain")
	public Response removeFloor(@PathParam("id") int id) throws ParkingException {
		System.out.println(id);
		Element<Floor> element = new Element<Floor>();
		element.addElement("id");
		element.addElement(id);
		if (parkingSystemService.deleteFloor(element)) {
			return Response.ok().entity("floor " + id + "deleted succesfully").build();
		} else {
			return Response.serverError().entity("Some error while deletion , Please Check").build();
		}
	}

	@PUT
	@Path("/{id}")
	@Produces("text/plain")
	public Response updateFloorStatus(@PathParam("id") int id, Floor floor) throws ParkingException {
		if (parkingSystemService.updateStatusFloor(id, floor.getStatus())) {
			return Response.ok().entity("Status of Floor Updated Successfully").build();
		} else {
			return Response.serverError().entity("Error while updating Floor").build();
		}
	}

}

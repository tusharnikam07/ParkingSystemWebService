package com.parkingsystem.api.controllers;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.parkingsystem.api.entities.Ticket;
import com.parkingsystem.api.exception.ParkingException;
import com.parkingsystem.api.service.ParkingSystemService;
import com.parkingsystem.api.serviceimpl.ParkingSystemServiceImpl;

@Path("tickets")
public class TicketController {

	ParkingSystemService parkingSystemService;

	public TicketController() throws ParkingException {
		parkingSystemService=new ParkingSystemServiceImpl();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTickets() throws ParkingException {
		List<Ticket> ticketList = parkingSystemService.getTickets();
		return Response.ok().entity("{\"Tickets\":\"" + ticketList + "\"}").build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTicket(@PathParam("id") int id) throws ParkingException {
		Ticket ticket = parkingSystemService.getTicket(id);
		return Response.ok().entity(ticket).build();

	}
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response addTicket(Ticket ticket) throws ParkingException {

		int id = parkingSystemService.insertTicket(ticket);
		return Response.created(null).entity(String.valueOf(id)).build();
	}
}

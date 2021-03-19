package com.parkingsystem.api.controllers;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.parkingsystem.api.entities.Element;
import com.parkingsystem.api.entities.Person;
import com.parkingsystem.api.exception.ParkingException;
import com.parkingsystem.api.service.ParkingSystemService;
import com.parkingsystem.api.serviceimpl.ParkingSystemServiceImpl;

@Path("persons")
public class PersonController {
	ParkingSystemService parkingSystemService;

	public PersonController() throws ParkingException {
		parkingSystemService = new ParkingSystemServiceImpl();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPersons() throws ParkingException {
		List<Person> personList = parkingSystemService.getPersons();
		return Response.ok().entity("{\"persons\":\"" + personList + "\"}").build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getGate(@PathParam("id") int id) throws ParkingException {
		Person person = parkingSystemService.getPerson(id);
		return Response.ok().entity(person).build();

	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response addPerson(Person person) throws ParkingException {

		int id = parkingSystemService.insertPerson(person);
		return Response.created(null).entity(String.valueOf(id)).build();
	}

	@DELETE
	@Path("/{id}")
	@Produces("text/plain")
	public Response removePerson(@PathParam("id") int id) throws ParkingException {
		Element<Person> element = new Element<Person>();
		element.addElement("id");
		element.addElement(id);
		if (parkingSystemService.deletePerson(element)) {
			return Response.ok().entity("person " + id + " deleted succesfully").build();
		} else {
			return Response.serverError().entity("Some error while deletion , Please Check").build();
		}
	}

}

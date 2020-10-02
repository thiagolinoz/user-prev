package br.com.linoz.api;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.linoz.api.entity.User;

@Path("/person")
public class PersonResource {

	private Map<Integer, User> users = new HashMap<Integer, User>();
	
	@RolesAllowed("ADMIN")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public String addPerson(User user) {
		if (users.containsKey(user.getId())) {
			throw new IllegalArgumentException("ID already exist");
		}
		users.put(user.getId(), user);
		
		return "The person " + user + " was created";
	}
	
	@RolesAllowed("ADMIN")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public String updatePerson(User user) {
		if (user.getId() == null) {
			throw new IllegalArgumentException("Invalid ID supplied");
		}
		users.put(user.getId(), user);
		
		return "The person " + user + " was updated";
	}
	
	@RolesAllowed({"ADMIN", "USER"})
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String findAllPerson() {
		return users.toString();
	}
	
	@RolesAllowed({"ADMIN", "USER"})
	@GET
	@Path("/{personId}")
	@Produces(MediaType.APPLICATION_JSON)
	public String findPersonById(@PathParam("personId") int personId) {
		if (users.get(personId) == null) {
			throw new NotFoundException("Person not found"); 
		}
		return users.get(personId).toString();
	}
	
	@RolesAllowed("ADMIN")
	@DELETE
	@Path("/{personId}")
	@Produces(MediaType.APPLICATION_JSON)
	public String deletePerson(@PathParam("personId") int personId) {
		if (users.get(personId) == null) {
			throw new NotFoundException("Person not found"); 
		}
		users.remove(personId);
		
		return users.toString();
	}
}

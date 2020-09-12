package br.com.linoz.api;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
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

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public String addPerson(User user) {
		users.put(user.getId(), user);

		return "The person " + user + " was created";
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public String updatePerson(User user) {
		users.put(user.getId(), user);
		
		return "The person " + user + " was updated";
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String findAllPerson() {
		return users.toString();
	}
	
	@GET
	@Path("/{personId}")
	@Produces(MediaType.APPLICATION_JSON)
	public String findPersonById(@PathParam("personId") int personId) {
		return users.get(personId).toString();		
	}
	
	@DELETE
	@Path("/{personId}")
	@Produces(MediaType.APPLICATION_JSON)
	public String deletePerson(@PathParam("personId") int personId) {
		users.remove(personId);
		
		return users.toString();
	}
}

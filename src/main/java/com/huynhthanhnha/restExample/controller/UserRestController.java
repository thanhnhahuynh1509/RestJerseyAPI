package com.huynhthanhnha.restExample.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.huynhthanhnha.restExample.entity.Link;
import com.huynhthanhnha.restExample.entity.User;
import com.huynhthanhnha.restExample.exception.NotFoundException;
import com.huynhthanhnha.restExample.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Api("/users service")
@SwaggerDefinition(tags= {@Tag(name="users service", description="Rest endpoint for users")})
public class UserRestController {

	private final UserService userService;
	
	@Context
	private UriInfo uri;
	
	public UserRestController() {
		this.userService = new UserService();
	}
	
	@GET
	public Response users() {
		
		List<User> users = userService.getUsers();
		users.forEach(user -> {
			List<Link> links = setLinks(uri, user);
			user.setLinks(links);
		});
		return Response.ok().entity(users).build();
	}
	
	@GET
	@Path("/{id}")
	public Response user(@PathParam("id") Integer id) {
		User user = userService.getUser(id);
		
		if(user == null)
			throw new NotFoundException("Not found user with id: " + id);
		
		List<Link> links = setLinks(uri, user);
		user.setLinks(links);
		return Response.ok(user).build();
	}
	
	@POST
	public Response save(User user) {
		userService.save(user);
		List<Link> links = setLinks(uri, user);
		
		user.setLinks(links);
		return Response.status(201).entity(user).build();
	}
	
	@PUT
	@Path("/{id}")
	public Response update(@PathParam("id") Integer id, User user) {
		if(!checkUserExistsById(id))
			throw new NotFoundException("Not found User with id: " + id);
		userService.update(id, user);
		List<Link> links = setLinks(uri, user);
		user.setLinks(links);
		return Response.status(200).entity(user).build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") Integer id) {
		if(!checkUserExistsById(id))
			throw new NotFoundException("Not found User with id: " + id);
		userService.delete(id);
		return Response.ok().entity("Delete user with id: " + id + " successfully").build();
	}
	
	private boolean checkUserExistsById(Integer id) {
		User user = userService.getUser(id);
		
		return user != null;
	}

	private List<Link> setLinks(UriInfo uri, User user) {
		String selfLink = uri.getBaseUriBuilder()
				.path(UserRestController.class)
				.path(UserRestController.class, "user")
				.resolveTemplate("id", user.getId()).toString();
		
		String usersLink = uri.getBaseUriBuilder()
				.path(UserRestController.class).toString();
		
		Link self = new Link(selfLink, "self");
		Link users = new Link(usersLink, "users");
		
		List<Link> links = new ArrayList<>();
		links.add(self);
		links.add(users);
		
		return links;
	}

}

package com.github.alissonmelonascimento;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.github.alissonmelonascimento.model.User;
import com.github.alissonmelonascimento.service.UserService;

@Path("/users")
public class UserResource {
	
	@Inject
	UserService userService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> hello() {
        return userService.getUsers();
    }
}
package com.github.alissonmelonascimento;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.opentracing.Traced;

import com.github.alissonmelonascimento.model.User;
import com.github.alissonmelonascimento.service.CadastroUsuarioService;
import com.github.alissonmelonascimento.service.UserService;

@Traced
@Path("/users")
public class UserResource {
	
	@Inject
	UserService userService;
	
	@Inject
	CadastroUsuarioService cadastroUsuarioService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> hello() {
        return userService.getUsers();
    }
    
    @POST
    @Transactional(rollbackOn = RuntimeException.class)
    public String vincularRoles() throws RuntimeException{
    	
    	try {
    		cadastroUsuarioService.vincularTodasRoles();
    		return "Concluido";
    	}catch(Exception e) {
    		throw new RuntimeException(e);
    	}
    }    
}
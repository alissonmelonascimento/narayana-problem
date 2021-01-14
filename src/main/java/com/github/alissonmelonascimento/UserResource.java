package com.github.alissonmelonascimento;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.opentracing.Traced;

import com.github.alissonmelonascimento.model.User;
import com.github.alissonmelonascimento.model.UserRole;
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
    @Tag(name = "Retorna Usuarios")
    public List<User> hello() {
        return userService.getUsers();
    }
    
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Transactional(rollbackOn = RuntimeException.class)
    @Tag(name = "Vincula todas as roles aos usuarios")
    public String vincularRoles() throws RuntimeException{
    	
    	try {
    		cadastroUsuarioService.vincularTodasRoles();
    		return "Concluido";
    	}catch(Exception e) {
    		throw new RuntimeException(e);
    	}
    }
    
    @GET
    @Path("cadastros")
    @Produces(MediaType.APPLICATION_JSON)
    @Tag(name = "Retorna todos os cadastros")
    public List<UserRole> getAllCadastros() {
        return cadastroUsuarioService.getAllCadastrados();
    }    
}
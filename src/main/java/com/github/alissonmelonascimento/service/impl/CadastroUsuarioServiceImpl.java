package com.github.alissonmelonascimento.service.impl;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.opentracing.Traced;

import com.github.alissonmelonascimento.model.Role;
import com.github.alissonmelonascimento.model.User;
import com.github.alissonmelonascimento.model.UserRole;
import com.github.alissonmelonascimento.repository.RoleRepository;
import com.github.alissonmelonascimento.repository.UserRepository;
import com.github.alissonmelonascimento.repository.UserRoleRepository;
import com.github.alissonmelonascimento.service.CadastroUsuarioService;

@Traced
@ApplicationScoped
public class CadastroUsuarioServiceImpl implements CadastroUsuarioService {
	
	@Inject
	UserRepository userRepository;
	
	@Inject
	RoleRepository roleRepository;
	
	@Inject
	UserRoleRepository userRoleRepository;

	@Override
	public void vincularTodasRoles() {

		userRoleRepository.deleteAll();
		
		List<Role> roles = roleRepository.findAll();
		List<User> users = userRepository.findAll();
		for(int i=0; i < users.size(); i++) {
			for(Role r : roles) {
				UserRole userRole = new UserRole();
				userRole.setRoleId(r.getId());
				userRole.setUserId(users.get(i).getId());
				userRoleRepository.insert(userRole);
			}
			
			/*if(i == users.size() - 2) {
				throw new RuntimeException("Erro forçado para testar transacao!");
			}*/
		}

	}

}

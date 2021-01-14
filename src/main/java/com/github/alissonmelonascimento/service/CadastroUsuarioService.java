package com.github.alissonmelonascimento.service;

import java.util.List;

import com.github.alissonmelonascimento.model.UserRole;

public interface CadastroUsuarioService {
	
	void vincularTodasRoles();
	List<UserRole> getAllCadastrados();

}

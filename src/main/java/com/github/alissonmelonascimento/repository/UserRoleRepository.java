package com.github.alissonmelonascimento.repository;

import java.util.List;

import com.github.alissonmelonascimento.model.UserRole;

public interface UserRoleRepository {
	
	void deleteAll();
	void insert(UserRole userRole);
	List<UserRole> findByUsuario(Integer userId);

}

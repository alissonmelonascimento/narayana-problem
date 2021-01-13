package com.github.alissonmelonascimento.repository;

import java.util.List;

import com.github.alissonmelonascimento.model.Role;

public interface RoleRepository {
	
	void insert(Role role);
	List<Role> findAll();

}

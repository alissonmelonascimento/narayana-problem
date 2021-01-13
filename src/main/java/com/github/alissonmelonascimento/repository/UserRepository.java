package com.github.alissonmelonascimento.repository;

import java.util.List;

import com.github.alissonmelonascimento.model.User;

public interface UserRepository {
	
	/**
	 * Retorna todos os usuarios
	 * 
	 * @return
	 */
	List<User> findAll();

}

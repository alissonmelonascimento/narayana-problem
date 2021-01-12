package com.github.alissonmelonascimento.service.impl;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.github.alissonmelonascimento.model.User;
import com.github.alissonmelonascimento.repository.UserRepository;
import com.github.alissonmelonascimento.service.UserService;

@ApplicationScoped
public class UserServiceImpl implements UserService {
	
	@Inject
	UserRepository userRepository;

	@Override
	public List<User> getUsers() {
		//FAZ ALGO...
		return userRepository.getUsers();
	}

}

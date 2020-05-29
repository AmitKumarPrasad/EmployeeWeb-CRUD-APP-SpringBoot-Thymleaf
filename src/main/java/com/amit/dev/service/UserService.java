package com.amit.dev.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amit.dev.model.User;
import com.amit.dev.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public User newRegistration(User user) {
		return repository.save(user);
	}

	public List<User> getAll() {
		return repository.findAll();
	}

	public User getbyUserId(Long id) {
		return repository.getOne(id);
	}

	public User updatebyId(Long id) {
		return repository.getOne(id);
	}

	public void deletebyId(Long id) {
		repository.deleteById(id);
	}

}

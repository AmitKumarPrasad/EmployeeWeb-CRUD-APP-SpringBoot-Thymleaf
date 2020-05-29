package com.amit.dev.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amit.dev.model.User;
import com.amit.dev.service.UserService;

@RestController
@RequestMapping("v1/api")
public class UserController {

	@Autowired
	private UserService service;

	@GetMapping("/all")
	public List<User> findAllUsers() {
		return service.getAll();
	}

	@PostMapping("/save")
	public ResponseEntity<String> saveUser(@Valid @RequestBody User user) {
		service.newRegistration(user);
		return new ResponseEntity<String>("Save The User", HttpStatus.CREATED);
	}

	@GetMapping("/byId/{id}")
	public ResponseEntity<User> findbyUserId(@PathVariable(value = "id") Long id) {
		User user = service.getbyUserId(id);
		if (user == null) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<User>(user,HttpStatus.OK);
	}

	@PutMapping("/user/{id}")
	public ResponseEntity<User> updateByUser(@PathVariable(value = "id") Long id, @RequestBody User user) {
		User update = service.updatebyId(id);
		if (update == null) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}

		update.setName(user.getName());
		update.setCountry(user.getCountry());
		update.setEmail(user.getEmail());

		User theUpdate = service.newRegistration(update);
		return new ResponseEntity<User>(theUpdate, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable(value = "id") Long id) {
		service.deletebyId(id);
		return new ResponseEntity<String>("delete Records", HttpStatus.OK);
	}

}

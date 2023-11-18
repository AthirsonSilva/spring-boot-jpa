package com.app.core.user.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.core.user.dto.UserDto;
import com.app.core.user.models.User;
import com.app.core.user.payload.UserPayload;
import com.app.core.user.services.UserServices;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
	private final UserServices userServices;
	private final ModelMapper modelMapper;

	@PostMapping
	public ResponseEntity<UserDto> createUser(@RequestBody UserPayload request) {
		User user = userServices.createUser(request);
		UserDto userDto = modelMapper.map(user, UserDto.class);

		return new ResponseEntity<>(userDto, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<Iterable<User>> getUsers() {
		return new ResponseEntity<>(userServices.getUsers(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getUser(@PathVariable String id) {
		return new ResponseEntity<>(userServices.getUser(id), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable String id) {
		userServices.deleteUser(id);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody UserPayload request) {
		return new ResponseEntity<>(userServices.updateUser(id, request), HttpStatus.OK);
	}

	@GetMapping("/test")
	public ResponseEntity<?> test() {
		UserDto userDto = new UserDto();
		userDto.setName("test");
		userDto.setAge(1);

		return new ResponseEntity<>(userDto, HttpStatus.OK);
	}
}

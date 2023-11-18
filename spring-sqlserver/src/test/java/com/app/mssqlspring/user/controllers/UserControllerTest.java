package com.app.mssqlspring.user.controllers;

import static org.mockito.Mockito.when;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.app.core.user.controllers.UserController;
import com.app.core.user.models.User;
import com.app.core.user.payload.UserPayload;
import com.app.core.user.services.UserServices;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {
	@InjectMocks
	private UserController userController;

	@Mock
	private UserServices userServices;

	// @Test
	// void createUser() {
	// UserPayload userPayload = new UserPayload("John", 20);

	// User user = new User();
	// user.setName(userPayload.name());
	// user.setAge(userPayload.age());

	// when(userServices.createUser(userPayload)).thenReturn(user);

	// ResponseEntity<User> response = userController.createUser(userPayload);

	// Assertions.assertEquals(user, response.getBody());
	// Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
	// }

	@Test
	void getUsers() {
		User user = new User();
		user.setName("John");
		user.setAge(20);

		when(userServices.getUsers()).thenReturn(List.of(user));

		ResponseEntity<Iterable<User>> response = userController.getUsers();
		Assertions.assertEquals(List.of(user), response.getBody());
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	void getUser() {
		String id = UUID.randomUUID().toString();

		User user = new User();
		user.setId(id);
		user.setName("John");
		user.setAge(20);

		when(userServices.getUser(id)).thenReturn(user);

		ResponseEntity<User> response = userController.getUser(id);
		Assertions.assertEquals(user, response.getBody());
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	void deleteUser() {
		String id = UUID.randomUUID().toString();

		ResponseEntity<?> response = userController.deleteUser(id);
		Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}

	@Test
	void updateUser() {
		String id = UUID.randomUUID().toString();

		UserPayload userPayload = new UserPayload("John", 20);

		User user = new User();
		user.setId(id);
		user.setName(userPayload.name());
		user.setAge(userPayload.age());

		when(userServices.updateUser(id, userPayload)).thenReturn(user);

		ResponseEntity<User> response = userController.updateUser(id, userPayload);
		Assertions.assertEquals(user, response.getBody());
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
	}
}
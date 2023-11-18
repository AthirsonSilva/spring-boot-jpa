package com.app.mssqlspring.user.services;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.app.core.user.models.User;
import com.app.core.user.payload.UserPayload;
import com.app.core.user.repositories.UserRepository;
import com.app.core.user.services.UserServices;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServicesTest {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserServices userServices;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void createUser() {
		UserPayload userPayload = generateUserDTO();

		User user = new User();

		user.setName(userPayload.name());
		user.setAge(userPayload.age());

		when(userRepository.save(any(User.class))).thenReturn(user);

		User savedUser = userServices.createUser(userPayload);

		assertEquals(user, savedUser, "User should be saved");

		verify(userRepository, times(1)).save(any());
	}

	public static UserPayload generateUserDTO() {
		return new UserPayload(
				"John",
				20);
	}

	@Test
	void getUsers() {
		List<User> users = new ArrayList<>();

		users.add(User.builder().name("John").age(20).build());
		users.add(User.builder().name("Jane").age(21).build());

		when(userRepository.findAll()).thenReturn(users);

		Iterable<User> savedUsers = userServices.getUsers();

		assertEquals(users, savedUsers, "Users should be returned");

		verify(userRepository, times(1)).findAll();
	}

	@Test
	void getUser() {
		String id = String.valueOf(UUID.randomUUID());
		User user = User.builder().id(id).name("John").age(20).build();

		when(userRepository.findById(any())).thenReturn(Optional.of(user));

		User savedUser = userServices.getUser(id);

		assertEquals(user, savedUser, "User should be returned");

		verify(userRepository, times(1)).findById(any());
	}

	@Test
	void deleteUser() {
		String id = String.valueOf(UUID.randomUUID());
		User user = User.builder().id(id).name("John").age(20).build();

		when(userRepository.findById(any())).thenReturn(Optional.of(user));

		userServices.deleteUser(id);

		verify(userRepository, times(1)).findById(any());
		verify(userRepository, times(1)).deleteById(any());
	}

	@Test
	void updateUser() {
		String id = String.valueOf(UUID.randomUUID());
		User user = User.builder().id(id).name("John").age(20).build();

		UserPayload userPayload = new UserPayload(
				user.getName(),
				user.getAge());

		when(userRepository.findById(id)).thenReturn(Optional.of(user));
		when(userRepository.save(any(User.class))).thenReturn(user);

		User savedUser = userServices.updateUser(id, userPayload);

		assertEquals(user, savedUser, "User should be updated");

		verify(userRepository, times(1)).findById(id);
		verify(userRepository, times(1)).save(any(User.class));
	}
}
package com.app.mssqlspring.repositories;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.app.core.user.models.User;
import com.app.core.user.repositories.UserRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserRepositoryTest {

	@Mock
	private UserRepository userRepository;

	@Test
	public void testFindByName() {
		// create a user object with the name "John"
		User user = new User();
		user.setName("John");
		// mock the behavior of the findByName method
		when(userRepository.findByName(anyString())).thenReturn(user);
		// find the user by name
		User foundUser = userRepository.findByName("John");
		// assert that the found user is not null and has the same name as the saved
		// user
		assertNotNull(foundUser);
		assertEquals("John", foundUser.getName());
	}

	@Test
	public void testCreate() {
		// create a user object with the name "John"
		User user = new User();
		user.setName("John");
		// mock the behavior of the save method
		when(userRepository.save(user)).thenReturn(user);
		// save the user
		User savedUser = userRepository.save(user);
		// assert that the saved user is not null and has the same name as the saved
		// user
		assertNotNull(savedUser);
		assertEquals("John", savedUser.getName());
	}

	@Test
	public void testUpdate() {
		// create a user object with the name "John"
		User user = new User();
		user.setName("John");
		// mock the behavior of the save method
		when(userRepository.save(user)).thenReturn(user);
		// save the user
		User savedUser = userRepository.save(user);
		// assert that the saved user is not null and has the same name as the saved
		// user
		assertNotNull(savedUser);
		assertEquals("John", savedUser.getName());
		// update the user
		savedUser.setName("John Doe");
		// mock the behavior of the save method
		when(userRepository.save(savedUser)).thenReturn(savedUser);
		// save the updated user
		User updatedUser = userRepository.save(savedUser);
		// assert that the updated user is not null and has the same name as the updated
		// user
		assertNotNull(updatedUser);
		assertEquals("John Doe", updatedUser.getName());
	}

	@Test
	public void testDelete() {
		// create a user object with the name "John"
		User user = new User();
		user.setName("John");
		// mock the behavior of the save method
		when(userRepository.save(user)).thenReturn(user);
		// save the user
		User savedUser = userRepository.save(user);
		// assert that the saved user is not null and has the same name as the saved
		// user
		assertNotNull(savedUser);
		assertEquals("John", savedUser.getName());

		doNothing().when(userRepository).deleteById(savedUser.getId());
		userRepository.deleteById(savedUser.getId());

		// assert that the saved user is not null and has the same name as the saved
		// user
		verify(userRepository, times(1)).deleteById(savedUser.getId());
	}
}
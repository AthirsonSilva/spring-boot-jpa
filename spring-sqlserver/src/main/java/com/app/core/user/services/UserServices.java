package com.app.core.user.services;

import org.springframework.stereotype.Service;

import com.app.core.user.models.Role;
import com.app.core.user.models.User;
import com.app.core.user.payload.UserPayload;
import com.app.core.user.repositories.RoleRepository;
import com.app.core.user.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServices {
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;

	public User createUser(UserPayload user) {
		if (roleRepository.findAll().size() == 0) {
			Role role = new Role();
			role.setName("USER_ROLE");

			roleRepository.save(role);
		}

		User newUser = new User();

		newUser.setName(user.name());
		newUser.setAge(user.age());
		newUser.setRole(roleRepository.findAll().get(0));

		return userRepository.save(newUser);
	}

	public Iterable<User> getUsers() {
		return userRepository.findAll();
	}

	public User getUser(String id) {
		return userRepository.findById(id).orElseThrow(
				() -> new RuntimeException("User not found"));
	}

	public void deleteUser(String id) {
		User user = userRepository.findById(id).orElse(null);

		if (user == null) {
			throw new RuntimeException("User not found");
		}

		userRepository.deleteById(id);
	}

	public User updateUser(String id, UserPayload request) {
		User user = userRepository.findById(id).orElse(null);

		if (user == null) {
			return null;
		}

		if (request.name() != null)
			user.setName(request.name());
		if (request.age() != 0)
			user.setAge(request.age());

		return userRepository.save(user);
	}

	public Role createRole(Role role) {
		return roleRepository.save(role);
	}
}

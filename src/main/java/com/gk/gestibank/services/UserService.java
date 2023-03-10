package com.gk.gestibank.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gk.gestibank.entities.Role;
import com.gk.gestibank.entities.User;
import com.gk.gestibank.repositories.RoleRepository;
import com.gk.gestibank.repositories.UserRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service("userService")
public class UserService {
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	public UserService(UserRepository userRepository, RoleRepository roleRepository,
			BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public void saveUser(User user, String role, int active) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setActive(active);
		Role userRole = roleRepository.findByRole(role);
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(user);
	}

	public List<User> findInactiveUserByRole(String role) {
		List<User> finalUsers = new ArrayList<>();
		List<User> users = userRepository.findAll();

		for (User user : users) {
			Role existingRole = (Role) user.getRoles().toArray()[0];
			if (user.getActive() == 0 && existingRole.getRole().equals(role)) {
				finalUsers.add(user);
			}
		}

		return finalUsers;
	}

	public List<User> findUsers(String role) {
		List<User> finalUsers = new ArrayList<>();
		List<User> users = userRepository.findAll();

		for (User user : users) {
			Role existingRole = (Role) user.getRoles().toArray()[0];
			if (existingRole.getRole().equals(role)) {
				finalUsers.add(user);
			}
		}

		return finalUsers;
	}
}

package com.gk.gestibank.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gk.gestibank.entities.Role;
import com.gk.gestibank.entities.User;
import com.gk.gestibank.repositories.RoleRepository;
import com.gk.gestibank.repositories.UserRepository;

import dto.UserDto;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/user/")
public class UserController {

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;

	public UserController(UserRepository userRepository, RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}

	@GetMapping("list")
	public String listUser(Model model) {

		List<User> users = userRepository.findAll();

		model.addAttribute("users", users);
		model.addAttribute("nbr", users.size());
		return "user/listUsers";
	}

	/*
	 * @GetMapping("add") public String showAddRoleForm() { return "role/addRole"; }
	 */

	@GetMapping("add")
	public String addUser(Model model) {
		// UserDto user = new UserDto();
		User user = new User();
		List<Role> roles = roleRepository.findAll();
		model.addAttribute("roles", roles);
		model.addAttribute("userDto", user);
		return "user/addUser";
	}

	@PostMapping("add")
	public String addUser(@Valid User user, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "user/addUser";
		}
		
		/*
		User user = new User();
		user.setActive(userDto.getActive());
		user.setEmail(userDto.getEmail());
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setPassword(userDto.getPassword());
		
		Role role = roleRepository.findById(userDto.getRole().getId());

		user.getRoles().add(role);*/
		userRepository.save(user);
		return "redirect:list";
	}

	@GetMapping("edit/{id}")
	public String editUser(@PathVariable("id") int id, Model model) {
		
		User user = userRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		model.addAttribute("user", user);
		
		List<Role> roles = roleRepository.findAll();
		model.addAttribute("roles", roles);
		
		return "user/updateUser";
	}

	@PostMapping("update")
	public String updateUser(@Valid User user, BindingResult result, Model model) {
		/*
		User existing = userRepository.findById(user.getId())
				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));*/

		userRepository.save(user);
		return "redirect:list";

	}

	@GetMapping("delete/{id}")
	public String deleteUser(@PathVariable("id") int id, Model model) {

		User user = userRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

		userRepository.delete(user);
		return "redirect:../list";
	}

}

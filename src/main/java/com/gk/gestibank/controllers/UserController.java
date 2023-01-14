package com.gk.gestibank.controllers;


import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.gk.gestibank.entities.User;
import com.gk.gestibank.repositories.UserRepository;

import jakarta.validation.Valid;


@Controller
@RequestMapping("/user/")
public class UserController {
	
	private final UserRepository userRepository;
	
	
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
	
	@GetMapping("list")
    public String listUser(Model model) {
    	
    	List<User> users =  userRepository.findAll();
    	
        model.addAttribute("users", users);
        model.addAttribute("nbr", users.size());
        return "user/listUsers";
    }
    
    /*@GetMapping("add")
    public String showAddRoleForm() {
        return "role/addRole";
    }*/
    
    
    
    @GetMapping("add")
	public String addUser(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "user/addUser";
	}
    
    @PostMapping("add")
	public String addUser(@Valid User user, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "user/addUser";
		}
		
		userRepository.save(user);
		return "redirect:list";
	}


}
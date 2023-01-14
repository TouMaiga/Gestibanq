package com.gk.gestibank.controllers;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;

import com.gk.gestibank.entities.Role;

public class RoleFormatter implements Formatter<Role> {

	@Override
	public String print(Role role, Locale locale) {
		return String.valueOf(role.getId());
	}

	@Override
	public Role parse(String id, Locale locale) throws ParseException {
		Role role = new Role();
		role.setId(Integer.parseInt(id));
		return role;
	}



}

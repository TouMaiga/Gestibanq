package com.gk.gestibank;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gk.gestibank.controllers.ConfigController;

@SpringBootApplication
public class GestiBankApplication {

	public static void main(String[] args) {
		new File(ConfigController.uploadDirectory).mkdir();
		SpringApplication.run(GestiBankApplication.class, args);
	}
	

}

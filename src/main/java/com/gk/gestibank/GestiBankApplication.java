package com.gk.gestibank;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gk.gestibank.controllers.ServiceController;

@SpringBootApplication
public class GestiBankApplication {

	public static void main(String[] args) {
		new File(ServiceController.uploadDirectory).mkdir();
		SpringApplication.run(GestiBankApplication.class, args);
	}

}

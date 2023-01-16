package com.gk.gestibank.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.gk.gestibank.entities.Service;
import com.gk.gestibank.repositories.ServiceRepository;

import javax.validation.Valid;

@Controller
@RequestMapping("/service/")
public class ServiceController {

	public static String uploadDirectory = System.getProperty("user.dir") + "/src/main/resources/static/uploads";

	private ServiceRepository serviceRepository;

	public ServiceController(ServiceRepository serviceRepository) {
		this.serviceRepository = serviceRepository;
	}

	@GetMapping("list")
	public String listService(Model model) {

		List<Service> services = (List<Service>) serviceRepository.findAll();

		model.addAttribute("services", services);

		return "service/listService";
	}

	@GetMapping("add")
	public String addService(Model model) {
		Service service = new Service();
		model.addAttribute("service", service);
		return "service/addService";
	}

	@PostMapping("add")
	public String addAgent(@Valid Service service, BindingResult result, Model model,
			@RequestParam("files") MultipartFile[] files) {
		if (result.hasErrors()) {
			System.out.println(result);
			return "service/addService";
		}

		addLogo(service, files);

		serviceRepository.save(service);
		return "redirect:list";
	}

	@GetMapping("edit/{id}")
	public String showAgentFormToUpdate(@PathVariable("id") long id, Model model) {
		Service service = serviceRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid service Id:" + id));

		model.addAttribute("service", service);

		return "service/updateService";
	}

	@PostMapping("update")
	public String updateService(@Valid Service service, BindingResult result, Model model,
			@RequestParam("files") MultipartFile[] files) {

		addLogo(service, files);

		serviceRepository.save(service);
		return "redirect:list";
	}

	@GetMapping("show/{id}")
	public String showDetailService(@PathVariable("id") long id, Model model) {
		Service service = serviceRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid service Id:" + id));
		model.addAttribute("service", service);
		return "service/showService";
	}

	@GetMapping("delete/{id}")
	public String deleteService(@PathVariable("id") long id, Model model) {

		Service service = serviceRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid service Id:" + id));

		serviceRepository.delete(service);

		return "redirect:../list";
	}
	
	private void addLogo(Service service, MultipartFile[] files) {
		StringBuilder fileName = new StringBuilder();
		MultipartFile file = files[0];
		Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
		fileName.append(file.getOriginalFilename());
		try {
			Files.write(fileNameAndPath, file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		service.setLogo(fileName.toString());
	}
}
package com.gk.gestibank.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gk.gestibank.entities.Message;
import com.gk.gestibank.repositories.MessageRepository;

import jakarta.validation.Valid;

        @Controller
        @RequestMapping("/message/")
public class MessageController {
	private final MessageRepository messageRepository;

	@Autowired
	public MessageController(MessageRepository messageRepository) {
		this.messageRepository = messageRepository;
	}

	     @GetMapping("list")
	    //@ResponseBody
	    public String listMessages(Model model) {
	    	
	    	List<Message> lp = (List<Message>)messageRepository.findAll();
	    	if(lp.size() == 0) {
	    		lp = null;
	    	}
	        model.addAttribute("messages",lp);	        
	        return "message/listMessage";
	        
	        //List<Provider> lp = (List<Provider>)providerRepository.findAll();
	        //System.out.println(lp);
	        
	        //return "Nombre de fournisseur = " + lp.size();
	    }
	  
	  
	  
		  @GetMapping("add") 
		  public String showAddMessageForm(Model model) {
		  Message message = new Message(); 
		  // object dont la valeur des attributs par defaut
		  model.addAttribute("message", message); 
		  return "message/addMessage"; }
		 
	 

	@PostMapping("add")
	public String addMessage(@Valid Message message, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "message/addMessage";
		}
		messageRepository.save(message);
		return "redirect:list";
	} 

	
	
	  @GetMapping("delete/{id}") 
	  public String deleteMessage(@PathVariable("id") long id, Model model) {
	  //long id2 = 100L;
	  
	  Message message = messageRepository.findById(id) .orElseThrow(()-> new
	  IllegalArgumentException("Invalid message Id:" + id));
	  
	  System.out.println("suite du programme...");
	  
	  messageRepository.delete(message);
	  /* model.addAttribute("providers", messageRepository.findAll()); 
	  return"message/listMessage"; */
	  return "redirect:../list";
	  }
	  
	  
	  
	  @GetMapping("edit/{id}")
	  public String showMessageFormToUpdate(@PathVariable("id") long id, Model model) {
	  Message message = messageRepository.findById(id) .orElseThrow(()->new IllegalArgumentException("Invalid message Id:" + id));
	  
	  model.addAttribute("message", message);
	  
	  return "message/updateMessage"; 
	  }
	  
	  
	  
	  @PostMapping("update") 
	  public String updateMessage(@Valid Message message, BindingResult result, Model model) {
	  
	  messageRepository.save(message); 
	  return"redirect:list";
	  
	  }
	 

}

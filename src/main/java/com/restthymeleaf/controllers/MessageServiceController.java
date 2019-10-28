package com.restthymeleaf.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.restthymeleaf.exception.ResourceAlredyReportedException;
import com.restthymeleaf.exception.ResourceNotFoundException;
import com.restthymeleaf.model.Message;
import com.restthymeleaf.service.MessageService;

/* 
 * 
 * Create a controller to communicate with front end 
 * */

@Controller
@RequestMapping("/messages/")
public class MessageServiceController {

	private final MessageService messageService;

	@Autowired
	public MessageServiceController(MessageService messageService) {
		this.messageService = messageService;
	}

	// Landing page call for starting the application
	@GetMapping("signup")
	public String showSignUpForm(Message message) {
		return "add-message";
	}

	// Provide list of Messages which are stored by Client
	@GetMapping("list")
	public String showUpdateForm(Model model) {
		model.addAttribute("messages", messageService.getAllMessage());
		return "index";
	}

	// Add the Message Detail
	@PostMapping("add")
	public String addMessage(@Valid Message message, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-message";
		}
		try {
			messageService.addMessage(message);
		} catch (ResourceAlredyReportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:list";
	}

	// Edit the Message by passing message id
	@GetMapping("edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Message message = messageService.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Message Id:" + id));
		model.addAttribute("message", message);
		return "update-message";
	}

	// Update the message by passing message id
	@PostMapping("update/{id}")
	public String updateMessage(@PathVariable("id") long id, @Valid Message message, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			message.setId(id);
			return "update-message";
		}

		try {
			messageService.updateMessage(message, messageService.findById(id).get().getMessageDetail());
		} catch (ResourceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("messages", messageService.getAllMessage());
		return "index";
	}

	// Delete the Message by passing message id
	@GetMapping("delete/{id}")
	public String deleteMessage(@PathVariable("id") long id, Model model) {
		Message message = messageService.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid message Id:" + id));
		try {
			messageService.deleteMessage(message.getMessageDetail());
		} catch (ResourceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("messages", messageService.getAllMessage());
		return "index";
	}
}

package com.restthymeleaf.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.restthymeleaf.exception.BadRequestException;
import com.restthymeleaf.exception.ResourceNotFoundException;
import com.restthymeleaf.model.Message;
import com.restthymeleaf.service.MessageService;

/*
 * 
 * Create a Rest Controller for Message API
  */
@RestController
public class MessageController {
	public MessageController() {
		// TODO Auto-generated constructor stub
	}

	@Autowired
	MessageService messageService;

	// Create Message method by passing Message model and in return get Message
	// model if Message created successfully.
	@PostMapping("/messages")
	public Message addMessage(@Valid @RequestBody Message messageDetail) throws ResourceNotFoundException {
		return messageService.addMessage(messageDetail);

	}

	// Update Message Method by passing Message model and message which you want to
	// change and in return get Message model if Message updated successfully.
	@PutMapping("/messages/{id}")
	public ResponseEntity<Message> updateMessage(@PathVariable(value = "id") long messageId,
			@Valid @RequestBody Message messageDetails) throws ResourceNotFoundException, BadRequestException {
		final Message updatedmessage = messageService.updateMessage(messageDetails, messageId);
		return ResponseEntity.ok(updatedmessage);
	}

	// Delete Message Method by passing message which you want to delete and in
	// return get true if Message deleted successfully.
	@DeleteMapping("/messages/{id}")
	public Map<String, Boolean> deleteMessage(@PathVariable(value = "id") long messageId)
			throws ResourceNotFoundException {
		messageService.deleteMessage(messageId);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

	// get all Message Method to get All messages which are stored during the API
	// call.
	@GetMapping("/messages")
	public List<Message> getAllMessage() {
		return messageService.getAllMessage();
	}

}

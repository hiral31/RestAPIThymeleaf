package com.restthymeleaf.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restthymeleaf.exception.BadRequestException;
import com.restthymeleaf.exception.ResourceNotFoundException;
import com.restthymeleaf.model.Message;
import com.restthymeleaf.repository.MessageRepository;

@Service
public class MessageService {

	@Autowired
	private MessageRepository messageRepository;

	// get All messages which are stored by client
	public List<Message> getAllMessage() {
		List<Message> messageList = new ArrayList<>();
		messageRepository.findAll().forEach(messageList::add);
		return messageList;
	}

	// Add message which are passed by client
	public Message addMessage(Message messageDetail) throws  BadRequestException {
		Message message = messageRepository.findByMessage(messageDetail.getMessageDetail());
		if (message == null) {
			return messageRepository.save(messageDetail);
		}
		throw new BadRequestException("Message alredy Added " + messageDetail.getMessageDetail());
	}

	// Update Message using messageDetail
	public Message updateMessage(Message messageDetails, long messageId) throws ResourceNotFoundException, BadRequestException {
		Optional<Message> message = messageRepository.findById(messageId);
		if (!message.isPresent()) {
			throw new ResourceNotFoundException("Message not found :" + messageId);
		}

		if (messageRepository.findByMessage(messageDetails.getMessageDetail()) != null) {
			throw new BadRequestException("Message alredy added :" + messageDetails.getMessageDetail());
		}

		message.get().setMessageDetail(messageDetails.getMessageDetail());
		Message updatedMessage = messageRepository.save(message.get());

		return updatedMessage;

	}

	// Delete Message by MessageDetail
	public void deleteMessage(long messageId) throws ResourceNotFoundException {
		Optional<Message> message = messageRepository.findById(messageId);
		if (!message.isPresent()) {
			throw new ResourceNotFoundException("Message not found :" + messageId);
		}
		messageRepository.delete(message.get());
	}

	// Find the Message based on messageDetail
	public Message findByMessage(String message) {
		return messageRepository.findByMessage(message);
	}

	// Find the Message based on messageId
	public Optional<Message> findById(long id) {
		return messageRepository.findById(id);
	}
}

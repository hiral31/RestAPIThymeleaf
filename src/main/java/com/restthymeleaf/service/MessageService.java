package com.restthymeleaf.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restthymeleaf.exception.ResourceAlredyReportedException;
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
	public Message addMessage(Message messageDetail) throws ResourceAlredyReportedException {
		Message message = messageRepository.findByMessage(messageDetail.getMessageDetail());
		if (message == null) {
			return messageRepository.save(messageDetail);
		}
		throw new ResourceAlredyReportedException("Message alredy Added " + messageDetail.getMessageDetail());
	}

	// Update Message using messageDetail
	public Message updateMessage(Message messageDetails, String messageDetail) throws ResourceNotFoundException {
		Message message = messageRepository.findByMessage(messageDetail);
		if (message == null) {
			throw new ResourceNotFoundException("Message not found :" + messageDetail);
		}

		if (messageRepository.findByMessage(messageDetails.getMessageDetail()) != null) {
			throw new ResourceNotFoundException("Message alredy added :" + messageDetail);
		}

		message.setMessageDetail(messageDetails.getMessageDetail());

		Message updatedMessage = messageRepository.save(message);

		return updatedMessage;

	}

	// Delete Message by MessageDetail
	public void deleteMessage(String messageDetail) throws ResourceNotFoundException {
		Message message = messageRepository.findByMessage(messageDetail);
		if (message == null) {
			throw new ResourceNotFoundException("Message not found :" + messageDetail);
		}
		messageRepository.delete(message);
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

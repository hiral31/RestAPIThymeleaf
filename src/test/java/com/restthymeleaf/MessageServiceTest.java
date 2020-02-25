package com.restthymeleaf;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.restthymeleaf.model.Message;
import com.restthymeleaf.repository.MessageRepository;

/*
 * Create a class to test Rest API
 * 
 * */

public class MessageServiceTest extends MessageAbstractTest {

	Message message;

	@Autowired
	MessageRepository messageRepository;

	@Before
	public void setUp() {
		super.setUp();
		if (message == null) {
			message = new Message();
			message.setMessageDetail("helloooo");
			messageRepository.save(message);
		}
	}

	@Test
	// Test the add Message
	public void createMessage() throws Exception {

		String uri = "/messages";
		message = new Message();
		message.setMessageDetail("hellooooooo");
		String inputJson = super.mapToJson(message);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);

	}

	// Test update Method
	@Test
	public void updateMessage() throws Exception {
		String uri = "/messages/1";
		message = new Message();
		message.setMessageDetail("helloooooo");
		String inputJson = super.mapToJson(message);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String actual = mvcResult.getResponse().getContentAsString();
		String content = "{\"id\":1,\"messageDetail\":\"helloooooo\"}";
		assertEquals(content, actual);
	}

	// Test Delete Message
	@Test
	public void deleteMessage() throws Exception {
		String uri = "/messages/1";
		String content = "{\"deleted\":true}";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String actual = mvcResult.getResponse().getContentAsString();
		assertEquals(content, actual);
	}

	// Test Add Message
	@Test
	public void createMessage1() throws Exception {
		String uri = "/messages";
		message = new Message();
		message.setMessageDetail("hi");
		String inputJson = super.mapToJson(message);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);

	}

	// Test Get All Message
	@Test
	public void getMessageList() throws Exception {
		String uri = "/messages";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Message[] messagelist = super.mapFromJson(content, Message[].class);
		if (messagelist != null) {
			assertTrue(messagelist.length > 0);
		}

	}
}

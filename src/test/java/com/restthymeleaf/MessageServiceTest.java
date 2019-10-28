package com.restthymeleaf;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.restthymeleaf.model.Message;

/*
 * Create a class to test Rest API
 * 
 * */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MessageServiceTest extends MessageAbstractTest {
	@Override
	@Before
	public void setUp() {
		super.setUp();
	}

	@Test
	// Test the add Message
	public void Message1() throws Exception {
		/* public void createMessage() throws Exception { */

		String uri = "/messages";
		Message message = new Message();
		message.setMessageDetail("hellooo");
		String inputJson = super.mapToJson(message);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		System.out.println("Create content --->" + content);
		/* assertEquals(content, "{\"id\":1,\"message\":\"hellooo\"}]"); */
	}

	// Test update Method
	@Test
	public void Message2() throws Exception {
		/* public void updateMessage() throws Exception { */
		String uri = "/messages/hellooo";
		Message message = new Message();
		message.setMessageDetail("helloooooo");
		String inputJson = super.mapToJson(message);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		System.out.println("update content--->" + content);
		/* assertEquals(content, "{\"id\":1,\"message\":\"hellooo\"}]"); */
	}

	// Test Delete Message
	@Test
	public void Message3() throws Exception {
		/* public void deleteMessage() throws Exception { */
		String uri = "/messages/helloooooo";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		System.out.println("Delete content--->" + content);
		/* assertEquals(content, "{"deleted\":\"true\"}"); */
	}

	// Test Add Message
	@Test
	public void Message4() throws Exception {
		/* public void createMessage1() throws Exception { */

		String uri = "/messages";
		Message message = new Message();
		message.setMessageDetail("hi");
		String inputJson = super.mapToJson(message);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		System.out.println("Create content --->" + content);
		/* assertEquals(content, "{\"id\":1,\"message\":\"hellooo\"}]"); */
	}

	// Test Get All Message
	@Test
	public void Message5() throws Exception {
		/* public void getMessageList() throws Exception { */
		String uri = "/messages";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		System.out.println("get message content ---->" + content);
		Message[] messagelist = super.mapFromJson(content, Message[].class);
		if (messagelist != null) {
			assertTrue(messagelist.length > 0);
		}

	}
}

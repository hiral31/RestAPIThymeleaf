package com.restthymeleaf;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import com.restthymeleaf.RestServiceApplication;
import com.restthymeleaf.model.Message;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RestServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MessageControllerTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port;
	}

	@Test
	public void contextLoads() {

	}

	@Test
	public void testCreateEmployee() {
		Message message = new Message();
		message.setMessageDetail("How are you");
		ResponseEntity<Message> postResponse = restTemplate.postForEntity(getRootUrl() + "/messages", message,
				Message.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
	}

	@Test
	public void testUpdateEmployee() {
		String id = "How are you";
		Message message = restTemplate.getForObject(getRootUrl() + "/messages/" + id, Message.class);
		message.setMessageDetail("I am good");
		restTemplate.put(getRootUrl() + "/messages/" + id, message);
		Message updatedMessage = restTemplate.getForObject(getRootUrl() + "/messages/" + id, Message.class);
		assertNotNull(updatedMessage);
	}

	@Test
	public void testDeleteEmployee() {
		String id = "hi";
		Message message = restTemplate.getForObject(getRootUrl() + "/messages/" + id, Message.class);
		assertNotNull(message);
		restTemplate.delete(getRootUrl() + "/messages/" + id);
		try {
			message = restTemplate.getForObject(getRootUrl() + "/messages/" + id, Message.class);
		} catch (final HttpClientErrorException e) {
			assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
		}
	}

	@Test
	public void getMessageList() {

		HttpHeaders haders = new HttpHeaders();

		HttpEntity<String> entity = new HttpEntity<String>(null, haders);

		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/messages", HttpMethod.GET, entity,
				String.class);
		System.out.println(response.getBody());
		assertNotNull(response.getBody());

	}
}

package com.restthymeleaf.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "messageDetail")
	private String messageDetail;

	public Message() {

	}

	public Message(String messageDetail) {
		super();
		this.messageDetail = messageDetail;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the messageDetail
	 */
	public String getMessageDetail() {
		return messageDetail;
	}

	/**
	 * @param messageDetail
	 *            the messageDetail to set
	 */
	public void setMessageDetail(String messageDetail) {
		this.messageDetail = messageDetail;
	}

}

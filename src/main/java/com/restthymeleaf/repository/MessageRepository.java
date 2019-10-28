package com.restthymeleaf.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.restthymeleaf.model.Message;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {

	@Query("SELECT t FROM Message t WHERE t.messageDetail = ?1")
	public Message findByMessage(String messageDetail);
}

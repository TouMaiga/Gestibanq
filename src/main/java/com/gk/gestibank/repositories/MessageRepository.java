package com.gk.gestibank.repositories;

import org.springframework.data.repository.CrudRepository;

import com.gk.gestibank.entities.Message;

public interface MessageRepository extends CrudRepository<Message, Long> {

}

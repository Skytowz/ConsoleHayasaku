package com.hayasaku.console.model.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hayasaku.console.model.dto.messages.Message;

/**
 * Repository CRUD pour la gestion en base de messages
 * @author Lucas "Skytowz" HOTTIN
 *
 */
@Repository
public interface MessageRepository extends CrudRepository<Message, Long>{
}

package com.hayasaku.console.model.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hayasaku.console.model.dto.messages.MessageRoom;
import com.hayasaku.console.model.dto.messages.payload.MessageRoomPayload;

/**
 * Repository CRUD pour la gestion en base des Messages envoyés dans des rooms
 * @author Quentin "Ruendan" DUBOIS
 *
 */
@Repository
public interface MessageRoomRepository extends CrudRepository<MessageRoom, Long> {
	public List<MessageRoomPayload> findFirst30ByRoomDestinationAndRoomDestinationNotNull(Long id);
}

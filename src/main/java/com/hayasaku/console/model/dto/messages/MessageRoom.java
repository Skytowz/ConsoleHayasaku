package com.hayasaku.console.model.dto.messages;

import com.hayasaku.console.model.dto.Room;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Entity MessagesRoom
 * @author Lucas "Skytowz" HOTTIN
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class MessageRoom extends Message {
	
	@ManyToOne
	private Room roomDestination;

	@Override
	public String getDestinationPath() {
		return roomDestination.getName();
	}
}

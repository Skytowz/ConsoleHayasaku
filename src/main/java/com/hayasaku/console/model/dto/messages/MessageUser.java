package com.hayasaku.console.model.dto.messages;

import com.hayasaku.console.model.dto.PurpleUser;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Entity MessagesUser
 * @author Quentin "Ruendan" DUBOIS
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class MessageUser extends Message {
	@ManyToOne
	private PurpleUser userDestination;
	
	@Override
	public String getDestinationPath() {
		return userDestination.getId() + "";
	}
}

package com.hayasaku.console.model.dto.messages;

import com.hayasaku.console.model.dto.HayasakuUser;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Entity MessagesUser
 * @author Lucas "Skytowz" HOTTIN
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class MessageUser extends Message {
	@ManyToOne
	private HayasakuUser userDestination;
	
	@Override
	public String getDestinationPath() {
		return userDestination.getId() + "";
	}
}

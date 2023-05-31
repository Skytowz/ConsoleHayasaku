package com.hayasaku.console.model.dto.messages.payload;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DTO pour l'envoi et la reception de messages privés aux utilisateurs
 * @author Lucas "Skytowz" HOTTIN
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MessageUserPayload extends MessagePayload {
	private Long userDestination;
}

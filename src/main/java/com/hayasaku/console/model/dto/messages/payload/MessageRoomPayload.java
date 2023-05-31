package com.hayasaku.console.model.dto.messages.payload;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DTO pour l'envoi et la reception de messages dans les rooms
 * @author Quentin "Ruendan" DUBOIS
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MessageRoomPayload extends MessagePayload {
	private Long roomDestination;
}

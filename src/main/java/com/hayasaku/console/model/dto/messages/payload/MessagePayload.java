package com.hayasaku.console.model.dto.messages.payload;

import java.time.LocalDate;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import lombok.Data;

/**
 * DTO pour l'envoi et la reception de messages
 * @author Quentin "Ruendan" DUBOIS
 *
 */
@Data
public abstract class MessagePayload {
	private Long sender;
	private String content;
	
	@JsonDeserialize(using = LocalDateDeserializer.class)  
	@JsonSerialize(using = LocalDateSerializer.class) 
	private LocalDate date;
}

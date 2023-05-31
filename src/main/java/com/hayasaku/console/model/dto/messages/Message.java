package com.hayasaku.console.model.dto.messages;

import java.time.LocalDate;

import com.hayasaku.console.model.dto.PurpleUser;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

/**
 * Entity Messages
 * @author Quentin "Ruendan" DUBOIS
 *
 */
@Entity
@Data
public abstract class Message {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private PurpleUser sender;
	private String content;
	private LocalDate date;
	private MessageStatus status;

	public enum MessageStatus {
		DELIVERED, 
		RECEIVED, 
		READ
	}

	public abstract String getDestinationPath();
}
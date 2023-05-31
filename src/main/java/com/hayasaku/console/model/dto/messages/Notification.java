package com.hayasaku.console.model.dto.messages;

import com.hayasaku.console.model.dto.PurpleUser;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

/**
 * Entity Notification
 * @author Quentin "Ruendan" DUBOIS
 *
 */
@Entity
@Data
public class Notification {
	@Id
    private Long id;
	
	@ManyToOne
    private PurpleUser sender;
}
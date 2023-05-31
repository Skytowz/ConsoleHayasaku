package com.hayasaku.console.model.dto.messages;

import com.hayasaku.console.model.dto.HayasakuUser;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

/**
 * Entity Notification
 * @author Lucas "Skytowz" HOTTIN
 *
 */
@Entity
@Data
public class Notification {
	@Id
    private Long id;
	
	@ManyToOne
    private HayasakuUser sender;
}
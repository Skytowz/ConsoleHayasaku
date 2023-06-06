package com.hayasaku.console.dto;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Data;

@Data
@Entity
@Inheritance( strategy = InheritanceType.JOINED )
public abstract class Command {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long commandId;
	protected String guildId;
	protected String name;
	protected List<String> caller;
	protected String help;
	protected String cmd;
	

}

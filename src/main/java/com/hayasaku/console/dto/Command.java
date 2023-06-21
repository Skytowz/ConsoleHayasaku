package com.hayasaku.console.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Data;

@Data
@Entity
@JsonDeserialize
@Inheritance( strategy = InheritanceType.JOINED )
public class Command {
	
	
	@JsonProperty("id")
	private String id;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	protected Long commandId;
	@JsonProperty("guild_id")
	protected String guildId;
	@JsonIgnore
	protected String name;
	@JsonProperty("description")
	protected String description;
	@JsonIgnore
	protected String cmd;
	@JsonProperty("name")
	protected String trigger;
	@JsonProperty("type")
	protected Long type;

}

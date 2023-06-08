package com.hayasaku.console.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode
public class CommandTrigger {
	
	@Id
	@EqualsAndHashCode.Exclude 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NonNull
	private String trigger;
	
	@NonNull 
	@ManyToOne
	@EqualsAndHashCode.Exclude
	@JoinColumn(name = "command_id")
	private Command commandId;
}

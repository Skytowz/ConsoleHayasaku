package com.hayasaku.console.dto;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
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
	protected String help;
	protected String cmd;
	
	@OneToMany(mappedBy = "commandId",fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	protected List<CommandTrigger> triggers;
	
	public List<String> getCaller() {
		if(triggers == null) return new ArrayList<>();
		return triggers.stream().map(trigger -> trigger.getTrigger()).toList();
	}
	
	public void setCaller(List<String> caller) {
		if(caller == null) this.triggers = new ArrayList<>();
		else this.triggers = caller.stream().map(call -> new CommandTrigger(call)).toList();
	}

}

package com.hayasaku.console.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.hayasaku.console.dto.Command;

public interface CommandRepository extends CrudRepository<Command, Long>{	
	@Query(value =  "SELECT c FROM Command c JOIN CommandTrigger ct ON c.commandId = ct.commandId WHERE c.guildId = ?1 AND ct.trigger IN ?2")
	public List<Command> findByGuildIdAndTriggersIn(String guildId,List<String> commandTrigger);
}


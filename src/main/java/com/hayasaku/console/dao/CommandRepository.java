package com.hayasaku.console.dao;

import org.springframework.data.repository.CrudRepository;

import com.hayasaku.console.dto.Command;

public interface CommandRepository extends CrudRepository<Command, Long>{	
	
	public Command findByGuildIdAndTrigger(String guildId,String trigger);
}


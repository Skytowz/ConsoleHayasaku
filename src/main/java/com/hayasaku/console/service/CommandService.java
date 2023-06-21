package com.hayasaku.console.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hayasaku.console.dao.CommandRepository;
import com.hayasaku.console.dao.MangaRepository;
import com.hayasaku.console.dto.Command;
import com.hayasaku.console.dto.Manga;

@Service
public class CommandService {

	@Autowired
	private MangaRepository mangaRepository;
	@Autowired
	private CommandRepository commandRepository;
	
	public Iterable<Manga> findAllManga(){
			return mangaRepository.findAll();
	}

	public List<Manga> findAllMangaByGuildId(String guildId) {
		return mangaRepository.findAllByGuildId(guildId);
	}
	
	public Manga findMangaById(String idCommand) {
		return mangaRepository.findById(idCommand);
	}
	
	public Manga saveManga(Manga manga) {
		return mangaRepository.save(manga);
	}

	public boolean triggerExistForGuild(String guildId, String newTrigger) {
		return null != commandRepository.findByGuildIdAndTrigger(guildId, newTrigger); 
	}
	
	public Iterable<Command> findAllCommand() {
		return commandRepository.findAll();
	}
	
}

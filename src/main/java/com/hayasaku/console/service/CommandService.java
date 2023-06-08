package com.hayasaku.console.service;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hayasaku.console.dao.CommandRepository;
import com.hayasaku.console.dao.MangaRepository;
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
	
	public Manga findMangaById(Long idCommand) {
		return mangaRepository.findById(idCommand).orElse(null);
	}
	
	public Manga saveManga(Manga manga) {
		return mangaRepository.save(manga);
	}

	public boolean triggerExistForGuild(String guildId, List<String> newTrigger) {
		return !CollectionUtils.isEmpty(commandRepository.findByGuildIdAndTriggersIn(guildId, newTrigger)); 
	}
	
}

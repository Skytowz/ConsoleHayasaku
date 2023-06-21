package com.hayasaku.console.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.hayasaku.console.dto.Manga;

public interface MangaRepository extends CrudRepository<Manga, Long>{
	@Query(value =  "SELECT m FROM Manga m JOIN Command c ON m.id = c.id WHERE c.guildId = ?1")
	public List<Manga> findAllByGuildId(String guildId);
	public Manga findById(String commandId);
}


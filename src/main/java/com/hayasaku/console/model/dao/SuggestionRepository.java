package com.hayasaku.console.model.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hayasaku.console.model.dto.support.Suggestion;
import com.hayasaku.console.model.dto.support.SuggestionCategory;

/**
 * Repository CRUD pour la gestion en base des tickets
 * @author Lucas "Skytowz" HOTTIN
 *
 */
@Repository
public interface SuggestionRepository extends CrudRepository<Suggestion, Long> {
	public Page<Suggestion> findAll(Pageable pageable);
	public Page<Suggestion> findByCategory(SuggestionCategory category, Pageable pageable);
}

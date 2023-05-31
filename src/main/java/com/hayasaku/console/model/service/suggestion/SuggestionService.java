package com.hayasaku.console.model.service.suggestion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.hayasaku.console.model.dao.SuggestionRepository;
import com.hayasaku.console.model.dto.support.Suggestion;
import com.hayasaku.console.model.dto.support.SuggestionCategory;

@Service
public class SuggestionService {

	@Autowired
	private SuggestionRepository suggestionRepo;

	public Page<Suggestion> findAllSuggestions(int pageNumber, int pageSize) {
		return suggestionRepo.findAll(PageRequest.of(pageNumber, pageSize, Sort.by("creationDate").ascending()));
	}

	public Page<Suggestion> findAllSuggestionsByCategory(SuggestionCategory category, int pageNumber, int pageSize) {
		return suggestionRepo.findByCategory(category, PageRequest.of(pageNumber, pageSize, Sort.by("creationDate").ascending()));
	}

	public void saveSuggestion(Suggestion newSuggestion) {
		suggestionRepo.save(newSuggestion);
	}

	public Page<Suggestion> findAllSuggestions(SuggestionCategory category, int pageNumber, Integer pageSize) {
		if(category == null) return findAllSuggestions(pageNumber,  pageSize);
		else return findAllSuggestionsByCategory(category, pageNumber, pageSize);
	}
}

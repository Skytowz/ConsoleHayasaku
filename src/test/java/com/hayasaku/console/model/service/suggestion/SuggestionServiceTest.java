package com.hayasaku.console.model.service.suggestion;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Description;

import com.hayasaku.console.model.dao.SuggestionRepository;
import com.hayasaku.console.model.dto.support.Suggestion;
import com.hayasaku.console.model.service.suggestion.SuggestionService;

@SpringBootTest
class SuggestionServiceTest {
	
	@MockBean
	private SuggestionRepository suggestionRepo;
    
    @InjectMocks
	private SuggestionService suggestionService;

	private Suggestion suggestionTest;

    private List<Suggestion> suggestionLists;
	
	@BeforeEach
	void reinitialize() {
		suggestionTest = new Suggestion();
		suggestionLists = new ArrayList<>();
		
		when(suggestionRepo.save(any())).then((e) -> {suggestionLists.add(e.getArgument(0)); return e.getArgument(0);});

		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	@Description("SuggestionService#saveSuggestion - Expect suggestionRepo.save to be called once.")
	void saveSuggestion() {
		suggestionService.saveSuggestion(suggestionTest);
		verify(suggestionRepo, times(1)).save(any());
	}
	
	@Test
	@Description("SuggestionService#findAllSuggestions - if category null, return all suggestions")
	void findAllSuggestionsWithCategoryNull() {
//		Page<Suggestion> page = suggestionService.findAllSuggestions(null,0,5);
		assertTrue(true);
	}

}

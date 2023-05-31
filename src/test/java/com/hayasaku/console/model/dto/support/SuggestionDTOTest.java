package com.hayasaku.console.model.dto.support;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import com.hayasaku.console.model.dto.support.Suggestion;
import com.hayasaku.console.model.dto.support.SuggestionCategory;
import com.hayasaku.console.model.dto.support.SuggestionDTO;

class SuggestionDTOTest {

	@Test
	void toSuggestion() {
		SuggestionDTO dto = new SuggestionDTO();
		dto.setTitle("Title");
		dto.setContent("This is a content");
		dto.setCategory(SuggestionCategory.ACCOUNT);
		dto.setPrivateTicket(false);
		
		Suggestion suggestion = dto.toSuggestion();
		
		assertEquals("Title", suggestion.getTitle());
		assertEquals("This is a content", suggestion.getContent());
		assertEquals(SuggestionCategory.ACCOUNT, suggestion.getCategory());
		assertFalse(suggestion.isPrivateTicket());
		assertFalse(suggestion.isClosed());
	}

}

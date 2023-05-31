package com.hayasaku.console.model.dto.support;

import lombok.Data;

/**
 * SuggestionDTO pour la reception de Ticket
 * @author Lucas "Skytowz" HOTTIN
 *
 */
@Data
public class SuggestionDTO {
	
	private String title;
	private String content;
	private SuggestionCategory category;
	private boolean privateTicket;
	
	public Suggestion toSuggestion() {
		Suggestion res = new Suggestion();
		res.setTitle(title);
		res.setContent(content);
		res.setCategory(category);
		res.setPrivateTicket(privateTicket);
		return res;
	}
}

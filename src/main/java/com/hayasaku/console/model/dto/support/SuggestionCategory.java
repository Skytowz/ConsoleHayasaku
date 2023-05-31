package com.hayasaku.console.model.dto.support;

/**
 * Enum SuggestionCategory
 * @author Quentin "Ruendan" DUBOIS
 *
 */
public enum SuggestionCategory {
	
	BUG("fas fa-bug"), 
	ACCOUNT("fas fa-user"), 
	FEATURE("fas fa-bolt"), 
	HELP("fas fa-question"), 
	OTHER("fas fa-feather");
	
	private String fasClass;
	
	private SuggestionCategory(String fasClass) {
		this.fasClass = fasClass;
	}
	
	public String getFasClass() {
		return this.fasClass;
	}
}

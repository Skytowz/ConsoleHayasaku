package com.hayasaku.console.controller.servlet.more;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hayasaku.console.model.dto.support.SuggestionCategory;
import com.hayasaku.console.model.dto.support.SuggestionDTO;
import com.hayasaku.console.model.service.suggestion.SuggestionService;

/**
 * Controleurs pour la gestion de tickets
 * @author Lucas "Skytowz" HOTTIN
 *
 */
@Controller
public class BugReportController {
	
	@Autowired
	private SuggestionService suggestionService;
	
	@GetMapping(value = {"report","/report"})
	public String getReport(Model model, @RequestParam(required = false) SuggestionCategory category, @RequestParam(required = false, defaultValue = "1") Integer pageNumber, @RequestParam(required = false, defaultValue = "15") Integer pageSize) {
		model.addAttribute("bugs", suggestionService.findAllSuggestions(category, pageNumber <= 1 ? 0 : pageNumber - 1, pageSize));
		model.addAttribute("categories", SuggestionCategory.values());
		return "public/report";	
	}
	
	@PostMapping(value = "newSuggestion")
	public String newSuggestion(@ModelAttribute SuggestionDTO newSuggestion) {
		suggestionService.saveSuggestion(newSuggestion.toSuggestion());
		return "redirect:/report";
	}
}

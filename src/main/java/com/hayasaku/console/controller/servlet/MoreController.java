package com.hayasaku.console.controller.servlet;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controleurs pour la redirections vers les liens de la navbar
 * @author Lucas "Skytowz" HOTTIN
 *
 */
@Controller
public class MoreController {

	@GetMapping(value = {"navbar","/navbar"})
	public String getNavbar(Model model, @RequestParam(required = false) String error) {
		return "navbar";
	}

}

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
	@GetMapping(value = {"about","/about"})
	public String getAbout(Model model, @RequestParam(required = false) String error) {
		return "public/about";
	}
	
	@GetMapping(value = {"contact","/contact"})
	public String getContact(Model model, @RequestParam(required = false) String error) {
		return "public/contact";
	}

	@GetMapping(value = {"navbar","/navbar"})
	public String getNavbar(Model model, @RequestParam(required = false) String error) {
		return "public/navbar";
	}

	@GetMapping(value = {"calendar","/calendar"})
	public String getCalendar(Model model, @RequestParam(required = false) String error) {
		return "calendar";
	}

}

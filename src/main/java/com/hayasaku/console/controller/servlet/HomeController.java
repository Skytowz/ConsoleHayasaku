package com.hayasaku.console.controller.servlet;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controleurs pour le retour à l'accueil
 * @author Lucas "Skytowz" HOTTIN
 *
 */
@Controller
public class HomeController {
	@GetMapping(value = {"/home","/index", "/"})
	public String getHome(Model model) {
		return "public/home";
	}
}

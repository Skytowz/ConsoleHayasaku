package com.hayasaku.console.controller.servlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.hayasaku.console.entity.DiscordUser;

/**
 * Controleurs pour le retour à l'accueil
 * @author Lucas "Skytowz" HOTTIN
 *
 */
@Controller
public class HomeController {
	
	@Autowired
	@Qualifier("sakuBot")
	private DiscordUser sakuBot;
	
	@GetMapping(value = {"/home","/index", "/"})
	public String getHome(Model model,@SessionAttribute DiscordUser user) {
		return "home";
	}
}

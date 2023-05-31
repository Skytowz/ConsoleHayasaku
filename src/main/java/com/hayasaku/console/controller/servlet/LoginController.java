package com.hayasaku.console.controller.servlet;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controleurs pour la redirection au formulaire de login
 * @author Lucas "Skytowz" HOTTIN
 *
 */
@Controller
@RequestMapping("/login")
public class LoginController {
	
	@GetMapping(value = {"","/"})
	public String getLogin(Model model, @RequestParam(required = false) String error) {
		return "public/login";
	}

}

package com.hayasaku.console.controller.servlet;

import java.util.Arrays;
import java.util.regex.Pattern;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hayasaku.console.model.dao.PurpleUserRepository;
import com.hayasaku.console.model.dto.PurpleRole;
import com.hayasaku.console.model.dto.PurpleUser;

/**
 * Controleurs pour l'inscription au site
 * @author Quentin "Ruendan" DUBOIS
 *
 */
@Controller
@RequestMapping("/register")
public class RegisterController {
	
	@Autowired
	private PurpleUserRepository purpleUserRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping(value = {"", "/"})
	public String getRegister(Model model,
			@ModelAttribute(name = "error") String error) {
		model.addAttribute("error", error);
		return "public/register";
	}
	
	@PostMapping(value = {"", "/"})
	public String postRegister(Model model, @ModelAttribute PurpleUser pu, @RequestParam String confirm_password, RedirectAttributes redirectAttributes) {
		pu.setEnabled(true);
		pu.setAuthorities(Arrays.asList(PurpleRole.USER));
		if(!confirm_password.equals(pu.getPassword())) {
			System.out.println("PasswordMatch");
			redirectAttributes.addFlashAttribute("error", "passworddonotmatch");
			return "redirect:/register";
		}
		if(isWeakPassword(pu.getPassword())) {
			System.out.println("PasswordWeak");
			redirectAttributes.addFlashAttribute("error", "passwordtooweak");
			return "redirect:/register";
		}
		pu.setPassword(passwordEncoder.encode(pu.getPassword()));
		if(purpleUserRepo.existsByUsername(pu.getUsername())) {
			redirectAttributes.addFlashAttribute("error", "usernamealreadyused");
			return "redirect:/register";
		}
		if(!isEmailValid(pu.getEmail())) {
			redirectAttributes.addFlashAttribute("error", "emailinvalid");
			return "redirect:/register";
		}
		if(purpleUserRepo.existsByEmail(pu.getEmail())) {
			redirectAttributes.addFlashAttribute("error", "emailalreadyused");
			return "redirect:/register";
		}
		pu.setEnabled(true);
		pu.setAccountNonExpired(true);
		pu.setAccountNonLocked(true);
		pu.setCredentialsNonExpired(true);
		purpleUserRepo.save(pu);
		return "redirect:/home";
	}
	
	private boolean isWeakPassword(String password) {
		return password == null || password.length() <= 8 ||
				// Contient pas de majuscule
				password.equals(password.toLowerCase()) ||
				// Contient pas de minuscule
				password.equals(password.toUpperCase()) ||
				// Contient pas de char spéciaux
				!Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE).matcher(password).find();
	}

	private boolean isEmailValid(String email) {
		System.out.println("Mail : " +email);
		return EmailValidator.getInstance().isValid(email);
	}
}

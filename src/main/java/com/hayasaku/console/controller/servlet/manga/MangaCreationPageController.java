package com.hayasaku.console.controller.servlet.manga;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.hayasaku.console.dto.Manga;
import com.hayasaku.console.entity.DiscordUser;

@Controller
public class MangaCreationPageController {
	@GetMapping(value = {"/guild/{guildId}/manga/new"})
	public String getManga(Model model, @SessionAttribute DiscordUser user, @ModelAttribute(name = "error") String error,@ModelAttribute(name = "manga") Manga manga) {
		if(StringUtils.isNotBlank(error)) {
			model.addAttribute("error", error);
		}
		model.addAttribute("manga",manga);
		model.addAttribute("mode", "create");
		return "mangaDetail";
	}
}

package com.hayasaku.console.controller.servlet.manga;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.hayasaku.console.entity.DiscordUser;
import com.hayasaku.console.entity.Type;
import com.hayasaku.console.service.CommandService;

@Controller
public class MangaController {
	
	@Autowired
	CommandService commandService;
	
	@GetMapping(value = {"/guild/{guildId}/manga"})
	public String getManga(Model model, @SessionAttribute DiscordUser user, @PathVariable String guildId, @ModelAttribute(name = "error") String error) {
		if(StringUtils.isNotBlank(error)) {
			model.addAttribute("error", error);
		}
		model.addAttribute("commands",commandService.findAllMangaByGuildId(guildId));
		model.addAttribute("type",Type.MANGA);
		return "commandList";
	}
}

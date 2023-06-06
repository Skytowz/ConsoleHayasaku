package com.hayasaku.console.controller.servlet;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.hayasaku.console.entity.DiscordGuild;
import com.hayasaku.console.entity.DiscordUser;

import jakarta.servlet.http.HttpSession;

@Controller
public class GuildController {

	
	@GetMapping(value = {"/guild/{guildId}"})
	public String getHome(Model model,@PathVariable String guildId,@SessionAttribute DiscordUser user, HttpSession session) {
		DiscordGuild choosenGuild = user.getGuilds().stream().filter(guild -> guild.getId().equals(guildId)).findFirst().orElse(null);
		session.setAttribute("currentGuild", choosenGuild);
		return "guild";
	}
}

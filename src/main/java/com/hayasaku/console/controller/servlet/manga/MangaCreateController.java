package com.hayasaku.console.controller.servlet.manga;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hayasaku.console.dto.Manga;
import com.hayasaku.console.entity.DiscordUser;
import com.hayasaku.console.service.CommandService;

@Controller
public class MangaCreateController {

	@Autowired
	CommandService commandService;
	
	@PostMapping(value= "/guild/{guildId}/manga/create")
	public String postManga(Model model, @SessionAttribute DiscordUser user, @PathVariable String guildId, @ModelAttribute Manga manga, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("manga",manga);
		if(StringUtils.isBlank(manga.getName())) {
			redirectAttributes.addFlashAttribute("error","Le nom n'est pas pas correct");
			return "redirect:/guild/"+guildId+"/manga/new";
		}
		if(StringUtils.isBlank(manga.getDescription())) {
			redirectAttributes.addFlashAttribute("error","La description n'est pas pas correcte");
			return "redirect:/guild/"+guildId+"/manga/new";
		}
		if(StringUtils.isBlank(manga.getIdManga())) {
			redirectAttributes.addFlashAttribute("error","L'ID Mangadex n'est pas pas correct");
			return "redirect:/guild/"+guildId+"/manga/new";
		}
		if(StringUtils.isBlank(manga.getIdManga())) {
			redirectAttributes.addFlashAttribute("error","Le trigger n'est pas correct");
			return "redirect:/guild/"+guildId+"/manga/new";
		}
//		if(manga.getCaller().stream().anyMatch(call -> commandService.exist(guildId, call))) {
//			redirectAttributes.addFlashAttribute("error","Certaine commande existe déjà");
//			return "redirect:/guild/"+guildId+"/manga/new";
//		}
		
		
		String cmd = manga.getTrigger()+" <chap> [page]";
		manga.setCmd(cmd);
		Manga newManga = commandService.saveManga(manga);
		return "redirect:/guild/"+guildId+"/manga/"+newManga.getId()+"/"+newManga.getName();
	}
 
}

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
public class MangaUpdateController {

	@Autowired
	CommandService commandService;
	
	@PostMapping(value= "/guild/{guildId}/manga/update")
	public String postManga(Model model, @SessionAttribute DiscordUser user, @PathVariable String guildId, @ModelAttribute Manga manga, RedirectAttributes redirectAttributes) {
		if(manga.getCommandId() == null) {
			redirectAttributes.addFlashAttribute("error","Le manga n'existe pas où a été supprimé");
			return "redirect:/guild/"+guildId+"/manga";
		}
		Manga oldManga = commandService.findMangaById(manga.getCommandId());
		
		if(StringUtils.isBlank(manga.getName())) {
			redirectAttributes.addFlashAttribute("error","Le nom n'est pas pas correct");
			return "redirect:/guild/"+guildId+"/manga/"+oldManga.getCommandId()+"/"+oldManga.getName();
		}
		if(StringUtils.isBlank(manga.getHelp())) {
			redirectAttributes.addFlashAttribute("error","La description n'est pas pas correcte");
			return "redirect:/guild/"+guildId+"/manga/"+oldManga.getCommandId()+"/"+oldManga.getName();
		}
		if(StringUtils.isBlank(manga.getIdManga())) {
			redirectAttributes.addFlashAttribute("error","L'ID Mangadex n'est pas pas correct");
			return "redirect:/guild/"+guildId+"/manga/"+oldManga.getCommandId()+"/"+oldManga.getName();
		}
		if(manga.getCaller() == null || manga.getCaller().isEmpty() || !manga.getCaller().stream().anyMatch(StringUtils::isNotBlank)) {
			redirectAttributes.addFlashAttribute("error","Il n'y a aucune commande correcte");
			return "redirect:/guild/"+guildId+"/manga/"+oldManga.getCommandId()+"/"+oldManga.getName();
		}
		manga.setCaller(manga.getCaller().stream().filter(StringUtils::isNotBlank).toList());
		if(!manga.getCaller().stream().allMatch(StringUtils::isAllLowerCase)) {
			redirectAttributes.addFlashAttribute("error","Les commandes doivent être impérativement en minuscule");
			return "redirect:/guild/"+guildId+"/manga/"+oldManga.getCommandId()+"/"+oldManga.getName();
		}
		
		String cmd = String.join("/",manga.getCaller())+" <chap> [page]";
		manga.setCmd(cmd);
		Manga newManga = commandService.saveManga(manga);
		return "redirect:/guild/"+guildId+"/manga/"+newManga.getCommandId()+"/"+newManga.getName();
	}
 
}

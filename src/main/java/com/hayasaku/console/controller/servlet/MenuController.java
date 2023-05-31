package com.hayasaku.console.controller.servlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hayasaku.console.model.dto.PurpleUser;
import com.hayasaku.console.model.service.room.RoomService;
import com.hayasaku.console.model.service.user.PurpleUserService;

/**
 * Controleurs pour la redirection au Menu principal
 * @author Quentin "Ruendan" DUBOIS
 *
 */
@Controller
public class MenuController {

	@Autowired
	private PurpleUserService userService;
	@Autowired
	private RoomService roomService;

	@GetMapping(value = {"menu","/menu"})
	public String getMenu(Model model, @RequestParam(required = false) String error, @AuthenticationPrincipal PurpleUser user) {
		
		model.addAttribute("friends", userService.findUsersFriends(user.getId()));
		model.addAttribute("rooms", roomService.findAllRooms());
		return "public/menu";
	}
}

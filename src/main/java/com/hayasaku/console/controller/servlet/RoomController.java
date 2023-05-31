package com.hayasaku.console.controller.servlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hayasaku.console.model.dto.HayasakuUser;
import com.hayasaku.console.model.dto.Room;
import com.hayasaku.console.model.service.room.RoomService;
import com.hayasaku.console.model.service.user.HayasakuUserService;

/**
 * Controleurs pour la gestion des rooms
 * @author Lucas "Skytowz" HOTTIN
 *
 */
@Controller
public class RoomController {

	@Autowired
	private RoomService roomService;
	@Autowired
	private HayasakuUserService userService;

	@GetMapping(value = { "/rooms", "/rooms/" })
	public String getRooms(@RequestParam(required = false, defaultValue = "0") int page, Model model,
			@ModelAttribute String noroomerror) {
		if (noroomerror != null && !"".equals(noroomerror)) model.addAttribute(noroomerror);
		model.addAttribute("rooms", roomService.findAll(PageRequest.of(page, 20)));
		return "rooms";
	}

	@GetMapping(value = { "/room", "/room/" })
	public String getRoom(Model model, @RequestParam(required = true) String roomname,
			RedirectAttributes redirectAttributes, @AuthenticationPrincipal HayasakuUser user) {
		Room room = roomService.findByName(roomname);
		model.addAttribute("room", room);
		user.setCurrentRoom(room);
		userService.updateUser(user);
		return "room";
	}
}

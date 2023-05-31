package com.hayasaku.console.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hayasaku.console.model.dao.HayasakuUserRepository;
import com.hayasaku.console.model.dto.HayasakuUser;

/**
 * Classe de Webservices Rest pour l'endpoint "/profile"
 * @author Lucas "Skytowz" HOTTIN
 *
 */
@RestController("/profile")
public class ProfileRestController {
	
	@Autowired
	private HayasakuUserRepository HayasakuUserRepo;
	
	@ResponseBody
	@GetMapping(value = "/pictures/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public byte[] getProfileInfos(@PathVariable Long id) {
		HayasakuUser pu = HayasakuUserRepo.findById(id).orElse(null);
		if(pu == null) return "{'message': ''}".getBytes();
		return pu.getProfilePicture();
	}
}

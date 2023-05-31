package com.hayasaku.console.controller.listener;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import com.hayasaku.console.model.dto.HayasakuUser;
import com.hayasaku.console.model.service.user.HayasakuUserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Handler afin de gérer proprement la déconnexion d'utilisateurs {@link DisconnectSessionListener}
 * @author Lucas "Skytowz" HOTTIN
 *
 */
@Service
public class HayasakuLogoutHandler implements LogoutHandler {
	
	@Autowired
	private HayasakuUserService userService;
	
	@Autowired
	private Log logger;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, 
      Authentication authentication) {
    	HayasakuUser user = (HayasakuUser) authentication.getPrincipal();
    	logger.warn("Disconnecting User : " + user.getUsername());
        userService.disconnect(user);
    }
}
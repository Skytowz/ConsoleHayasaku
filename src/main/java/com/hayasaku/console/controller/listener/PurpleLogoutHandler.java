package com.hayasaku.console.controller.listener;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import com.hayasaku.console.model.dto.PurpleUser;
import com.hayasaku.console.model.service.user.PurpleUserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Handler afin de gérer proprement la déconnexion d'utilisateurs {@link DisconnectSessionListener}
 * @author Quentin "Ruendan" DUBOIS
 *
 */
@Service
public class PurpleLogoutHandler implements LogoutHandler {
	
	@Autowired
	private PurpleUserService userService;
	
	@Autowired
	private Log logger;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, 
      Authentication authentication) {
    	PurpleUser user = (PurpleUser) authentication.getPrincipal();
    	logger.warn("Disconnecting User : " + user.getUsername());
        userService.disconnect(user);
    }
}
package com.hayasaku.console.controller.listener;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.hayasaku.console.model.dto.HayasakuUser;
import com.hayasaku.console.model.service.user.HayasakuUserService;

import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

/**
 * Classe servant a fournir un Listener de Session afin d'effectuer des actions à la deconnection d'un utilisateur
 * @author Lucas "Skytowz" HOTTIN
 *
 */
@Configuration
public class DisconnectSessionListener {
	
	@Autowired
	private HayasakuUserService userService;
	
	@Autowired
	private Log logger;
	
	@Bean
	public HttpSessionListener httpSessionListener() {
		 return new HttpSessionListener() {
		     @Override
		     public void sessionCreated(HttpSessionEvent se) {
		    	 if(se != null && se.getSession() != null) logger.info(se.getSession().getId());
		     }
		     
		     @Override
		     public void sessionDestroyed(HttpSessionEvent se) {
		    	 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		    	 if(authentication == null || authentication.getPrincipal() == null || !(authentication.getDetails() instanceof HayasakuUser)) return;
		    	 HayasakuUser userToDisconnect = (HayasakuUser) authentication.getDetails();
		    	 userService.disconnect(userToDisconnect);
		     }
		 };
	}
}

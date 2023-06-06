package com.hayasaku.console.config.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.hayasaku.console.entity.DiscordUser;
import com.hayasaku.console.service.DiscordService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Configuration
public class DiscordAuthenticationSuccessHandler implements AuthenticationSuccessHandler {


	@Autowired
	private OAuth2AuthorizedClientService authorizedClientService;
	
	@Autowired
	private DiscordService discordService;
	
	@Autowired
	private DiscordUser sakuBot;
	
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,  HttpServletResponse response, Authentication authentication) throws IOException {
    	OAuth2AuthenticationToken auth = (OAuth2AuthenticationToken) authentication;
    	HttpSession session = request.getSession();
		OAuth2AuthorizedClient client = authorizedClientService
			      .loadAuthorizedClient(
			        auth.getAuthorizedClientRegistrationId(), 
			          auth.getName());
		DiscordUser user = discordService.getUserByToken(client.getAccessToken().getTokenValue(),false);
		user.setGuilds(discordService.getMutualGuildsWithRight(user));
		session.setAttribute("user",user);
    	redirectStrategy.sendRedirect(request, response, "/home");
    }
    
}

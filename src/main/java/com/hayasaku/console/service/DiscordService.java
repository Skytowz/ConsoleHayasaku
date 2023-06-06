package com.hayasaku.console.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hayasaku.console.entity.DiscordGuild;
import com.hayasaku.console.entity.DiscordUser;

@Service
public class DiscordService {
	
	private final static String USERAGENT = "DiscordApp Hayasaku";
	private final static String DISCORD_URL_API = "https://discord.com/api/";
	private final static int PERMISSIONS = 0x20000000 | 0x8;
	
	@Value("${hayasaku.console.sakubot.token}")
	private String sakuBotToken;
	
	@Bean
	@Scope("application")
	public DiscordUser sakuBot() {
		DiscordUser sakuBot = this.getUserByToken(sakuBotToken,true);
		sakuBot.setGuilds(this.getAllGuildsByToken(sakuBot.getToken(),true));
		return sakuBot;

	}
	
	
	private HttpEntity<String> getHeaderWithToken(String token,boolean isBot){
		HttpHeaders headers = new HttpHeaders();
		
		headers.set("Authorization", (isBot ? "Bot ":"Bearer ") + token);
		headers.set("User-Agent", USERAGENT);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    return new HttpEntity<String>(headers);
	}

	public DiscordUser getUserByToken(String token, boolean isBot) {
		RestTemplate restTemplate = new RestTemplate();
	    HttpEntity<String> entity = getHeaderWithToken(token, isBot);
		ResponseEntity<DiscordUser> userResponse = restTemplate.exchange(DISCORD_URL_API+"users/@me",HttpMethod.GET,entity,DiscordUser.class);
		DiscordUser user = userResponse.getBody();
		user.setToken(token);
		return user;
	}

	public List<DiscordGuild> getAllGuildsByToken(String token, boolean isBot) {
		List<DiscordGuild> guilds = new ArrayList<>();
		RestTemplate restTemplate = new RestTemplate();
	    HttpEntity<String> entity = getHeaderWithToken(token, isBot);
	    ResponseEntity<List<DiscordGuild>> response = restTemplate.exchange(DISCORD_URL_API+"users/@me/guilds", HttpMethod.GET,entity,new ParameterizedTypeReference<List<DiscordGuild>>() {});
		return response.getBody();
	}

	public List<DiscordGuild> getMutualGuildsWithRight(DiscordUser user) {
		List<DiscordGuild> allGuilds = this.getAllGuildsByToken(user.getToken(), user.isBot());
		List<DiscordGuild> usableGuilds = new ArrayList<>();
		DiscordUser sakuBot = sakuBot();
		if(allGuilds != null) {
			usableGuilds = allGuilds.stream()
					.filter(guild -> sakuBot.getGuilds().contains(guild))
					.filter(guild -> ((guild.getPermissions() & PERMISSIONS) != 0) || guild.isOwner()).toList();
		}
		return usableGuilds;
	}
	
}

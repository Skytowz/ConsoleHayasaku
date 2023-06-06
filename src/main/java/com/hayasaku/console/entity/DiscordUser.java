package com.hayasaku.console.entity;

import java.util.List;

import lombok.Data;

@Data
public class DiscordUser {
	
	private final String URL_AVATAR = "https://cdn.discordapp.com/avatars/";
	
	private String id;
	private String username;
	private String avatar;
	private String token;
	private boolean bot;
	
	private List<DiscordGuild> guilds;
	
	public String getUrlAvatar() {
		return URL_AVATAR + id + "/" + avatar + ".png";
	} 
}

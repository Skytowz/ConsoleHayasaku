package com.hayasaku.console.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class DiscordGuild {
	private final String URL_ICON = "https://cdn.discordapp.com/icons/";
	
	@EqualsAndHashCode.Include 
	private String id;
	private String name;
	private String icon;
	@EqualsAndHashCode.Exclude 
	private boolean owner;
	@EqualsAndHashCode.Exclude 
	private int permissions;
	
	public String getUrlIcon() {
		return URL_ICON + id + "/" + icon + ".png";
	}
}

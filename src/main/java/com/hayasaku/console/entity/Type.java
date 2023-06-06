package com.hayasaku.console.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Type {
	MANGA("Manga","manga"),GUILD_COMMAND("Commande Personalisé","command");
	
	private String typeName;
	private String path;

}

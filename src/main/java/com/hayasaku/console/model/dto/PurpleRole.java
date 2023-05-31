package com.hayasaku.console.model.dto;

import java.io.Serializable;

import org.springframework.security.core.GrantedAuthority;

/**
 * Class PurpleRole pour renseigner les roles d'un utilisateur
 * @author Quentin "Ruendan" DUBOIS
 *
 */
public enum PurpleRole implements GrantedAuthority, Serializable {
	USER("USER"), 
	ADMIN("ADMIN"), 
	DEV("DEV");
	
	private String authority;

	private PurpleRole(String authority) {
		this.authority = authority;
	}

	@Override
	public String getAuthority() {
		return this.authority;
	}
}

package com.hayasaku.console.model.dto;

import java.io.Serializable;

import org.springframework.security.core.GrantedAuthority;

/**
 * Class HayasakuRole pour renseigner les roles d'un utilisateur
 * @author Lucas "Skytowz" HOTTIN
 *
 */
public enum HayasakuRole implements GrantedAuthority, Serializable {
	USER("USER"), 
	ADMIN("ADMIN"), 
	DEV("DEV");
	
	private String authority;

	private HayasakuRole(String authority) {
		this.authority = authority;
	}

	@Override
	public String getAuthority() {
		return this.authority;
	}
}

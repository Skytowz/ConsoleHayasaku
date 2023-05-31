package com.hayasaku.console.config.unleash;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.annotation.RequestScope;

import com.hayasaku.console.model.dto.PurpleUser;

import no.finn.unleash.UnleashContext;
import no.finn.unleash.UnleashContextProvider;

/**
 * Classe de configuration pour Unleash afin de fournir un context
 * @author Quentin "Ruendan" DUBOIS
 *
 */
@Component
@RequestScope
public class PurpleUnleashContextProvider implements UnleashContextProvider {

    @Override
    public UnleashContext getContext() {
    	String id = "";
    	if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof PurpleUser) {
    		PurpleUser user = ((PurpleUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    		id = (!StringUtils.hasLength(user.getUsername()) ? "annon" : user.getUsername());
    	} else {
    		id = (SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
    	}
        return UnleashContext.builder()
                .userId(id)
                .build();
    }
}

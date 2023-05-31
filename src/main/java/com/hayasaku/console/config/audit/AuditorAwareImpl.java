package com.hayasaku.console.config.audit;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;

import com.hayasaku.console.model.dto.PurpleUser;

/**
 * Classe de configuration pour l'audit afin de savoir quel utilisateur effectue des modifications en base
 * @author Quentin "Ruendan" DUBOIS
 *
 */
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
    	if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof PurpleUser) {
    		PurpleUser user = ((PurpleUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    		return Optional.of(!StringUtils.hasLength(user.getUsername()) ? "annon" : user.getUsername());
    	} else {
    		return Optional.of(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
    	}

    }

}
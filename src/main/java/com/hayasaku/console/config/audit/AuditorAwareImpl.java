package com.hayasaku.console.config.audit;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Classe de configuration pour l'audit afin de savoir quel utilisateur effectue des modifications en base
 * @author Lucas "Skytowz" HOTTIN
 *
 */
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
    		Object test = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    		return Optional.of("test");

    }

}
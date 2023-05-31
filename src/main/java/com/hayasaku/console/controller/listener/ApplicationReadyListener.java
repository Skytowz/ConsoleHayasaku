package com.hayasaku.console.controller.listener;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import io.sentry.Sentry;
import no.finn.unleash.EvaluatedToggle;
import no.finn.unleash.Unleash;
import no.finn.unleash.UnleashContext;

/**
 * Listener du chargement de l'application afin d'effectuer des actions à son démarage
 * @author Quentin "Ruendan" DUBOIS
 *
 */
@Configuration
public class ApplicationReadyListener {

	@Autowired
	private Log logger;

	@Autowired
	private Unleash unleash;
	
	@Value("${server.port}")
	private String port;

	@EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup() {
		logger.info("Purplenpink Informations Check");
		logger.info("");
		logger.info("[x] Purplenpink Feature Flags");
		for(EvaluatedToggle toggle : unleash.more().evaluateAllToggles(UnleashContext.builder().userId("Ruendan").build())) {
			logger.info("    - %s : %s ".formatted(toggle.getName(), toggle.isEnabled() ? "enabled " : "disabled"));
		}
		logger.info("");
		logger.info("[x] Purplenpink Sentry");
		logger.info("    - %s : %s ".formatted("Enabled", Sentry.isEnabled() ? "yes" : "no"));
		logger.info("");
	    logger.info("Purplenpink Successfully Started on port %s".formatted(port));
	}
}

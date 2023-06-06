package com.hayasaku.console.controller.listener;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;


/**
 * Listener du chargement de l'application afin d'effectuer des actions à son démarage
 * @author Lucas "Skytowz" HOTTIN
 *
 */
@Configuration
public class ApplicationReadyListener {

	@Autowired
	private Log logger;

	@Value("${server.port}")
	private String port;
	
	@EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup() {
		logger.info("Hayasaku Informations Check");
		logger.info("");
	    logger.info("Hayasaku Successfully Started on port %s".formatted(port));
	}
}

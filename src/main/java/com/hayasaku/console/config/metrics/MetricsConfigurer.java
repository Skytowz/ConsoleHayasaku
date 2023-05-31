package com.hayasaku.console.config.metrics;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.MeterRegistry;

/**
 * Classe de configuration pour les metrics prometheus, afin de permettre l'annotation @Timed
 * @author Quentin "Ruendan" DUBOIS
 *
 */
@Configuration
public class MetricsConfigurer {
	@Bean
	public TimedAspect timedAspect(MeterRegistry registry) {
	    return new TimedAspect(registry);
	}
}

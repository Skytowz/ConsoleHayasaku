package com.hayasaku.console.config.unleash;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import no.finn.unleash.DefaultUnleash;
import no.finn.unleash.FakeUnleash;
import no.finn.unleash.Unleash;
import no.finn.unleash.UnleashContextProvider;
import no.finn.unleash.util.UnleashConfig;

/**
 * Classe de configuration pour Unleash afin de verifier les FeatureFlags
 * @author Quentin "Ruendan" DUBOIS
 *
 */
@Configuration
public class PurpleUnleashConfigurer {
	
    @Value("${unleash.appName}")
    private String appName;
    
    @Value("${unleash.instanceId}")
    private String instanceId;
    
    @Value("${unleash.apiUrl}")
    private String apiUrl;
    
    @Value("${unleash.clientSecret}")
    private String clientSecret;
    
    @Value("${pnp.env}")
    private String env;
    
    @Autowired
    private UnleashContextProvider unleashContextProvider;

    @Bean
    public UnleashConfig unleashConfig() {
        return UnleashConfig.builder()
                .appName(appName)
                .instanceId(instanceId)
                .unleashAPI(apiUrl)
                .unleashContextProvider(unleashContextProvider)
                .build();
    }

    @Bean
    public Unleash unleash(UnleashConfig unleashConfig) {
    	return Arrays.asList("production","staging").contains(env) ? new DefaultUnleash(unleashConfig) : fakeUnleash();
    }
    
    private Unleash fakeUnleash() {
    	FakeUnleash res = new FakeUnleash();
    	res.enableAll();
    	return res;
    }
}
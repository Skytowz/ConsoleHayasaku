package com.hayasaku.console.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestCustomizers;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestRedirectFilter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class HayasakuSecurityConfigurer{
	
	@Autowired
    public DiscordAuthenticationSuccessHandler discordAuthenticationSuccessHandler;

	
	@Bean
	public PasswordEncoder passwordEncoder() {
		DelegatingPasswordEncoder res = (DelegatingPasswordEncoder) PasswordEncoderFactories
				.createDelegatingPasswordEncoder();
		return res;
	}
	
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, ClientRegistrationRepository clientRegistrationRepository) throws Exception {
        String base_uri = OAuth2AuthorizationRequestRedirectFilter.DEFAULT_AUTHORIZATION_REQUEST_BASE_URI;
        DefaultOAuth2AuthorizationRequestResolver resolver = new DefaultOAuth2AuthorizationRequestResolver(clientRegistrationRepository, base_uri);
        resolver.setAuthorizationRequestCustomizer (OAuth2AuthorizationRequestCustomizers.withPkce () ) ;
        http
            .authorizeHttpRequests()
                .anyRequest().authenticated()
            .and()
            	.csrf()
				.ignoringRequestMatchers((request) -> request.getRequestURL().toString().contains("/h2-console/"))
			.and()
				.headers()
				.frameOptions().sameOrigin()
            .and()
            	.oauth2Login()
            	.loginPage("/oauth2/authorization/discord")
             	.authorizationEndpoint().authorizationRequestResolver(resolver)
         	.and()
         		.successHandler(discordAuthenticationSuccessHandler)
         	.and()
         		.logout().logoutUrl("/logout");
     	return http.build();
    }
    
    
}
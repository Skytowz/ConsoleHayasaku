package com.hayasaku.console.config.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.hayasaku.console.controller.listener.PurpleLogoutHandler;
import com.hayasaku.console.model.dto.PurpleUser;
import com.hayasaku.console.model.service.user.PurpleUserDetailsService;

/**
 * Classe de configuration pour SpringSecurity
 *  - Méthodes d'authentification
 *  - PermitAlls
 *  - ContentSecurityPolicy
 *  - Pages d'accueil
 * @author Quentin "Ruendan" DUBOIS
 *
 */
@Configuration
public class HayasakuSecurityConfigurer {

	@Autowired
	private PurpleLogoutHandler purpleLogoutHandler;

	@Bean
	protected UserDetailsService userDetailsService() {
		return new PurpleUserDetailsService();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		DelegatingPasswordEncoder res = (DelegatingPasswordEncoder) PasswordEncoderFactories
				.createDelegatingPasswordEncoder();
		return res;
	}

	@Order(1)
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}
	
	@Order(0)
	@Bean
	public AuthenticationProvider inMemoryAuthenticationProvider() {
		return new AuthenticationProvider() {
			
			@Override
			public boolean supports(Class<?> authentication) {return true;}
			
			@Override
			public Authentication authenticate(Authentication authentication) throws AuthenticationException {
				if(!"HayasakuMonitoring".equals(authentication.getName()) || !"m0N1T0r1nG_p4sSW0Rd_Hayasaku".equals(authentication.getCredentials().toString())) throw new BadCredentialsException("Not Monitoring");
				return UsernamePasswordAuthenticationToken.authenticated(new PurpleUser(), authentication.getCredentials(), Arrays.asList(new SimpleGrantedAuthority("MONITORING")));
			}
		};
	}

	@Bean
	public AuthenticationManager authManager(HttpSecurity http) throws Exception {
		AuthenticationManagerBuilder authenticationManagerBuilder = 
		          http.getSharedObject(AuthenticationManagerBuilder.class);
		authenticationManagerBuilder.authenticationProvider(inMemoryAuthenticationProvider());
		authenticationManagerBuilder.authenticationProvider(authenticationProvider());
		return authenticationManagerBuilder.build();
	}
	
	private static final String CSP = "default-src 'self'; "
			+ "style-src 'unsafe-inline' 'self' https://cdn.jsdelivr.net/npm/bulma@0.9.4/css/bulma.min.css https://cdn.jsdelivr.net/npm/bulma-social@2/css/all.min.css ; "
			+ "script-src 'unsafe-inline' 'self' https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js "
			+ "https://use.fontawesome.com/releases/v5.3.1/js/all.js ; "
			+ "img-src 'self' http://placekitten.com/128/128 http://placekitten.com/1280/960"
			+ " https://bulma.io/images/placeholders/1280x960.png https://bulma.io/images/placeholders/96x96.png https://bulma.io/images/placeholders/128x128.png https://flagicons.lipis.dev/flags/4x3/fr.svg https://bulma.io/images/made-with-bulma.png";


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authManager) throws Exception {
    	http.httpBasic().and()
    		.authorizeHttpRequests().requestMatchers("/scripts/**", "/css/**").permitAll()
				.requestMatchers("/").permitAll()
				.requestMatchers("/favicon.ico").permitAll()
				.requestMatchers("/WEB-INF/jsp/**").permitAll()
				.requestMatchers("/logo.jpg").permitAll()
				.requestMatchers("/index").permitAll()
				.requestMatchers("/error").permitAll()
				.requestMatchers("/navbar").permitAll()
				.requestMatchers("/register").permitAll()
				.requestMatchers("/login").permitAll()
				.requestMatchers("/about").permitAll()
				.requestMatchers("/contact").permitAll()
				.requestMatchers("/report").permitAll()
				.requestMatchers("/newSuggestion").permitAll()
				.requestMatchers("/home").permitAll()
				.requestMatchers("/scripts/**").permitAll()
				.requestMatchers("/style/**").permitAll()
				.requestMatchers("/ws/**").permitAll()
				.requestMatchers("/h2-console/**").hasAuthority("ADMIN")
				.requestMatchers("/actuator").hasAuthority("MONITORING")
				.requestMatchers("/actuator/prometheus").hasAuthority("MONITORING")
				.requestMatchers("/admin/**").hasAuthority("ADMIN")
				.anyRequest().authenticated()
				.and()
			.csrf()
				.ignoringRequestMatchers("/ws/**")
				.ignoringRequestMatchers("/actuator")
				.ignoringRequestMatchers("/actuator/prometheus")
				.ignoringRequestMatchers((request) -> request.getRequestURL().toString().contains("/h2-console/"))
				.and()
			.headers()
				.xssProtection()
				.and()
				.contentSecurityPolicy(CSP) // Content Security Policy
				.and()
				.frameOptions().sameOrigin() // Clickjacking
				.and()
			.formLogin()
				.loginPage("/home")
				.loginProcessingUrl("/login")
				.defaultSuccessUrl("/rooms", true)
				.failureUrl("/home?error=true")
				.and()
			.authenticationManager(authManager)
			.logout().logoutUrl("/logout").permitAll()
				.invalidateHttpSession(true)
				.addLogoutHandler(purpleLogoutHandler)
				.logoutSuccessUrl("/home")
			.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
    	return http.build();
	}
}

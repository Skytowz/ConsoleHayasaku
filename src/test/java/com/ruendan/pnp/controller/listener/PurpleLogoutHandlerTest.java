package com.ruendan.pnp.controller.listener;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.apache.commons.logging.Log;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Description;
import org.springframework.security.core.Authentication;

import com.hayasaku.console.controller.listener.PurpleLogoutHandler;
import com.hayasaku.console.model.dto.PurpleUser;
import com.hayasaku.console.model.service.user.PurpleUserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SpringBootTest
class PurpleLogoutHandlerTest {

	@MockBean
	private PurpleUserService purpleUserService;
	@MockBean
	private Log logger;
	
    @InjectMocks
	private PurpleLogoutHandler purpleLogoutHandler;
    
	@Test
	@Description("PurpleLogoutHandler#logout")
	void logout() {
		PurpleUser purpleUser = new PurpleUser();
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		Authentication auth = mock(Authentication.class);
		when(auth.getPrincipal()).thenReturn(purpleUser);
		
		MockitoAnnotations.openMocks(this);

		purpleLogoutHandler.logout(request, response, auth);
		
		verify(purpleUserService, times(1)).disconnect(purpleUser);
	}

}

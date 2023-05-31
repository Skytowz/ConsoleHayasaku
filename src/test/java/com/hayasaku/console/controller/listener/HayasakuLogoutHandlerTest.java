package com.hayasaku.console.controller.listener;

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

import com.hayasaku.console.controller.listener.HayasakuLogoutHandler;
import com.hayasaku.console.model.dto.HayasakuUser;
import com.hayasaku.console.model.service.user.HayasakuUserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SpringBootTest
class HayasakuLogoutHandlerTest {

	@MockBean
	private HayasakuUserService HayasakuUserService;
	@MockBean
	private Log logger;
	
    @InjectMocks
	private HayasakuLogoutHandler HayasakuLogoutHandler;
    
	@Test
	@Description("HayasakuLogoutHandler#logout")
	void logout() {
		HayasakuUser HayasakuUser = new HayasakuUser();
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		Authentication auth = mock(Authentication.class);
		when(auth.getPrincipal()).thenReturn(HayasakuUser);
		
		MockitoAnnotations.openMocks(this);

		HayasakuLogoutHandler.logout(request, response, auth);
		
		verify(HayasakuUserService, times(1)).disconnect(HayasakuUser);
	}

}

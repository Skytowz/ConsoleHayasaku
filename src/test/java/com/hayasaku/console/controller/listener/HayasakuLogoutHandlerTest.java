package com.hayasaku.console.controller.listener;

import org.apache.commons.logging.Log;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Description;

@SpringBootTest
class HayasakuLogoutHandlerTest {

	@MockBean
	private Log logger;
	
    @InjectMocks
	private HayasakuLogoutHandler HayasakuLogoutHandler;
    
	@Test
	@Description("HayasakuLogoutHandler#logout")
	void logout() {

	}

}

package com.hayasaku.console.model.service.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Description;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.hayasaku.console.model.dao.HayasakuUserRepository;
import com.hayasaku.console.model.dto.HayasakuUser;
import com.hayasaku.console.model.service.user.HayasakuUserDetailsService;

import io.micrometer.common.util.StringUtils;

@SpringBootTest
class HayasakuUserDetailsServiceTest {

	@MockBean
	private HayasakuUserRepository userRepo;
    
    @InjectMocks
	private HayasakuUserDetailsService HayasakuUserDetailsService;
    
    private List<HayasakuUser> userLists;
    private HayasakuUser HayasakuUserTest;
    
    @BeforeEach
	void reinitialize() {
		HayasakuUserTest = new HayasakuUser();
		HayasakuUserTest.setUsername("pnp");
		HayasakuUserTest.setEmail("test@pnp.com");
		userLists = List.of(HayasakuUserTest);
		
		List<HayasakuUser> HayasakuUserList = new ArrayList<>();
		HayasakuUserList.add(HayasakuUserTest);

		when(userRepo.findByUsername(any())).then((e) -> userLists.stream().filter(e2 -> StringUtils.isNotBlank(e2.getUsername()) && e2.getUsername().equals(e.getArgument(0))).findFirst());
		when(userRepo.findByEmail(any())).then((e) -> userLists.stream().filter(e2 -> StringUtils.isNotBlank(e2.getEmail()) && e2.getEmail().equals(e.getArgument(0))).findFirst());

		MockitoAnnotations.openMocks(this);
	}
    
    @Test
    @Description("HayasakuUserDetailsService#loadByUsername")
    void loadByUsername() {
    	assertThrows(UsernameNotFoundException.class, () -> HayasakuUserDetailsService.loadUserByUsername("PnPMonitoring6"));
    	assertThrows(UsernameNotFoundException.class, () -> HayasakuUserDetailsService.loadUserByUsername(""));
    	assertThrows(UsernameNotFoundException.class, () -> HayasakuUserDetailsService.loadUserByUsername(null));
    	assertThrows(UsernameNotFoundException.class, () -> HayasakuUserDetailsService.loadUserByUsername("bad_username"));
    	assertThrows(UsernameNotFoundException.class, () -> HayasakuUserDetailsService.loadUserByUsername("bad_email@mail.com"));
    	assertEquals(HayasakuUserTest, HayasakuUserDetailsService.loadUserByUsername("pnp"));
    	assertEquals(HayasakuUserTest, HayasakuUserDetailsService.loadUserByUsername("test@pnp.com"));
    }

}

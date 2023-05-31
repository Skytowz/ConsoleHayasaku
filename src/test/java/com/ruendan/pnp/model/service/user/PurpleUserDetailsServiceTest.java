package com.ruendan.pnp.model.service.user;

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

import com.hayasaku.console.model.dao.PurpleUserRepository;
import com.hayasaku.console.model.dto.PurpleUser;
import com.hayasaku.console.model.service.user.PurpleUserDetailsService;

import io.micrometer.common.util.StringUtils;

@SpringBootTest
class PurpleUserDetailsServiceTest {

	@MockBean
	private PurpleUserRepository userRepo;
    
    @InjectMocks
	private PurpleUserDetailsService purpleUserDetailsService;
    
    private List<PurpleUser> userLists;
    private PurpleUser purpleUserTest;
    
    @BeforeEach
	void reinitialize() {
		purpleUserTest = new PurpleUser();
		purpleUserTest.setUsername("pnp");
		purpleUserTest.setEmail("test@pnp.com");
		userLists = List.of(purpleUserTest);
		
		List<PurpleUser> purpleUserList = new ArrayList<>();
		purpleUserList.add(purpleUserTest);

		when(userRepo.findByUsername(any())).then((e) -> userLists.stream().filter(e2 -> StringUtils.isNotBlank(e2.getUsername()) && e2.getUsername().equals(e.getArgument(0))).findFirst());
		when(userRepo.findByEmail(any())).then((e) -> userLists.stream().filter(e2 -> StringUtils.isNotBlank(e2.getEmail()) && e2.getEmail().equals(e.getArgument(0))).findFirst());

		MockitoAnnotations.openMocks(this);
	}
    
    @Test
    @Description("PurpleUserDetailsService#loadByUsername")
    void loadByUsername() {
    	assertThrows(UsernameNotFoundException.class, () -> purpleUserDetailsService.loadUserByUsername("PnPMonitoring6"));
    	assertThrows(UsernameNotFoundException.class, () -> purpleUserDetailsService.loadUserByUsername(""));
    	assertThrows(UsernameNotFoundException.class, () -> purpleUserDetailsService.loadUserByUsername(null));
    	assertThrows(UsernameNotFoundException.class, () -> purpleUserDetailsService.loadUserByUsername("bad_username"));
    	assertThrows(UsernameNotFoundException.class, () -> purpleUserDetailsService.loadUserByUsername("bad_email@mail.com"));
    	assertEquals(purpleUserTest, purpleUserDetailsService.loadUserByUsername("pnp"));
    	assertEquals(purpleUserTest, purpleUserDetailsService.loadUserByUsername("test@pnp.com"));
    }

}

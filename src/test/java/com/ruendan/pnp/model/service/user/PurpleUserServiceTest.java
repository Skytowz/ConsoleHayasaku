package com.ruendan.pnp.model.service.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Description;

import com.hayasaku.console.model.dao.PurpleUserRepository;
import com.hayasaku.console.model.dto.PurpleUser;
import com.hayasaku.console.model.dto.Room;
import com.hayasaku.console.model.service.user.PurpleUserService;

@SpringBootTest
class PurpleUserServiceTest {
	
	@MockBean
	private PurpleUserRepository userRepo;
    
    @InjectMocks
	private PurpleUserService purpleUserService;
	
    private PurpleUser purpleUserTest;
    private PurpleUser purpleUserFriendTest;
    private Room roomTest;

    private Set<PurpleUser> userLists;
    
	@BeforeEach
	void reinitialize() {
		purpleUserTest = new PurpleUser();
		purpleUserFriendTest = new PurpleUser();
		purpleUserTest.setFriends(List.of(purpleUserFriendTest));
		purpleUserFriendTest.setFriends(List.of(purpleUserTest));
		
		roomTest = new Room();
		userLists = new HashSet<>();
		
		List<PurpleUser> purpleUserList = new ArrayList<>();
		purpleUserList.add(purpleUserTest);
		roomTest.setPurpleusers(purpleUserList);
		roomTest.setId(1L);

		purpleUserTest.setCurrentRoom(roomTest);
		
		when(userRepo.save(any())).then((e) -> {userLists.add(e.getArgument(0)); return e.getArgument(0);});
		when(userRepo.findFriendsById(any())).then((e) -> purpleUserTest.getFriends());

		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	@Description("PurpleUserService#disconnect")
	void disconnect() {
		purpleUserService.disconnect(purpleUserTest);
		
		assertNull(purpleUserTest.getCurrentRoom());
		assertEquals(0, roomTest.getPurpleusers().size());
		assertTrue(userLists.contains(purpleUserTest));
	}
	
	@Test
	@Description("PurpleUserService#updateUser - Expect userRepo.save to be called once.")
	void updateUser() {
		purpleUserService.updateUser(purpleUserTest);
		verify(userRepo, times(1)).save(any());
	}
	
	@Test
	@Description("PurpleUserService#findUsersFriends - Expect userRepo.findFriendsById to be called once.")
	void findUsersFriends() {
		assertEquals(List.of(purpleUserFriendTest), purpleUserService.findUsersFriends(1L));
		verify(userRepo, times(1)).findFriendsById(any());
	}
}

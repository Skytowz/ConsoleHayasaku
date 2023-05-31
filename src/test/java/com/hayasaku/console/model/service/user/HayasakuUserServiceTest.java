package com.hayasaku.console.model.service.user;

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

import com.hayasaku.console.model.dao.HayasakuUserRepository;
import com.hayasaku.console.model.dto.HayasakuUser;
import com.hayasaku.console.model.dto.Room;
import com.hayasaku.console.model.service.user.HayasakuUserService;

@SpringBootTest
class HayasakuUserServiceTest {
	
	@MockBean
	private HayasakuUserRepository userRepo;
    
    @InjectMocks
	private HayasakuUserService HayasakuUserService;
	
    private HayasakuUser HayasakuUserTest;
    private HayasakuUser HayasakuUserFriendTest;
    private Room roomTest;

    private Set<HayasakuUser> userLists;
    
	@BeforeEach
	void reinitialize() {
		HayasakuUserTest = new HayasakuUser();
		HayasakuUserFriendTest = new HayasakuUser();
		HayasakuUserTest.setFriends(List.of(HayasakuUserFriendTest));
		HayasakuUserFriendTest.setFriends(List.of(HayasakuUserTest));
		
		roomTest = new Room();
		userLists = new HashSet<>();
		
		List<HayasakuUser> HayasakuUserList = new ArrayList<>();
		HayasakuUserList.add(HayasakuUserTest);
		roomTest.setHayasakuusers(HayasakuUserList);
		roomTest.setId(1L);

		HayasakuUserTest.setCurrentRoom(roomTest);
		
		when(userRepo.save(any())).then((e) -> {userLists.add(e.getArgument(0)); return e.getArgument(0);});
		when(userRepo.findFriendsById(any())).then((e) -> HayasakuUserTest.getFriends());

		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	@Description("HayasakuUserService#disconnect")
	void disconnect() {
		HayasakuUserService.disconnect(HayasakuUserTest);
		
		assertNull(HayasakuUserTest.getCurrentRoom());
		assertEquals(0, roomTest.getHayasakuusers().size());
		assertTrue(userLists.contains(HayasakuUserTest));
	}
	
	@Test
	@Description("HayasakuUserService#updateUser - Expect userRepo.save to be called once.")
	void updateUser() {
		HayasakuUserService.updateUser(HayasakuUserTest);
		verify(userRepo, times(1)).save(any());
	}
	
	@Test
	@Description("HayasakuUserService#findUsersFriends - Expect userRepo.findFriendsById to be called once.")
	void findUsersFriends() {
		assertEquals(List.of(HayasakuUserFriendTest), HayasakuUserService.findUsersFriends(1L));
		verify(userRepo, times(1)).findFriendsById(any());
	}
}

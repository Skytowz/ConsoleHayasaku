package com.hayasaku.console.model.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hayasaku.console.model.dao.HayasakuUserRepository;
import com.hayasaku.console.model.dto.HayasakuUser;

import io.micrometer.core.annotation.Timed;

@Service
public class HayasakuUserService {

	@Autowired
	private HayasakuUserRepository userRepo;

	@Timed(value = "findUsers.time", description = "Time taken to find a user's friends")
	public List<HayasakuUser> findUsersFriends(Long id) {
		return userRepo.findFriendsById(id);
	}

	public void updateUser(HayasakuUser user) {
		userRepo.save(user);
	}

	public void disconnect(HayasakuUser user) {
		System.out.println("Disconnecting " + user);
		if(user.getCurrentRoom() != null) {
			user.getCurrentRoom().getHayasakuusers().remove(user);
			user.setCurrentRoom(null);
			userRepo.save(user);
		}
	}
}

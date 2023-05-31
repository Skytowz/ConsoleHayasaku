package com.hayasaku.console.model.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hayasaku.console.model.dao.PurpleUserRepository;
import com.hayasaku.console.model.dto.PurpleUser;

import io.micrometer.core.annotation.Timed;

@Service
public class PurpleUserService {

	@Autowired
	private PurpleUserRepository userRepo;

	@Timed(value = "findUsers.time", description = "Time taken to find a user's friends")
	public List<PurpleUser> findUsersFriends(Long id) {
		return userRepo.findFriendsById(id);
	}

	public void updateUser(PurpleUser user) {
		userRepo.save(user);
	}

	public void disconnect(PurpleUser user) {
		System.out.println("Disconnecting " + user);
		if(user.getCurrentRoom() != null) {
			user.getCurrentRoom().getPurpleusers().remove(user);
			user.setCurrentRoom(null);
			userRepo.save(user);
		}
	}
}

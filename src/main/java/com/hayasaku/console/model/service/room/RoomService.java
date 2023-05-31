package com.hayasaku.console.model.service.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.hayasaku.console.model.dao.RoomRepository;
import com.hayasaku.console.model.dto.Room;
import com.hayasaku.console.model.exceptions.rooms.RoomNotFoundException;

@Service
public class RoomService {
	
	@Autowired
	private RoomRepository roomRepository;
	
	public Iterable<Room> findAllRooms() {
		return roomRepository.findAll();
	}

	public Iterable<Room> findAll(PageRequest of) {
		return roomRepository.findAll(of);
	}

	public Room findByName(String roomname) throws RoomNotFoundException {
		return roomRepository.findByName(roomname).orElseThrow(() -> new RoomNotFoundException(roomname));
	}

}

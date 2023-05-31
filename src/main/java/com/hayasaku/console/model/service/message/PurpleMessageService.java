package com.hayasaku.console.model.service.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hayasaku.console.model.dao.PurpleUserRepository;
import com.hayasaku.console.model.dao.RoomRepository;
import com.hayasaku.console.model.dto.messages.MessageRoom;
import com.hayasaku.console.model.dto.messages.MessageUser;
import com.hayasaku.console.model.dto.messages.Message.MessageStatus;
import com.hayasaku.console.model.dto.messages.payload.MessageRoomPayload;
import com.hayasaku.console.model.dto.messages.payload.MessageUserPayload;

@Service
public class PurpleMessageService {

	@Autowired
	private PurpleUserRepository purpleUserRepo;
	@Autowired
	private RoomRepository purpleRoomRepo;

	public MessageRoom convertMessageRoomPayloadToMessageRoom(MessageRoomPayload payload) {
		MessageRoom res = new MessageRoom();
		res.setSender(purpleUserRepo.findById(payload.getSender()).orElseThrow());
		res.setContent(payload.getContent());
		res.setDate(payload.getDate());
		res.setRoomDestination(purpleRoomRepo.findById(payload.getRoomDestination()).orElseThrow());
		res.setStatus(MessageStatus.DELIVERED);
		return res;
	}
	
	public MessageUser convertMessageUserPayloadToMessageUser(MessageUserPayload payload) {
		MessageUser res = new MessageUser();
		res.setSender(purpleUserRepo.findById(payload.getSender()).orElseThrow());
		res.setContent(payload.getContent());
		res.setDate(payload.getDate());
		res.setUserDestination(purpleUserRepo.findById(payload.getUserDestination()).orElseThrow());
		res.setStatus(MessageStatus.DELIVERED);
		return res;
	}
}

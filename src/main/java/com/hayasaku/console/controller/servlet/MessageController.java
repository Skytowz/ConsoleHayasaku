package com.hayasaku.console.controller.servlet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hayasaku.console.model.dao.MessageRepository;
import com.hayasaku.console.model.dao.MessageRoomRepository;
import com.hayasaku.console.model.dto.messages.Message;
import com.hayasaku.console.model.dto.messages.Notification;
import com.hayasaku.console.model.dto.messages.payload.MessageRoomPayload;
import com.hayasaku.console.model.service.message.PurpleMessageService;

/**
 * Controleurs pour la gestions des WebSockets Messages
 * @author Quentin "Ruendan" DUBOIS
 *
 */
@Controller
public class MessageController {
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	@Autowired
	private MessageRepository messageRepo;
	@Autowired
	private MessageRoomRepository messageRoomRepo;
	@Autowired
	private PurpleMessageService messageService;

	@MessageMapping("/room/{roomId}")
	public void processMessage(@Payload MessageRoomPayload roomMessagePayload, @DestinationVariable Integer roomId) {
		Message message = messageService.convertMessageRoomPayloadToMessageRoom(roomMessagePayload);
		Message saved = messageRepo.save(message);
		Notification notif = new Notification();
		notif.setSender(saved.getSender());
		messagingTemplate.convertAndSend("/user/room/" + roomId + "/queue/messages", roomMessagePayload);
//		messagingTemplate.convertAndSend("/room" + roomMessagePayload.getRoomDestination(), roomMessagePayload);
	}
	
	@GetMapping(value = "/room/{id}", produces = "application/json")
	@ResponseBody
	public List<MessageRoomPayload> getRoomMessages(@PathVariable Long id) {
		return messageRoomRepo.findFirst30ByRoomDestinationAndRoomDestinationNotNull(id);
	}
}

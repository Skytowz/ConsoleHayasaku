package com.ruendan.pnp.model.service.message;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Description;

import com.hayasaku.console.model.dao.PurpleUserRepository;
import com.hayasaku.console.model.dao.RoomRepository;
import com.hayasaku.console.model.dto.PurpleUser;
import com.hayasaku.console.model.dto.Room;
import com.hayasaku.console.model.dto.messages.MessageRoom;
import com.hayasaku.console.model.dto.messages.MessageUser;
import com.hayasaku.console.model.dto.messages.Message.MessageStatus;
import com.hayasaku.console.model.dto.messages.payload.MessageRoomPayload;
import com.hayasaku.console.model.dto.messages.payload.MessageUserPayload;
import com.hayasaku.console.model.service.message.PurpleMessageService;

@SpringBootTest
class PurpleMessageServiceTest {
	
	@MockBean
	private PurpleUserRepository purpleUserRepository;
	
	@MockBean
	private RoomRepository roomRepository;
    
    @InjectMocks
	private PurpleMessageService purpleMessageService;
	
	private MessageRoomPayload messageRoomPayload;
	private MessageUserPayload messageUserPayload;
	
	private PurpleUser senderPurpleUser;
	
	private PurpleUser recieverPurpleUser;
	private Room recieverRoom;
	
	private LocalDate now = LocalDate.now();
	private String messageContent = "this is the content of a message";

	@BeforeEach
	void reinitialize() {
		senderPurpleUser = new PurpleUser();
		recieverPurpleUser = new PurpleUser();
		recieverRoom = new Room();
		
		messageRoomPayload = new MessageRoomPayload();
		messageRoomPayload.setSender(1L);
		messageRoomPayload.setRoomDestination(1L);
		messageRoomPayload.setDate(now);
		messageRoomPayload.setContent(messageContent);
		
		messageUserPayload = new MessageUserPayload();
		messageUserPayload.setSender(1L);
		messageUserPayload.setUserDestination(2L);
		messageUserPayload.setDate(now);
		messageUserPayload.setContent(messageContent);
		
		when(purpleUserRepository.findById(eq(1L))).thenReturn(Optional.of(senderPurpleUser));
		when(purpleUserRepository.findById(eq(2L))).thenReturn(Optional.of(recieverPurpleUser));
		when(roomRepository.findById(eq(1L))).thenReturn(Optional.of(recieverRoom));

		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	@Description("PurpleMessageService#convertMessageRoomPayloadToMessageRoom")
	void convertMessageRoomPayloadToMessageRoom() {
		MessageRoom roomResult = purpleMessageService.convertMessageRoomPayloadToMessageRoom(messageRoomPayload);
		assertEquals(messageContent, roomResult.getContent());
		assertEquals(now, roomResult.getDate());
		assertEquals(MessageStatus.DELIVERED, roomResult.getStatus());
		assertEquals(senderPurpleUser, roomResult.getSender());
		assertEquals(recieverRoom, roomResult.getRoomDestination());
		assertEquals(recieverRoom.getName(), roomResult.getDestinationPath());
	}
	
	@Test
	@Description("PurpleMessageService#convertMessageUserPayloadToMessageUser")
	void convertMessageUserPayloadToMessageUser() {
		MessageUser userResult = purpleMessageService.convertMessageUserPayloadToMessageUser(messageUserPayload);
		assertEquals(messageContent, userResult .getContent());
		assertEquals(now, userResult.getDate());
		assertEquals(MessageStatus.DELIVERED, userResult.getStatus());
		assertEquals(senderPurpleUser, userResult.getSender());
		assertEquals(recieverPurpleUser, userResult.getUserDestination());
		assertEquals("" + recieverPurpleUser.getId(), userResult.getDestinationPath());
	}
	
	@Test
	@Description("PurpleMessageService#convertMessageUserPayloadToMessageUser - Should throw exception if sender or reciever do not exists")
	void convertMessageUserPayloadToMessageUser_Should_Throw_Exception_If_Any_End_Dont_Exists() {
		messageUserPayload.setSender(3L);
		assertThrows(NoSuchElementException.class, () -> purpleMessageService.convertMessageUserPayloadToMessageUser(messageUserPayload));
		messageUserPayload.setSender(1L);
		messageUserPayload.setUserDestination(3L);
		assertThrows(NoSuchElementException.class, () -> purpleMessageService.convertMessageUserPayloadToMessageUser(messageUserPayload));
	}
	
	@Test
	@Description("PurpleMessageService#convertMessageRoomPayloadToMessageRoom - Should throw exception if sender or reciever do not exists")
	void convertMessageRoomPayloadToMessageRoom_Should_Throw_Exception_If_Any_End_Dont_Exists() {
		messageRoomPayload.setSender(3L);
		assertThrows(NoSuchElementException.class, () -> purpleMessageService.convertMessageRoomPayloadToMessageRoom(messageRoomPayload));
		messageRoomPayload.setSender(1L);
		messageRoomPayload.setRoomDestination(3L);
		assertThrows(NoSuchElementException.class, () -> purpleMessageService.convertMessageRoomPayloadToMessageRoom(messageRoomPayload));
	}
}

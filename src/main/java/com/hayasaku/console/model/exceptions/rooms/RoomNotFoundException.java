package com.hayasaku.console.model.exceptions.rooms;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Exception RoomNotFoundException renvoyée lorsque l'on essaye d'accéder a une Room qui n'existe pas
 * @author Lucas "Skytowz" HOTTIN
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class RoomNotFoundException extends RuntimeException {

	private String roomName;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public RoomNotFoundException(Long roomId) {
		super("Couldn't find room with id '" + roomId + "'");
		this.roomName = "" + roomId;
	}
	public RoomNotFoundException(String name) {
		super("Couldn't find room with name '" + name + "'");
		this.roomName = name;
	}
}

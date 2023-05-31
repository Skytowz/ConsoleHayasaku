package com.hayasaku.console.controller.advices;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.hayasaku.console.model.exceptions.rooms.RoomNotFoundException;

/**
 * Advice servant a rediriger un utilisateur faisant face a l'exception "RoomNotFoundException" pour le rediriger vers la liste des rooms
 * @author Lucas "Skytowz" HOTTIN
 *
 */
@ControllerAdvice
public class RoomNotFoundAdvice {

    @ExceptionHandler({ RoomNotFoundException.class })
    public String handleRoomNotFoundException(Model model, RoomNotFoundException ex) {
    	model.addAttribute("noroomerror", "Couldn't find room with name " + ex.getRoomName());
    	return "rooms";
    }
}

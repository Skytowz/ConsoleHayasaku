package com.hayasaku.console.model.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.hayasaku.console.model.dto.Room;

/**
 * Repository CRUD pour la gestion en base de rooms
 * @author Quentin "Ruendan" DUBOIS
 *
 */
public interface RoomRepository extends CrudRepository<Room, Long>{
	public List<Room> findAll(Pageable pageable);
	public boolean existsByName(String roomname);
	public Optional<Room> findByName(String roomname);
}

package com.hayasaku.console.model.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hayasaku.console.model.dto.PurpleUser;

/**
 * Repository CRUD pour la gestion en base des utilisateurs
 * @author Quentin "Ruendan" DUBOIS
 *
 */
@Repository
public interface PurpleUserRepository extends CrudRepository<PurpleUser, Long> {
	public boolean existsByEmail(String email);
	public Optional<PurpleUser> findByUsername(String username);
	public Optional<PurpleUser> findByEmail(String email);
	public boolean existsByUsername(String username);
	
	@Query("SELECT f FROM PurpleUser p JOIN p.friends f WHERE p.id = :id")
	public List<PurpleUser> findFriendsById(Long id);
}

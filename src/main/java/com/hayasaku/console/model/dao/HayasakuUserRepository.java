package com.hayasaku.console.model.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hayasaku.console.model.dto.HayasakuUser;

/**
 * Repository CRUD pour la gestion en base des utilisateurs
 * @author Lucas "Skytowz" HOTTIN
 *
 */
@Repository
public interface HayasakuUserRepository extends CrudRepository<HayasakuUser, Long> {
	public boolean existsByEmail(String email);
	public Optional<HayasakuUser> findByUsername(String username);
	public Optional<HayasakuUser> findByEmail(String email);
	public boolean existsByUsername(String username);
	
	@Query("SELECT f FROM HayasakuUser p JOIN p.friends f WHERE p.id = :id")
	public List<HayasakuUser> findFriendsById(Long id);
}

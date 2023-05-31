package com.hayasaku.console.model.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hayasaku.console.model.dto.tags.Tag;

/**
 * Repository CRUD pour la gestion en base des tags
 * @author Lucas "Skytowz" HOTTIN
 *
 */
@Repository
public interface TagRepository extends CrudRepository<Tag, Long>{


}

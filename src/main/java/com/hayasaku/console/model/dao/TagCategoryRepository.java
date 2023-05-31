package com.hayasaku.console.model.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hayasaku.console.model.dto.tags.TagCategory;

/**
 * Repository CRUD pour la gestion en base des categories de tags
 * @author Quentin "Ruendan" DUBOIS
 *
 */
@Repository
public interface TagCategoryRepository extends CrudRepository<TagCategory, Long>{

}

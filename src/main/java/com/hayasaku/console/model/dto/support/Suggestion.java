package com.hayasaku.console.model.dto.support;

import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * Entity Suggestion pour la gestion de Tickets
 * @author Quentin "Ruendan" DUBOIS
 *
 */
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Suggestion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;
	private String content;
	
	@Enumerated(EnumType.STRING)
	private SuggestionCategory category;
	
	@ColumnDefault("FALSE")
	private boolean closed;
	
	@ColumnDefault("TRUE")
	private boolean privateTicket;

	@CreatedDate
	@ColumnDefault("NOW()")
    @Column(nullable = false, updatable = false)
	private LocalDateTime creationDate;

	@LastModifiedDate
    @Column(nullable = false, updatable = false)
	@ColumnDefault("NOW()")
	private LocalDateTime lastUpdateDate;

	@CreatedBy
	private String author;

	@LastModifiedBy
	private String lastModifyBy;
}

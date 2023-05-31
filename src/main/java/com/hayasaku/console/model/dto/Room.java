package com.hayasaku.console.model.dto;

import java.io.Serializable;
import java.util.List;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Entity Room représentant des Salles dans lesquelles on trouve des utilisateurs
 * @author Lucas "Skytowz" HOTTIN
 *
 */
@Data
@Entity
@EqualsAndHashCode(exclude = "Hayasakuusers")
public class Room implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String rules;

	@ColumnDefault("FALSE")
	private boolean official;
	
	@OneToMany(mappedBy = "currentRoom",fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<HayasakuUser> Hayasakuusers;
}

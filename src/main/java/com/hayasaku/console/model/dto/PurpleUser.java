package com.hayasaku.console.model.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.core.userdetails.UserDetails;

import com.hayasaku.console.model.dto.tags.Tag;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Entity PurpleUser représentant les utilisateurs
 * @author Quentin "Ruendan" DUBOIS
 *
 */
@Data
@Entity
@EqualsAndHashCode(exclude = "friends")
public class PurpleUser implements UserDetails, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String username;

	private String password;

	private String email;
	
	private LocalDate dateNaiss;
	
	private String mood;
	
	private String bio;
	
	@ColumnDefault("TRUE")
	private boolean accountNonExpired;
	@ColumnDefault("TRUE")
	private boolean accountNonLocked;
	@ColumnDefault("TRUE")
	private boolean credentialsNonExpired;
	
	@Lob
	private byte[] profilePicture;

	@ToString.Exclude
	@ManyToOne
	private Room currentRoom;
	
	@ColumnDefault("TRUE")
	private boolean enabled;

	@ToString.Exclude
	@ManyToMany
	private List<PurpleUser> friends;
	
	@ToString.Exclude
	@ManyToMany
	private List<Tag> tags;
	
	@ElementCollection(targetClass = PurpleRole.class, fetch = FetchType.EAGER)
	@CollectionTable
	@Enumerated(EnumType.STRING)
	private Collection<PurpleRole> authorities;
}

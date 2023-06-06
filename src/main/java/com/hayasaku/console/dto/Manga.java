package com.hayasaku.console.dto;


import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Data;

@Data
@Entity
@PrimaryKeyJoinColumn(name = "command_id")
public class Manga extends Command {

	private String idManga;
}

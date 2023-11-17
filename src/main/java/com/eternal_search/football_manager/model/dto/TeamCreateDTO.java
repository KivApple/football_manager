package com.eternal_search.football_manager.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamCreateDTO {
	@NotEmpty
	private String name;
	
	@NotEmpty
	private String acronym;
	
	@NotNull
	private Long budget;
	
	private List<PlayerCreateDTO> players;
}

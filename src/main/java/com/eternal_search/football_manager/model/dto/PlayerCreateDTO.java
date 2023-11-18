package com.eternal_search.football_manager.model.dto;

import com.eternal_search.football_manager.model.enumeration.PlayerPosition;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlayerCreateDTO {
	@NotEmpty
	private String name;
	
	@NotNull
	private PlayerPosition position;
}

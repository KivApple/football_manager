package com.eternal_search.football_manager.model.dto;

import com.eternal_search.football_manager.model.enumeration.PlayerPosition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDTO {
	private Long id;
	
	private String name;
	
	private PlayerPosition position;
}

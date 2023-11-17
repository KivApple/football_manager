package com.eternal_search.football_manager.model.dto;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageRequestDTO {
	@Min(0)
	private int page = 0; // Default value
	
	@Min(1)
	private int pageSize = 10; // Default value
}

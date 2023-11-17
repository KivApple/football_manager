package com.eternal_search.football_manager.model.dto;

import com.eternal_search.football_manager.model.enumeration.SortMode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamSortDTO {
	private SortMode nameSort = SortMode.NONE; // Default value
	
	private SortMode acronymSort = SortMode.NONE; // Default value
	
	private SortMode budgetSort = SortMode.NONE; // Default value
}

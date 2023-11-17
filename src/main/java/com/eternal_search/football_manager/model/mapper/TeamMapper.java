package com.eternal_search.football_manager.model.mapper;

import com.eternal_search.football_manager.model.dto.TeamDTO;
import com.eternal_search.football_manager.model.entity.TeamEntity;
import org.mapstruct.Mapper;

/**
 * Convert of team entities to DTOs and vise-versa
 */
@Mapper(componentModel = "spring")
public interface TeamMapper {
	TeamDTO toDTO(TeamEntity entity);
	
	TeamEntity toEntity(TeamDTO dto);
}

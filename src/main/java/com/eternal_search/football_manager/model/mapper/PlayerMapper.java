package com.eternal_search.football_manager.model.mapper;

import com.eternal_search.football_manager.model.dto.PlayerCreateDTO;
import com.eternal_search.football_manager.model.dto.PlayerDTO;
import com.eternal_search.football_manager.model.entity.PlayerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Convert of player entities to DTOs and vise-versa
 */
@Mapper(componentModel = "spring")
public interface PlayerMapper {
	PlayerDTO toDTO(PlayerEntity entity);
	
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "team", ignore = true)
	PlayerEntity toEntity(PlayerCreateDTO dto);
}

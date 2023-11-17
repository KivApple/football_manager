package com.eternal_search.football_manager.service;

import com.eternal_search.football_manager.model.dto.*;
import com.eternal_search.football_manager.model.mapper.TeamMapper;
import com.eternal_search.football_manager.model.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * A service that encapsulates all teams business-logic.
 */
@Service
@RequiredArgsConstructor
public class TeamService {
	private final TeamRepository teamRepository;
	private final TeamMapper teamMapper;
	
	/**
	 * Query teams using sorting and pagination options. Converts entities to DTO
	 * @param sort Sorting options
	 * @param pageRequest Pagination options
	 * @return One page of team DTOs
	 */
	public PageDTO<TeamDTO> findAll(TeamSortDTO sort, PageRequestDTO pageRequest) {
		return teamRepository.findAll(sort, pageRequest)
				.transform(teamMapper::toDTO);
	}
	
	/**
	 * Create a new team
 	 * @param team Team options
	 * @return Created team
	 */
	public TeamDTO create(TeamCreateDTO team) {
		return teamMapper.toDTO(teamRepository.create(team));
	}
	
	/**
	 * Delete a team
	 * @param id Team id
	 */
	public void delete(long id) {
		teamRepository.delete(id);
	}
}

package com.eternal_search.football_manager.model.repository;

import com.eternal_search.football_manager.exception.TeamNotFoundException;
import com.eternal_search.football_manager.model.dto.*;
import com.eternal_search.football_manager.model.entity.PlayerEntity;
import com.eternal_search.football_manager.model.entity.TeamEntity;
import com.eternal_search.football_manager.model.enumeration.SortMode;
import com.eternal_search.football_manager.model.mapper.TeamMapper;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A repository that encapsulates all teams database access.
 */
@Repository
@RequiredArgsConstructor
@Slf4j
public class TeamRepository {
	private final EntityManager entityManager;
	private final TeamMapper teamMapper;
	
	/**
	 * Query team list using optional sorting and pagination
	 * @param sort Sorting options
	 * @param pageRequest Pagination options
	 * @return One page with teams
	 */
	public PageDTO<TeamEntity> findAll(TeamSortDTO sort, PageRequestDTO pageRequest) {
		// Count total team count
		Query countQuery = entityManager.createQuery("SELECT COUNT(t.id) FROM TeamEntity t");
		long totalCount = (long) countQuery.getSingleResult();
		// Make a query
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<TeamEntity> cq = cb.createQuery(TeamEntity.class);
		EntityGraph<?> playerEntityEntityGraph = entityManager.getEntityGraph("team-players");
		Root<TeamEntity> teamRoot = cq.from(TeamEntity.class);
		applySort(cb, cq, teamRoot, sort);
		TypedQuery<TeamEntity> query = entityManager.createQuery(cq);
		applyPagination(query, pageRequest);
		query.setHint("jakarta.persistence.loadgraph", playerEntityEntityGraph); // Avoid N + 1 problem
		// Execute the query
		return PageDTO.<TeamEntity>builder()
				.items(query.getResultList())
				.pageSize(pageRequest.getPageSize())
				.page(pageRequest.getPage())
				.pageCount(((int) totalCount + pageRequest.getPageSize() - 1) / pageRequest.getPageSize())
				.build();
	}
	
	/**
	 * Setup pagination for a query using options from page request
	 * @param query A query to setup
	 * @param pageRequest Options of pagination
	 */
	private void applyPagination(Query query, PageRequestDTO pageRequest) {
		query.setFirstResult(pageRequest.getPage() * pageRequest.getPageSize());
		query.setMaxResults(pageRequest.getPageSize());
	}
	
	/**
	 * Helper function that converts sorting mode to JPA Order
	 * @param cb CriteriaBuilder instance used for this query
	 * @param teamRoot TeamEntity root used for this query
	 * @param fieldName Field name to sort by
	 * @param mode Sort mode
	 * @return JPA Order clause to be used in CriteriaQuery. Can be null in case if no sorting requested
	 */
	private Order makeOrderBy(
			CriteriaBuilder cb,
			Root<TeamEntity> teamRoot,
			String fieldName,
			SortMode mode
	) {
		return switch (mode) {
			case ASC -> cb.asc(teamRoot.get(fieldName));
			case DESC -> cb.desc(teamRoot.get(fieldName));
			case NONE -> null;
		};
	}
	
	/**
	 * Apply all possible sorting options for teams
	 * @param cb CriteriaBuilder instance used for this query
	 * @param cq CriteriaQuery instance
	 * @param teamRoot TeamEntity root used for this query
	 * @param sort Sorting options
	 */
	private void applySort(
			CriteriaBuilder cb,
			CriteriaQuery<TeamEntity> cq,
			Root<TeamEntity> teamRoot,
			TeamSortDTO sort
	) {
		cq.orderBy(
				Stream.of(
						makeOrderBy(cb, teamRoot, "name", sort.getNameSort()),
						makeOrderBy(cb, teamRoot, "acronym", sort.getAcronymSort()),
						makeOrderBy(cb, teamRoot, "budget", sort.getBudgetSort()),
						// Predictable order in case of no sorting requested
						// or if teams have equal values of all sorting criteria (e.g. equal budget)
						makeOrderBy(cb, teamRoot, "id", SortMode.ASC)
				)
						.filter(Objects::nonNull) // Discard NONE sorting criteria
						.collect(Collectors.toList())
		);
	}
	
	/**
	 * Create a team
	 * @param team Team creation options
	 * @return Newly created team
	 */
	@Transactional
	public TeamEntity create(TeamCreateDTO team) {
		TeamEntity entity = teamMapper.toEntity(team);
		entityManager.persist(entity);
		if (entity.getPlayers() != null) {
			for (PlayerEntity player : entity.getPlayers()) {
				player.setTeam(entity);
				entityManager.persist(player);
			}
		}
		log.info("Created a team with id={}", entity.getId());
		return entity;
	}
	
	/**
	 * Delete a team by id
	 * @param id Team id
	 */
	@Transactional
	public void delete(long id) {
		long count = entityManager.createQuery("DELETE FROM TeamEntity WHERE id = :id")
				.setParameter("id", id)
				.executeUpdate();
		if (count < 1) {
			throw new TeamNotFoundException(id);
		}
		log.info("Deleted a team with id={}", id);
		// We don't need to delete players because of cascading
	}
}

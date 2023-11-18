package com.eternal_search.football_manager;

import com.eternal_search.football_manager.model.dto.PlayerCreateDTO;
import com.eternal_search.football_manager.model.dto.TeamCreateDTO;
import com.eternal_search.football_manager.model.dto.TeamDTO;
import com.eternal_search.football_manager.model.enumeration.PlayerPosition;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.Assert;

import java.util.Arrays;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class FootballManagerIntegrationTests {
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	/**
	 * Creates a team with players and then deletes it
	 * @throws Exception An exception
	 */
	@Test
	void testCreateAndDelete() throws Exception {
		// Team create request
		TeamCreateDTO request = TeamCreateDTO.builder()
				.name("Dream team")
				.acronym("DREAM")
				.budget(10L * 1000 * 1000)
				.players(Arrays.asList(
						PlayerCreateDTO.builder()
								.name("Player A")
								.position(PlayerPosition.GOALKEEPER)
								.build(),
						PlayerCreateDTO.builder()
								.name("Player B")
								.position(PlayerPosition.DEFENDER)
								.build()
				))
				.build();
		// Send team create request
		String responseBody = mvc.perform(
				MockMvcRequestBuilders.post("/team")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(request))
		).andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn()
				.getResponse()
				.getContentAsString();
		// Check response
		TeamDTO team = objectMapper.readValue(responseBody, TeamDTO.class);
		Assert.notNull(team.getId(), "Team id should not be null");
		Assert.isTrue(team.getName().equals(request.getName()), "Team name should be equal to the request");
		Assert.isTrue(team.getAcronym().equals(request.getAcronym()), "Team acronym should be equal to the request");
		Assert.isTrue(team.getBudget().equals(request.getBudget()), "Team budget should be equal to the request");
		Assert.isTrue(team.getPlayers().size() == request.getPlayers().size(), "Team player count should be equal to the request");
		// Delete team
		mvc.perform(MockMvcRequestBuilders.delete("/team/{id}", team.getId()))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
}

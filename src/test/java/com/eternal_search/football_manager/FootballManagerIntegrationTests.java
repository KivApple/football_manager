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
import static org.assertj.core.api.Assertions.*;

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
		assertThat(team.getId()).isNotNull();
		assertThat(team.getName()).isEqualTo(request.getName());
		assertThat(team.getAcronym()).isEqualTo(request.getAcronym());
		assertThat(team.getBudget()).isEqualTo(request.getBudget());
		assertThat(team.getPlayers()).hasSameSizeAs(request.getPlayers());
		// Delete team
		mvc.perform(MockMvcRequestBuilders.delete("/team/{id}", team.getId()))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
}

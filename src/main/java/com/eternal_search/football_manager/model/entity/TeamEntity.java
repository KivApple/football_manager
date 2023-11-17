package com.eternal_search.football_manager.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "teams")
@NamedEntityGraph(
		name = "team-players",
		attributeNodes = {
				@NamedAttributeNode("players")
		}
)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;

	private String acronym;
	
	private Long budget;
	
	@OneToMany(mappedBy = "team")
	@ToString.Exclude // Avoid infinite recursion
	@EqualsAndHashCode.Exclude // Avoid infinite recursion
	private List<PlayerEntity> players;
}

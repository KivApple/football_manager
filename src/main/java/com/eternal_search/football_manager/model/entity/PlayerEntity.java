package com.eternal_search.football_manager.model.entity;

import com.eternal_search.football_manager.model.enumeration.PlayerPosition;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "players")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	@Enumerated(EnumType.STRING)
	private PlayerPosition position;
	
	@ManyToOne
	@ToString.Exclude // Avoid infinite recursion
	@EqualsAndHashCode.Exclude // Avoid infinite recursion
	private TeamEntity team;
}

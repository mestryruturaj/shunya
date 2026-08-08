package io.two.bit.saint.shunya.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "season_teams")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeasonTeam extends AbstractUserAuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="season_id", referencedColumnName = "id")
    private Season season;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="team_id", referencedColumnName = "id")
    private Team team;
}

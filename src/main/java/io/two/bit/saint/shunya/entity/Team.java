package io.two.bit.saint.shunya.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "teams")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Team extends AbstractUserAuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String teamName;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="captain_id", referencedColumnName = "id")
    private Player captain;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="vice_captain_id", referencedColumnName = "id")
    private Player viceCaptain;
}

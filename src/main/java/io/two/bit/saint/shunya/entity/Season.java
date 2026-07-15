package io.two.bit.saint.shunya.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "seasons")
public class Season extends AbstractAuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String edition;
    private String description;
    private Integer totalTeams;
    private Integer squadSize;
    private boolean hasIconPlayer;
    private String groundType; //Turf, ground
    private String bowlingType; //Overarm, Half pitch, underarm
    private Integer overLimit;
    private String formatType; //T20, T10, 50 overs
    private String ballType; //Leather, Tennis
    private String ballColor; //Red, White
    private boolean isDayNight;
    private boolean hasPowerPlay;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String status; //Scheduled, Ongoing, Completed
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tournament_id", nullable = false)
    private Tournament tournament;
}



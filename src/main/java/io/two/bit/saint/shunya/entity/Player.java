package io.two.bit.saint.shunya.entity;

import io.two.bit.saint.shunya.enums.player.BattingStyleEnum;
import io.two.bit.saint.shunya.enums.player.BowlingStyleEnum;
import io.two.bit.saint.shunya.enums.player.SkillLevelEnum;
import io.two.bit.saint.shunya.enums.player.StatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

/**
 * Player entity representing a cricket player profile
 * Extends the User entity with cricket-specific attributes
 */
@Entity
@Table(name = "players")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",
            nullable = false,
            unique = true,
            referencedColumnName = "is")
    private User user;

    private String name;

    private boolean canBat;

    private boolean canBowl;

    private boolean canKeepWicket;

    @Enumerated(EnumType.STRING)
    @Column(name = "batting_style", nullable = false)
    private BattingStyleEnum battingStyle;

    @Enumerated(EnumType.STRING)
    @Column(name = "bowling_style", nullable = false)
    private BowlingStyleEnum bowlingStyle;

    @Enumerated(EnumType.STRING)
    @Column(name = "skill_level", nullable = false)
    private SkillLevelEnum skillLevel;

    @Column(name = "profile_image_url")
    private String profileImageUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    @Builder.Default
    private StatusEnum status = StatusEnum.ACTIVE;

    @Column(name = "country")
    private String country;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}

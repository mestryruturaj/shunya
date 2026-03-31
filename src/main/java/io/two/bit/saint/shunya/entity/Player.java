package io.two.bit.saint.shunya.entity;

import io.two.bit.saint.shunya.enums.player.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Player entity representing a cricket player profile
 * Extends the User entity with cricket-specific attributes
 */
@Entity
@Table(name = "player")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private RoleEnum role;

    /**
     * Player's cricket strengths/roles
     * Uses ElementCollection for storing multiple enum values
     */
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "player_strengths", joinColumns = @JoinColumn(name = "player_id"))
    @Column(name = "strength")
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Set<StrengthEnum> strengths = new HashSet<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "batting_style", nullable = false)
    private BattingStyleEnum battingStyle;

    @Enumerated(EnumType.STRING)
    @Column(name = "bowling_style")
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

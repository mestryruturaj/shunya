package io.two.bit.saint.shunya.dao;

import io.two.bit.saint.shunya.entity.SeasonPlayer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeasonPlayerRepository extends JpaRepository<SeasonPlayer, Long> {
    public boolean existsBySeasonIdAndPlayerId(Long seasonId, Long playerId);
}

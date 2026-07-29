package io.two.bit.saint.shunya.dao;

import io.two.bit.saint.shunya.entity.Player;
import io.two.bit.saint.shunya.entity.SeasonPlayer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SeasonPlayerRepository extends JpaRepository<SeasonPlayer, Long> {
    public boolean existsBySeasonIdAndPlayerId(Long seasonId, Long playerId);

    @Query("SELECT sp.player FROM SeasonPlayer sp JOIN sp.player p WHERE sp.season.id = :seasonId")
    public List<Player> findPlayersBySeasonId(@Param("seasonId") Long seasonId);
}

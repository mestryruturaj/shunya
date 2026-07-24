package io.two.bit.saint.shunya.dao;

import io.two.bit.saint.shunya.entity.Player;
import io.two.bit.saint.shunya.entity.TournamentPlayer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TournamentPlayerRepository extends JpaRepository<TournamentPlayer, Long> {
    boolean existsByTournamentIdAndPlayerId(Long tournamentId, Long playerId);

    @Query("SELECT tp.player FROM TournamentPlayer tp JOIN tp.player p WHERE tp.tournament.id = :tournamentId")
    List<Player> findPlayersByTournamentId(@Param("tournamentId") Long tournamentId);
}

package io.two.bit.saint.shunya.dao;

import io.two.bit.saint.shunya.entity.TournamentPlayer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TournamentPlayerRepository extends JpaRepository<TournamentPlayer, Long> {
    boolean existsByTournamentIdAndPlayerId(Long tournamentId, Long playerId);
}

package io.two.bit.saint.shunya.validator;

import io.two.bit.saint.shunya.dao.TournamentPlayerRepository;
import io.two.bit.saint.shunya.dto.TournamentPlayerValidContext;
import io.two.bit.saint.shunya.entity.Player;
import io.two.bit.saint.shunya.entity.Tournament;
import io.two.bit.saint.shunya.exception.InvalidArgumentException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TournamentPlayerValidator {
    private final TournamentValidator tournamentValidator;
    private final PlayerValidator playerValidator;
    private final TournamentPlayerRepository tournamentPlayerRepository;

    public TournamentPlayerValidContext validateTournamentPlayer(Long tournamentId, Long playerId) {
        Tournament validTournament = tournamentValidator.validateAndFetchTournament(tournamentId);
        Player validPlayer = playerValidator.validateAndFetchPlayer(playerId);
        if (tournamentPlayerRepository.existsByTournamentIdAndPlayerId(tournamentId, playerId))
            throw new InvalidArgumentException("Player with ID " + playerId + " is already registered for tournament with ID " + tournamentId);

        return TournamentPlayerValidContext.builder()
                .tournament(validTournament)
                .player(validPlayer)
                .build();
    }
}
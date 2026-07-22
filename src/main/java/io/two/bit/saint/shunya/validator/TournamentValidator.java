package io.two.bit.saint.shunya.validator;

import io.two.bit.saint.shunya.dao.TournamentRepository;
import io.two.bit.saint.shunya.entity.Tournament;
import io.two.bit.saint.shunya.exception.InvalidArgumentException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TournamentValidator {

    private final TournamentRepository tournamentRepository;

    public Tournament validateAndFetchTournament(Long tournamentId) {
        return tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new InvalidArgumentException("Tournament not found with ID: " + tournamentId));
    }

}

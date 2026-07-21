package io.two.bit.saint.shunya.validator;

import io.two.bit.saint.shunya.dao.SeasonRepository;
import io.two.bit.saint.shunya.dao.TournamentRepository;
import io.two.bit.saint.shunya.entity.Tournament;
import io.two.bit.saint.shunya.exception.InvalidArgumentException;
import lombok.RequiredArgsConstructor;
import org.openapitools.model.SeasonBase;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class SeasonManagementValidator {
    private final SeasonRepository seasonRepository;
    private final TournamentRepository tournamentRepository;

    public Tournament validateSeasonRequest(SeasonBase seasonRequest) {
        if (Objects.isNull(seasonRequest.getTournamentId())) {
            throw new InvalidArgumentException("Tournament ID cannot be null or empty");
        }
        return tournamentRepository.findById(seasonRequest.getTournamentId())
                .orElseThrow(() -> new InvalidArgumentException("Tournament with ID " + seasonRequest.getTournamentId() + " does not exist"));
    }

}

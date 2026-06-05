package io.two.bit.saint.shunya.validator;

import io.micrometer.common.util.StringUtils;
import io.two.bit.saint.shunya.dao.SeasonManagementRepository;
import io.two.bit.saint.shunya.dao.TournamentRepository;
import io.two.bit.saint.shunya.entity.Tournament;
import lombok.RequiredArgsConstructor;
import org.openapitools.model.SeasonCreateRequest;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class SeasonManagementValidator {
    private final SeasonManagementRepository seasonManagementRepository;
    private final TournamentRepository tournamentRepository;

    public Tournament validateSeasonCreateRequest(SeasonCreateRequest seasonCreateRequest) {
        if (Objects.isNull(seasonCreateRequest.getTournamentId())) {
            throw new IllegalArgumentException("Tournament ID cannot be null or empty");
        }
        return tournamentRepository.findById(seasonCreateRequest.getTournamentId())
                .orElseThrow(() -> new IllegalArgumentException("Tournament with ID " + seasonCreateRequest.getTournamentId() + " does not exist"));
    }

}

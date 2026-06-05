package io.two.bit.saint.shunya.service;

import io.two.bit.saint.shunya.dao.SeasonManagementRepository;
import io.two.bit.saint.shunya.entity.Season;
import io.two.bit.saint.shunya.entity.Tournament;
import io.two.bit.saint.shunya.mapper.SeasonMapper;
import io.two.bit.saint.shunya.validator.SeasonManagementValidator;
import lombok.RequiredArgsConstructor;
import org.openapitools.model.SeasonCreateRequest;
import org.openapitools.model.SeasonResponse;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SeasonManagementServiceImpl implements SeasonManagementService {
    private final SeasonManagementValidator seasonManagementValidator;
    private final SeasonManagementRepository seasonManagementRepository;
    private final SeasonMapper seasonMapper;

    @Override
    public SeasonResponse createSeason(SeasonCreateRequest seasonCreateRequest) {
        Tournament tournament = seasonManagementValidator.validateSeasonCreateRequest(seasonCreateRequest);
        Season season = seasonMapper.mapToSeasonEntityFromSeasonCreateRequest(seasonCreateRequest);
        season.setTournament(tournament);

        Season savedSeason = seasonManagementRepository.save(season);
        return seasonMapper.mapToSeasonResponseFromSeasonEntity(savedSeason);
    }
}

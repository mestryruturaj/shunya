package io.two.bit.saint.shunya.service;

import io.two.bit.saint.shunya.dao.SeasonManagementRepository;
import io.two.bit.saint.shunya.entity.Season;
import io.two.bit.saint.shunya.entity.Tournament;
import io.two.bit.saint.shunya.exception.InvalidArgumentException;
import io.two.bit.saint.shunya.mapper.SeasonMapper;
import io.two.bit.saint.shunya.validator.SeasonManagementValidator;
import lombok.RequiredArgsConstructor;
import org.openapitools.model.SeasonCreateRequest;
import org.openapitools.model.SeasonResponse;
import org.openapitools.model.SeasonUpdateRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SeasonManagementServiceImpl implements SeasonManagementService {
    private final SeasonManagementValidator seasonManagementValidator;
    private final SeasonManagementRepository seasonManagementRepository;
    private final SeasonMapper seasonMapper;

    @Override
    public SeasonResponse createSeason(SeasonCreateRequest seasonCreateRequest) {
        Tournament tournament = seasonManagementValidator.validateSeasonRequest(seasonCreateRequest);
        Season season = seasonMapper.mapToSeasonEntityFromSeasonBase(seasonCreateRequest);
        season.setTournament(tournament);

        Season savedSeason = seasonManagementRepository.save(season);
        return seasonMapper.mapToSeasonResponseFromSeasonEntity(savedSeason);
    }

    @Override
    public SeasonResponse getSeasonById(Long seasonId) {
        Season season = seasonManagementRepository.findById(seasonId)
                .orElseThrow(() -> new InvalidArgumentException("Season with ID " + seasonId + " does not exist"));
        return seasonMapper.mapToSeasonResponseFromSeasonEntity(season);
    }

    @Override
    public SeasonResponse updateSeasonById(Long seasonId, SeasonUpdateRequest seasonUpdateRequest) {
        Season existingSeason = seasonManagementRepository.findById(seasonId)
                .orElseThrow(() -> new InvalidArgumentException("Season with ID " + seasonId + " does not exist"));

        Tournament tournament = seasonManagementValidator.validateSeasonRequest(seasonUpdateRequest);

        Season updatedSeason = seasonMapper.mapToSeasonEntityFromSeasonBase(seasonUpdateRequest);
        updatedSeason.setTournament(tournament);

        Season savedSeason = seasonManagementRepository.save(updatedSeason);
        return seasonMapper.mapToSeasonResponseFromSeasonEntity(savedSeason);
    }
}

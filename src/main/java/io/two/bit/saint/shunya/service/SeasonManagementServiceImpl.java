package io.two.bit.saint.shunya.service;

import io.two.bit.saint.shunya.dao.SeasonRepository;
import io.two.bit.saint.shunya.entity.Season;
import io.two.bit.saint.shunya.entity.Tournament;
import io.two.bit.saint.shunya.exception.InvalidArgumentException;
import io.two.bit.saint.shunya.mapper.SeasonMapper;
import io.two.bit.saint.shunya.validator.SeasonValidator;
import lombok.RequiredArgsConstructor;
import org.openapitools.model.SeasonCreateRequest;
import org.openapitools.model.SeasonResponse;
import org.openapitools.model.SeasonUpdateRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SeasonManagementServiceImpl implements SeasonManagementService {
    private final SeasonValidator seasonValidator;
    private final SeasonRepository seasonRepository;
    private final SeasonMapper seasonMapper;

    @Override
    public SeasonResponse createSeason(SeasonCreateRequest seasonCreateRequest) {
        Tournament tournament = seasonValidator.validateSeasonRequest(seasonCreateRequest);
        Season season = seasonMapper.mapToSeasonEntityFromSeasonBase(seasonCreateRequest);
        season.setTournament(tournament);

        Season savedSeason = seasonRepository.save(season);
        return seasonMapper.mapToSeasonResponseFromSeasonEntity(savedSeason);
    }

    @Override
    public SeasonResponse getSeasonById(Long seasonId) {
        Season season = seasonRepository.findById(seasonId)
                .orElseThrow(() -> new InvalidArgumentException("Season with ID " + seasonId + " does not exist"));
        return seasonMapper.mapToSeasonResponseFromSeasonEntity(season);
    }

    @Override
    public SeasonResponse updateSeasonById(Long seasonId, SeasonUpdateRequest seasonUpdateRequest) {
        seasonRepository.findById(seasonId)
                .orElseThrow(() -> new InvalidArgumentException("Season with ID " + seasonId + " does not exist"));

        Tournament tournament = seasonValidator.validateSeasonRequest(seasonUpdateRequest);

        Season updatedSeason = seasonMapper.mapToSeasonEntityFromSeasonBase(seasonUpdateRequest);
        updatedSeason.setTournament(tournament);

        Season savedSeason = seasonRepository.save(updatedSeason);
        return seasonMapper.mapToSeasonResponseFromSeasonEntity(savedSeason);
    }

    @Override
    public SeasonResponse deleteSeasonById(Long seasonId) {
        Season existingSeason = seasonRepository.findById(seasonId)
                .orElseThrow(() -> new InvalidArgumentException("Season with ID " + seasonId + " does not exist"));

        seasonRepository.deleteById(seasonId);

        return seasonMapper.mapToSeasonResponseFromSeasonEntity(existingSeason);
    }

    @Override
    public Season fetchById(Long seasonId) {
        seasonValidator.validateIdField(seasonId, Season.class.getSimpleName());

        return seasonRepository.findById(seasonId)
                .orElseThrow(() -> new InvalidArgumentException("Season not found with id: " + seasonId));
    }
}

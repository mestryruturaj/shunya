package io.two.bit.saint.shunya.service;

import io.two.bit.saint.shunya.dao.SeasonTeamRepository;
import io.two.bit.saint.shunya.dto.SeasonTeamValidContext;
import io.two.bit.saint.shunya.entity.SeasonTeam;
import io.two.bit.saint.shunya.exception.InvalidArgumentException;
import io.two.bit.saint.shunya.mapper.SeasonTeamMapper;
import io.two.bit.saint.shunya.validator.SeasonTeamValidator;
import lombok.RequiredArgsConstructor;
import org.openapitools.model.SeasonTeamCreateRequest;
import org.openapitools.model.SeasonTeamResponse;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SeasonTeamManagementServiceImpl implements SeasonTeamManagementService {
    private final SeasonTeamValidator seasonTeamValidator;
    private final SeasonTeamRepository seasonTeamRepository;
    private final SeasonTeamMapper seasonTeamMapper;

    @Override
    public SeasonTeamResponse createSeasonTeam(SeasonTeamCreateRequest seasonTeamCreateRequest) {
        SeasonTeamValidContext seasonTeamValidContext = seasonTeamValidator.validateSeasonTeamCreateRequest(seasonTeamCreateRequest);
        SeasonTeam mappedSeasonTeam = seasonTeamMapper.mapToSeasonTeam(seasonTeamValidContext);
        SeasonTeam savedSeasonTeam = seasonTeamRepository.save(mappedSeasonTeam);
        return seasonTeamMapper.mapToSeasonTeamResponse(savedSeasonTeam);
    }

    @Override
    public SeasonTeamResponse getSeasonTeamById(Long id) {
        seasonTeamValidator.validateIdField(id, SeasonTeam.class.getSimpleName());
        SeasonTeam seasonTeam = fetchById(id);
        return seasonTeamMapper.mapToSeasonTeamResponse(seasonTeam);
    }

    public SeasonTeam fetchById(Long id) {
        return seasonTeamRepository.findById(id)
                .orElseThrow(() -> new InvalidArgumentException("SeasonTeam with id " + id + " not found"));
    }
}

package io.two.bit.saint.shunya.service;

import io.two.bit.saint.shunya.dao.SeasonTeamRepository;
import io.two.bit.saint.shunya.dto.SeasonTeamValidContext;
import io.two.bit.saint.shunya.entity.Season;
import io.two.bit.saint.shunya.entity.SeasonTeam;
import io.two.bit.saint.shunya.entity.Team;
import io.two.bit.saint.shunya.exception.InvalidArgumentException;
import io.two.bit.saint.shunya.mapper.SeasonMapper;
import io.two.bit.saint.shunya.mapper.SeasonTeamMapper;
import io.two.bit.saint.shunya.mapper.TeamMapper;
import io.two.bit.saint.shunya.validator.SeasonTeamValidator;
import lombok.RequiredArgsConstructor;
import org.openapitools.model.SeasonTeamCreateRequest;
import org.openapitools.model.SeasonTeamResponse;
import org.openapitools.model.SeasonTeamsResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeasonTeamManagementServiceImpl implements SeasonTeamManagementService {
    private final SeasonTeamValidator seasonTeamValidator;
    private final SeasonTeamRepository seasonTeamRepository;
    private final SeasonTeamMapper seasonTeamMapper;
    private final SeasonManagementService seasonManagementService;
    private final TeamMapper teamMapper;
    private final SeasonMapper seasonMapper;

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

    @Override
    public SeasonTeamsResponse getSeasonTeamsBySeasonId(Long seasonId) {
        seasonTeamValidator.validateIdField(seasonId, Season.class.getSimpleName());
        Season validatedSeason = seasonManagementService.fetchById(seasonId);// Ensure the season exists
        List<Team> allTeamsBySeasonId = seasonTeamRepository.findAllTeamsBySeasonId(seasonId);
        return buildSeasonTeamsResponse(validatedSeason, allTeamsBySeasonId);
    }

    private SeasonTeamsResponse buildSeasonTeamsResponse(Season season, List<Team> teams) {
        SeasonTeamsResponse seasonTeamsResponse = new SeasonTeamsResponse();
        seasonTeamsResponse.setSeason(seasonMapper.mapToSeasonSummary(season));
        seasonTeamsResponse.setTeams(teams.stream()
                .map(teamMapper::mapToTeamSummary)
                .toList());

        return seasonTeamsResponse;
    }

    @Override
    public SeasonTeamResponse deleteSeasonTeamById(Long seasonTeamId) {
        seasonTeamValidator.validateIdField(seasonTeamId, SeasonTeam.class.getSimpleName());
        SeasonTeam seasonTeam = fetchById(seasonTeamId);
        seasonTeamRepository.delete(seasonTeam);
        return seasonTeamMapper.mapToSeasonTeamResponse(seasonTeam);
    }
}

package io.two.bit.saint.shunya.service;

import io.two.bit.saint.shunya.entity.SeasonTeam;
import org.openapitools.model.SeasonTeamCreateRequest;
import org.openapitools.model.SeasonTeamResponse;
import org.openapitools.model.SeasonTeamsResponse;
import org.springframework.http.ResponseEntity;

public interface SeasonTeamManagementService {
    SeasonTeamResponse createSeasonTeam(SeasonTeamCreateRequest seasonTeamCreateRequest);

    SeasonTeamResponse getSeasonTeamById(Long id);

    SeasonTeamsResponse getSeasonTeamsBySeasonId(Long seasonId);

    SeasonTeam fetchById(Long id);

    SeasonTeamResponse deleteSeasonTeamById(Long seasonTeamId);
}

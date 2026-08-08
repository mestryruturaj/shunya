package io.two.bit.saint.shunya.service;

import io.two.bit.saint.shunya.entity.SeasonTeam;
import org.openapitools.model.SeasonTeamCreateRequest;
import org.openapitools.model.SeasonTeamResponse;

public interface SeasonTeamManagementService {
    SeasonTeamResponse createSeasonTeam(SeasonTeamCreateRequest seasonTeamCreateRequest);

    SeasonTeamResponse getSeasonTeamById(Long id);

    SeasonTeam fetchById(Long id);
}

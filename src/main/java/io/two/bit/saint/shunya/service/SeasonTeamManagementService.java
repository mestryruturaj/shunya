package io.two.bit.saint.shunya.service;

import org.openapitools.model.SeasonTeamCreateRequest;
import org.openapitools.model.SeasonTeamResponse;

public interface SeasonTeamManagementService {
    SeasonTeamResponse createSeasonTeam(SeasonTeamCreateRequest seasonTeamCreateRequest);
}

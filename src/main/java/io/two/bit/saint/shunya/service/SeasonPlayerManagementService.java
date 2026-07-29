package io.two.bit.saint.shunya.service;

import org.openapitools.model.SeasonPlayerCreateRequest;
import org.openapitools.model.SeasonPlayerResponse;
import org.openapitools.model.SeasonPlayersResponse;

public interface SeasonPlayerManagementService {
    SeasonPlayerResponse createSeasonPlayer(SeasonPlayerCreateRequest seasonPlayerCreateRequest);

    SeasonPlayerResponse getSeasonPlayerById(Long seasonPlayerId);

    SeasonPlayersResponse getSeasonPlayersBySeasonId(Long seasonId);
}

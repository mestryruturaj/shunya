package io.two.bit.saint.shunya.service;

import org.openapitools.model.TournamentPlayerCreateRequest;
import org.openapitools.model.TournamentPlayerResponse;

public interface TournamentPlayerManagementService {
    TournamentPlayerResponse createTournamentPlayer(TournamentPlayerCreateRequest tournamentPlayerCreateRequest);
}

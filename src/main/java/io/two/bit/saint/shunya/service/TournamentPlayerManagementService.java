package io.two.bit.saint.shunya.service;

import org.openapitools.model.TournamentPlayerCreateRequest;
import org.openapitools.model.TournamentPlayerResponse;
import org.openapitools.model.TournamentPlayersResponse;

public interface TournamentPlayerManagementService {
    TournamentPlayerResponse createTournamentPlayer(TournamentPlayerCreateRequest tournamentPlayerCreateRequest);

    TournamentPlayerResponse getTournamentPlayerById(Long tournamentPlayerId);

    TournamentPlayersResponse getTournamentPlayersByTournamentId(Long tournamentId);
}

package io.two.bit.saint.shunya.service;

import org.openapitools.model.TournamentPlayerCreateRequest;
import org.openapitools.model.TournamentPlayerResponse;

import java.util.List;

public interface TournamentPlayerManagementService {
    TournamentPlayerResponse createTournamentPlayer(TournamentPlayerCreateRequest tournamentPlayerCreateRequest);

    TournamentPlayerResponse getTournamentPlayersByTournamentId(Long tournamentPlayerId);
}

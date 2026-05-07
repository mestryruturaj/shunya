package io.two.bit.saint.shunya.service;

import org.openapitools.model.TournamentCreateRequest;
import org.openapitools.model.TournamentResponse;
import org.openapitools.model.TournamentUpdateRequest;

public interface TournamentService {
    TournamentResponse createTournament(TournamentCreateRequest tournamentCreateRequest);

    TournamentResponse getTournamentById(Long tournamentId);

    TournamentResponse updateTournament(Long tournamentId, TournamentUpdateRequest tournamentUpdateRequest);
}

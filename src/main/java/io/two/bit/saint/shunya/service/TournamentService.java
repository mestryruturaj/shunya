package io.two.bit.saint.shunya.service;

import org.openapitools.model.TournamentCreateRequest;
import org.openapitools.model.TournamentResponse;

public interface TournamentService {
    TournamentResponse createTournament(TournamentCreateRequest tournamentCreateRequest);
}

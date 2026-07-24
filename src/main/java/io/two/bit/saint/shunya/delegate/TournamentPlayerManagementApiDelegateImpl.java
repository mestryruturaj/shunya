package io.two.bit.saint.shunya.delegate;

import io.two.bit.saint.shunya.service.TournamentPlayerManagementService;
import lombok.RequiredArgsConstructor;
import org.openapitools.api.TournamentPlayerManagementApiDelegate;
import org.openapitools.model.TournamentPlayerCreateRequest;
import org.openapitools.model.TournamentPlayerResponse;
import org.openapitools.model.TournamentPlayersResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TournamentPlayerManagementApiDelegateImpl implements TournamentPlayerManagementApiDelegate {
    private final TournamentPlayerManagementService tournamentPlayerManagementService;

    @Override
    public ResponseEntity<TournamentPlayerResponse> createTournamentPlayer(TournamentPlayerCreateRequest tournamentPlayerCreateRequest) {
        return new ResponseEntity<>(
                tournamentPlayerManagementService.createTournamentPlayer(tournamentPlayerCreateRequest),
                HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<TournamentPlayerResponse> getTournamentPlayerById(Long tournamentPlayerId) {
        return new ResponseEntity<>(
                tournamentPlayerManagementService.getTournamentPlayerById(tournamentPlayerId),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TournamentPlayersResponse> getTournamentPlayersByTournamentId(Long tournamentId) {
        return new ResponseEntity<>(
                tournamentPlayerManagementService.getTournamentPlayersByTournamentId(tournamentId),
                HttpStatus.OK);
    }
}

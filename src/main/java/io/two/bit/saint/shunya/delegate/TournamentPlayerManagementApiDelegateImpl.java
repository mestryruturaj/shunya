package io.two.bit.saint.shunya.delegate;

import io.two.bit.saint.shunya.service.TournamentPlayerManagementService;
import lombok.RequiredArgsConstructor;
import org.openapitools.api.TournamentPlayerManagementApiDelegate;
import org.openapitools.model.TournamentPlayerCreateRequest;
import org.openapitools.model.TournamentPlayerResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

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
                tournamentPlayerManagementService.getTournamentPlayersByTournamentId(tournamentPlayerId),
                HttpStatus.OK);
    }
}

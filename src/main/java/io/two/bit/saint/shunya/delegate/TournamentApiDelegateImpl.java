package io.two.bit.saint.shunya.delegate;

import io.two.bit.saint.shunya.service.TournamentService;
import lombok.RequiredArgsConstructor;
import org.openapitools.api.TournamentApiDelegate;
import org.openapitools.model.TournamentCreateRequest;
import org.openapitools.model.TournamentResponse;
import org.openapitools.model.TournamentUpdateRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TournamentApiDelegateImpl implements TournamentApiDelegate {
    private final TournamentService tournamentService;

    @Override
    public ResponseEntity<TournamentResponse> createTournament(TournamentCreateRequest tournamentCreateRequest) {
        return new ResponseEntity<>(tournamentService.createTournament(tournamentCreateRequest), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<TournamentResponse> getTournamentById(Long tournamentId) {
        return ResponseEntity.ok(tournamentService.getTournamentById(tournamentId));
    }

    @Override
    public ResponseEntity<TournamentResponse> updateTournamentById(Long tournamentId, TournamentUpdateRequest tournamentUpdateRequest) {
        return ResponseEntity.ok(tournamentService.updateTournament(tournamentId, tournamentUpdateRequest));
    }
}

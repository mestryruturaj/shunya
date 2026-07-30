package io.two.bit.saint.shunya.delegate;

import io.two.bit.saint.shunya.service.SeasonPlayerManagementService;
import lombok.RequiredArgsConstructor;
import org.openapitools.api.SeasonPlayerManagementApiDelegate;
import org.openapitools.model.SeasonPlayerCreateRequest;
import org.openapitools.model.SeasonPlayerResponse;
import org.openapitools.model.SeasonPlayersResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SeasonPlayerManagementApiDelegateImpl implements SeasonPlayerManagementApiDelegate {
    private final SeasonPlayerManagementService seasonPlayerManagementService;

    @Override
    public ResponseEntity<SeasonPlayerResponse> createSeasonPlayer(SeasonPlayerCreateRequest seasonPlayerCreateRequest) {
        return new ResponseEntity<>(seasonPlayerManagementService.createSeasonPlayer(seasonPlayerCreateRequest), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<SeasonPlayerResponse> getSeasonPlayerById(Long seasonPlayerId) {
        return new ResponseEntity<>(seasonPlayerManagementService.getSeasonPlayerById(seasonPlayerId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SeasonPlayersResponse> getSeasonPlayersBySeasonId(Long seasonId) {
        return new ResponseEntity<>(seasonPlayerManagementService.getSeasonPlayersBySeasonId(seasonId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SeasonPlayerResponse> deleteSeasonPlayerById(Long seasonPlayerId) {
        return new ResponseEntity<>(seasonPlayerManagementService.deleteSeasonPlayerById(seasonPlayerId), HttpStatus.OK);
    }
}

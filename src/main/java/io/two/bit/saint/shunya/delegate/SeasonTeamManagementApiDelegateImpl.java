package io.two.bit.saint.shunya.delegate;

import io.two.bit.saint.shunya.service.SeasonTeamManagementService;
import lombok.RequiredArgsConstructor;
import org.openapitools.api.SeasonTeamManagementApiDelegate;
import org.openapitools.model.SeasonTeamCreateRequest;
import org.openapitools.model.SeasonTeamResponse;
import org.openapitools.model.SeasonTeamsResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SeasonTeamManagementApiDelegateImpl implements SeasonTeamManagementApiDelegate {
    private final SeasonTeamManagementService seasonTeamManagementService;

    @Override
    public ResponseEntity<SeasonTeamResponse> createSeasonTeam(SeasonTeamCreateRequest seasonTeamCreateRequest) {
        return new ResponseEntity<>(seasonTeamManagementService.createSeasonTeam(seasonTeamCreateRequest), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<SeasonTeamResponse> getSeasonTeamById(Long seasonTeamId) {
        return new ResponseEntity<>(seasonTeamManagementService.getSeasonTeamById(seasonTeamId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SeasonTeamsResponse> getSeasonTeamsBySeasonId(Long seasonTeamId) {
        return new ResponseEntity<>(seasonTeamManagementService.getSeasonTeamsBySeasonId(seasonTeamId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SeasonTeamResponse> deleteSeasonTeamById(Long seasonTeamId) {
        return new ResponseEntity<>(seasonTeamManagementService.deleteSeasonTeamById(seasonTeamId), HttpStatus.OK);
    }
}

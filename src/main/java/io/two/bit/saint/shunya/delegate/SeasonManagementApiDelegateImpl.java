package io.two.bit.saint.shunya.delegate;

import io.two.bit.saint.shunya.service.SeasonManagementService;
import lombok.RequiredArgsConstructor;
import org.openapitools.api.SeasonManagementApiDelegate;
import org.openapitools.model.SeasonCreateRequest;
import org.openapitools.model.SeasonResponse;
import org.openapitools.model.SeasonUpdateRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SeasonManagementApiDelegateImpl implements SeasonManagementApiDelegate {
    private final SeasonManagementService seasonManagementService;

    @Override
    public ResponseEntity<SeasonResponse> createSeason(SeasonCreateRequest seasonCreateRequest) {
        return new ResponseEntity<>(seasonManagementService.createSeason(seasonCreateRequest), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<SeasonResponse> getSeasonById(Long id) {
        return new ResponseEntity<>(seasonManagementService.getSeasonById(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SeasonResponse> updateSeasonById(Long seasonId,
                                                           SeasonUpdateRequest seasonUpdateRequest) {
        return new ResponseEntity<>(seasonManagementService.updateSeasonById(seasonId, seasonUpdateRequest), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SeasonResponse> deleteSeasonById(Long seasonId) {
        return new ResponseEntity<>(seasonManagementService.deleteSeasonById(seasonId), HttpStatus.OK);
    }
}

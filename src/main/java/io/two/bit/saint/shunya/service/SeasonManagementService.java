package io.two.bit.saint.shunya.service;

import org.openapitools.model.SeasonCreateRequest;
import org.openapitools.model.SeasonResponse;
import org.openapitools.model.SeasonUpdateRequest;
import org.springframework.http.ResponseEntity;

public interface SeasonManagementService {
    public SeasonResponse createSeason(SeasonCreateRequest seasonCreateRequest);

    public SeasonResponse getSeasonById(Long seasonId);

    public SeasonResponse updateSeasonById(Long seasonId, SeasonUpdateRequest seasonUpdateRequest);

    public SeasonResponse deleteSeasonById(Long seasonId);
}

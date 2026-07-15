package io.two.bit.saint.shunya.service;

import org.openapitools.model.SeasonCreateRequest;
import org.openapitools.model.SeasonResponse;
import org.springframework.http.ResponseEntity;

public interface SeasonManagementService {
    public SeasonResponse createSeason(SeasonCreateRequest seasonCreateRequest);
}

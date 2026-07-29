package io.two.bit.saint.shunya.service;

import org.openapitools.model.SeasonPlayerCreateRequest;
import org.openapitools.model.SeasonPlayerResponse;

public interface SeasonPlayerManagementService {
    SeasonPlayerResponse createSeasonPlayer(SeasonPlayerCreateRequest seasonPlayerCreateRequest);
}

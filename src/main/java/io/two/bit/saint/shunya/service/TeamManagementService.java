package io.two.bit.saint.shunya.service;

import org.openapitools.model.TeamCreateRequest;
import org.openapitools.model.TeamResponse;

public interface TeamManagementService {

    TeamResponse createTeam(TeamCreateRequest teamCreateRequest);
}

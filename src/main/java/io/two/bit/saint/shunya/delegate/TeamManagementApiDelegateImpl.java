package io.two.bit.saint.shunya.delegate;

import io.two.bit.saint.shunya.service.TeamManagementService;
import lombok.RequiredArgsConstructor;
import org.openapitools.api.TeamManagementApiDelegate;
import org.openapitools.model.TeamCreateRequest;
import org.openapitools.model.TeamResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TeamManagementApiDelegateImpl implements TeamManagementApiDelegate {

    private final TeamManagementService teamManagementService;

    @Override
    public ResponseEntity<TeamResponse> createTeam(TeamCreateRequest teamCreateRequest) {
        return new ResponseEntity<>(teamManagementService.createTeam(teamCreateRequest), HttpStatus.CREATED);
    }
}

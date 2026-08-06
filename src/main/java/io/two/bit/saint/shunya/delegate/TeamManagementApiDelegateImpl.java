package io.two.bit.saint.shunya.delegate;

import io.two.bit.saint.shunya.service.TeamManagementService;
import lombok.RequiredArgsConstructor;
import org.openapitools.api.TeamManagementApiDelegate;
import org.openapitools.model.TeamCreateRequest;
import org.openapitools.model.TeamResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TeamManagementApiDelegateImpl implements TeamManagementApiDelegate {

    private final TeamManagementService teamManagementService;

    @Override
    public ResponseEntity<TeamResponse> createTeam(TeamCreateRequest teamCreateRequest) {
        return new ResponseEntity<>(teamManagementService.createTeam(teamCreateRequest), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<TeamResponse> getTeamById(Long teamId) {
        return new ResponseEntity<>(teamManagementService.getTeamById(teamId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<TeamResponse>> getAllTeams() {
        return new ResponseEntity<>(teamManagementService.getAllTeams(), HttpStatus.OK);
    }
}

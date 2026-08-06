package io.two.bit.saint.shunya.service;

import org.openapitools.model.TeamCreateRequest;
import org.openapitools.model.TeamResponse;
import org.openapitools.model.TeamUpdateRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TeamManagementService {

    TeamResponse createTeam(TeamCreateRequest teamCreateRequest);

    TeamResponse getTeamById(Long teamId);

    List<TeamResponse> getAllTeams();

    TeamResponse updateTeamById(Long teamId, TeamUpdateRequest teamUpdateRequest);
}

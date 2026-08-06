package io.two.bit.saint.shunya.service;

import org.openapitools.model.TeamCreateRequest;
import org.openapitools.model.TeamResponse;

import java.util.List;

public interface TeamManagementService {

    TeamResponse createTeam(TeamCreateRequest teamCreateRequest);

    TeamResponse getTeamById(Long teamId);

    List<TeamResponse> getAllTeams();
}

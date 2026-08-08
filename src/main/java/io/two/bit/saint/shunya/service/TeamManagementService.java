package io.two.bit.saint.shunya.service;

import io.two.bit.saint.shunya.entity.Team;
import org.openapitools.model.TeamCreateRequest;
import org.openapitools.model.TeamResponse;
import org.openapitools.model.TeamUpdateRequest;

import java.util.List;

public interface TeamManagementService {

    TeamResponse createTeam(TeamCreateRequest teamCreateRequest);

    TeamResponse getTeamById(Long teamId);

    List<TeamResponse> getAllTeams();

    TeamResponse updateTeamById(Long teamId, TeamUpdateRequest teamUpdateRequest);

    TeamResponse deleteTeamById(Long teamId);

    Team fetchById(Long teamId);
}

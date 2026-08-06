package io.two.bit.saint.shunya.service;

import io.two.bit.saint.shunya.dao.TeamRepository;
import io.two.bit.saint.shunya.dto.TeamValidContext;
import io.two.bit.saint.shunya.entity.Team;
import io.two.bit.saint.shunya.exception.InvalidArgumentException;
import io.two.bit.saint.shunya.mapper.PlayerMapper;
import io.two.bit.saint.shunya.validator.TeamValidator;
import lombok.RequiredArgsConstructor;
import org.openapitools.model.TeamCreateRequest;
import org.openapitools.model.TeamResponse;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeamManagementServiceImpl implements TeamManagementService {

    private final TeamValidator teamValidator;
    private final TeamRepository teamRepository;
    private final PlayerMapper playerMapper;

    @Override
    public TeamResponse createTeam(TeamCreateRequest teamCreateRequest) {
        TeamValidContext teamValidContext = teamValidator.validatePlayerRequest(teamCreateRequest);
        Team unsavedTeam = buildTeamEntity(teamValidContext);

        Team savedTeam = teamRepository.save(unsavedTeam);
        return buildTeamResponse(savedTeam);
    }

    private Team buildTeamEntity(TeamValidContext teamValidContext) {
        return Team.builder()
                .teamName(teamValidContext.getTeamName())
                .captain(teamValidContext.getCaptain())
                .viceCaptain(teamValidContext.getViceCaptain())
                .build();
    }

    private TeamResponse buildTeamResponse(Team team) {
        TeamResponse teamResponse = new TeamResponse();
        teamResponse.setId(team.getId());
        teamResponse.setTeamName(team.getTeamName());
        teamResponse.setCaptain(playerMapper.mapToPlayerInfoFromPlayerEntity(team.getCaptain()));
        teamResponse.setViceCaptain(playerMapper.mapToPlayerInfoFromPlayerEntity(team.getViceCaptain()));
        return teamResponse;
    }

    @Override
    public TeamResponse getTeamById(Long teamId) {
        teamValidator.validateIdField(teamId, Team.class.getSimpleName());
        Team team = fetchTeamById(teamId);
        return buildTeamResponse(team);
    }

    public Team fetchTeamById(Long teamId) {
        return teamRepository.findById(teamId)
                .orElseThrow(() -> new InvalidArgumentException("Team not found with id: " + teamId));
    }
}

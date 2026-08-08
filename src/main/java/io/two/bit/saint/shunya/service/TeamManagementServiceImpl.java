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
import org.openapitools.model.TeamUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamManagementServiceImpl implements TeamManagementService {

    private final TeamValidator teamValidator;
    private final TeamRepository teamRepository;
    private final PlayerMapper playerMapper;
    private final PlayerService playerService;

    @Override
    public TeamResponse createTeam(TeamCreateRequest teamCreateRequest) {
        TeamValidContext teamValidContext = teamValidator.validateTeamRequest(teamCreateRequest);
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
        Team team = fetchById(teamId);
        return buildTeamResponse(team);
    }

    public Team fetchById(Long teamId) {
        return teamRepository.findById(teamId)
                .orElseThrow(() -> new InvalidArgumentException("Team not found with id: " + teamId));
    }

    @Override
    public List<TeamResponse> getAllTeams() {
        List<Team> teams = teamRepository.findAll();
        return teams.stream()
                .map(this::buildTeamResponse)
                .toList();
    }

    @Override
    public TeamResponse updateTeamById(Long teamId, TeamUpdateRequest teamUpdateRequest) {
        teamValidator.validateTeamRequest(teamId, teamUpdateRequest);
        Team existingTeam = fetchById(teamId);
        if (!whetherTeamDistinct(existingTeam, teamUpdateRequest)) {
            return buildTeamResponse(existingTeam);
        }

        Team updatedTeam = buildTeamEntity(existingTeam, teamUpdateRequest);
        Team savedTeam = teamRepository.save(updatedTeam);
        return buildTeamResponse(savedTeam);
    }

    public Team buildTeamEntity(Team existingTeam, TeamUpdateRequest teamUpdateRequest) {
        existingTeam.setTeamName(teamUpdateRequest.getTeamName());
        existingTeam.setCaptain(playerService.fetchPlayerById(teamUpdateRequest.getCaptain()));
        existingTeam.setViceCaptain(playerService.fetchPlayerById(teamUpdateRequest.getViceCaptain()));
        return existingTeam;
    }

    private boolean whetherTeamDistinct(Team savedTeam, TeamUpdateRequest teamUpdateRequest) {
        return !savedTeam.getTeamName().equals(teamUpdateRequest.getTeamName()) ||
                !savedTeam.getCaptain().getId().equals(teamUpdateRequest.getCaptain()) ||
                !savedTeam.getViceCaptain().getId().equals(teamUpdateRequest.getViceCaptain());
    }

    @Override
    public TeamResponse deleteTeamById(Long teamId) {
        teamValidator.validateIdField(teamId, Team.class.getSimpleName());
        Team existingTeam = fetchById(teamId);
        teamRepository.delete(existingTeam);
        return buildTeamResponse(existingTeam);
    }
}

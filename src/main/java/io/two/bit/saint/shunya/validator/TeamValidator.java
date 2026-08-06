package io.two.bit.saint.shunya.validator;

import io.two.bit.saint.shunya.dao.PlayerRepository;
import io.two.bit.saint.shunya.dao.TeamRepository;
import io.two.bit.saint.shunya.dto.TeamValidContext;
import io.two.bit.saint.shunya.entity.Player;
import io.two.bit.saint.shunya.entity.Team;
import io.two.bit.saint.shunya.exception.InvalidArgumentException;
import io.two.bit.saint.shunya.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.openapitools.model.TeamCreateRequest;
import org.openapitools.model.TeamUpdateRequest;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class TeamValidator extends BaseValidator {
    private final PlayerService playerService;
    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;

    public TeamValidContext validateTeamRequest(TeamCreateRequest teamCreateRequest) {
        validateIdField(teamCreateRequest.getCaptain(), Player.class.getSimpleName());
        validateIdField(teamCreateRequest.getViceCaptain(), Player.class.getSimpleName());
        if (Objects.equals(teamCreateRequest.getCaptain(), teamCreateRequest.getViceCaptain())) {
            throw new InvalidArgumentException("Captain and Vice-Captain cannot be the same player.");
        }

        Player captain = playerService.fetchPlayerById(teamCreateRequest.getCaptain());
        Player viceCaptain = playerService.fetchPlayerById(teamCreateRequest.getViceCaptain());

        return TeamValidContext.builder()
                .teamName(teamCreateRequest.getTeamName())
                .captain(captain)
                .viceCaptain(viceCaptain)
                .build();
    }

    public void validateTeamRequest(Long teamId, TeamUpdateRequest teamUpdateRequest) {
        validateIdField(teamId, Team.class.getSimpleName());
        validateIdField(teamUpdateRequest.getCaptain(), Player.class.getSimpleName());
        validateIdField(teamUpdateRequest.getViceCaptain(), Player.class.getSimpleName());
        if (Objects.equals(teamUpdateRequest.getCaptain(), teamUpdateRequest.getViceCaptain())) {
            throw new InvalidArgumentException("Captain and Vice-Captain cannot be the same player.");
        }

        if (!teamRepository.existsById(teamId)) {
            throw new InvalidArgumentException("Team not found with id: " + teamId);
        }
        if (!playerRepository.existsById(teamUpdateRequest.getCaptain())) {
            throw new InvalidArgumentException("Captain not found with id: " + teamUpdateRequest.getCaptain());
        }
        if (!playerRepository.existsById(teamUpdateRequest.getViceCaptain())) {
            throw new InvalidArgumentException("Vice-Captain not found with id: " + teamUpdateRequest.getViceCaptain());
        }
    }
}

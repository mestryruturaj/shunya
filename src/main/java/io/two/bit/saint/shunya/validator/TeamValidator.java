package io.two.bit.saint.shunya.validator;

import io.two.bit.saint.shunya.dto.TeamValidContext;
import io.two.bit.saint.shunya.entity.Player;
import io.two.bit.saint.shunya.exception.InvalidArgumentException;
import io.two.bit.saint.shunya.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.openapitools.model.PlayerCreateRequest;
import org.openapitools.model.TeamCreateRequest;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TeamValidator extends BaseValidator {
    private final PlayerService playerService;

    public TeamValidContext validatePlayerRequest(TeamCreateRequest teamCreateRequest) {
        validateIdField(teamCreateRequest.getCaptain(), Player.class.getSimpleName());
        validateIdField(teamCreateRequest.getViceCaptain(), Player.class.getSimpleName());
        if (teamCreateRequest.getCaptain() == teamCreateRequest.getViceCaptain()) {
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
}

package io.two.bit.saint.shunya.validator;

import io.two.bit.saint.shunya.dto.SeasonTeamValidContext;
import io.two.bit.saint.shunya.entity.Season;
import io.two.bit.saint.shunya.entity.Team;
import io.two.bit.saint.shunya.service.SeasonManagementService;
import io.two.bit.saint.shunya.service.TeamManagementService;
import lombok.RequiredArgsConstructor;
import org.openapitools.model.SeasonTeamCreateRequest;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SeasonTeamValidator extends BaseValidator {
    private final SeasonManagementService seasonManagementService;
    private final TeamManagementService teamManagementService;

    public SeasonTeamValidContext validateSeasonTeamCreateRequest(SeasonTeamCreateRequest seasonTeamCreateRequest) {
        validateSeasonTeamIdFields(seasonTeamCreateRequest.getSeasonId(), seasonTeamCreateRequest.getTeamId());
        Season validatedSeason = seasonManagementService.fetchById(seasonTeamCreateRequest.getSeasonId());
        Team validatedTeam = teamManagementService.fetchById(seasonTeamCreateRequest.getTeamId());
        return SeasonTeamValidContext.builder()
                .season(validatedSeason)
                .team(validatedTeam)
                .build();
    }

    private void validateSeasonTeamIdFields(Long seasonId, Long teamId) {
        validateIdField(seasonId, Season.class.getSimpleName());
        validateIdField(teamId, Team.class.getSimpleName());
    }
}


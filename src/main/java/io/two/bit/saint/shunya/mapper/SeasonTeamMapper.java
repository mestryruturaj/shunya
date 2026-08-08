package io.two.bit.saint.shunya.mapper;

import io.two.bit.saint.shunya.dto.SeasonTeamValidContext;
import io.two.bit.saint.shunya.entity.SeasonTeam;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.openapitools.model.SeasonTeamResponse;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {SeasonMapper.class, TeamMapper.class}
)
public interface SeasonTeamMapper {
    SeasonTeam mapToSeasonTeam(SeasonTeamValidContext seasonTeamValidContext);

    SeasonTeamResponse mapToSeasonTeamResponse(SeasonTeam seasonTeam);
}

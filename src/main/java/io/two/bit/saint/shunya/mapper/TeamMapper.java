package io.two.bit.saint.shunya.mapper;

import io.two.bit.saint.shunya.entity.Team;
import org.mapstruct.Mapper;
import org.openapitools.model.TeamSummary;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE,
        unmappedSourcePolicy = org.mapstruct.ReportingPolicy.IGNORE,
        uses = {PlayerMapper.class})
public interface TeamMapper {
    TeamSummary mapToTeamSummary(Team team);
}

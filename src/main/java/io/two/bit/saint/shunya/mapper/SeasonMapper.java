package io.two.bit.saint.shunya.mapper;

import io.two.bit.saint.shunya.entity.Season;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.openapitools.model.SeasonCreateRequest;
import org.openapitools.model.SeasonResponse;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface SeasonMapper {
    public Season mapToSeasonEntityFromSeasonCreateRequest(SeasonCreateRequest seasonCreateRequest);

    @Mapping(target = "tournamentId", source = "tournament.id")
    public SeasonResponse mapToSeasonResponseFromSeasonEntity(Season season);
}

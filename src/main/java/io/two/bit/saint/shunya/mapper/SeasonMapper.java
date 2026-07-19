package io.two.bit.saint.shunya.mapper;

import io.two.bit.saint.shunya.entity.Season;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.openapitools.model.SeasonBase;
import org.openapitools.model.SeasonResponse;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface SeasonMapper {
    public Season mapToSeasonEntityFromSeasonBase(SeasonBase seasonBase);

    @Mapping(target = "tournamentId", source = "tournament.id")
    @Mapping(target = "seasonId", source = "id")
    public SeasonResponse mapToSeasonResponseFromSeasonEntity(Season season);
}

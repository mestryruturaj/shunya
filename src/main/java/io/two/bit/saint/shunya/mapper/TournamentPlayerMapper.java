package io.two.bit.saint.shunya.mapper;

import io.two.bit.saint.shunya.dto.TournamentPlayerValidContext;
import io.two.bit.saint.shunya.entity.TournamentPlayer;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface TournamentPlayerMapper {
    public TournamentPlayer mapToTournamentPlayerFromTournamentPlayerValidContext(TournamentPlayerValidContext tournamentPlayerValidContext);
}

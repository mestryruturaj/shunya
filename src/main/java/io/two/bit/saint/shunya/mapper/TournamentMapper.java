package io.two.bit.saint.shunya.mapper;

import io.two.bit.saint.shunya.entity.Tournament;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.openapitools.model.TournamentCreateRequest;
import org.openapitools.model.TournamentResponse;
import org.openapitools.model.TournamentUpdateRequest;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface TournamentMapper {
    public Tournament mapToTournamentFromTournamentCreateRequest(TournamentCreateRequest tournamentCreateRequest);

    public TournamentResponse mapToTournamentResponseFromTournament(Tournament tournament);

    public Tournament mapToTournamentFromTournamentUpdateRequest(TournamentUpdateRequest tournamentUpdateRequest);
}

package io.two.bit.saint.shunya.validator;

import io.two.bit.saint.shunya.dto.AuctionValidContext;
import io.two.bit.saint.shunya.entity.Season;
import io.two.bit.saint.shunya.entity.Tournament;
import io.two.bit.saint.shunya.exception.InvalidArgumentException;
import io.two.bit.saint.shunya.service.SeasonManagementService;
import io.two.bit.saint.shunya.service.TournamentService;
import lombok.RequiredArgsConstructor;
import org.openapitools.model.AuctionBase;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuctionValidator {
    private final TournamentService tournamentService;
    private final SeasonManagementService seasonManagementService;

    public AuctionValidContext validateAuctionRequest(AuctionBase auctionBase) {
        Tournament validatedTournament = tournamentService.fetchBuId(auctionBase.getTournamentId());
        Season validatedSeason = seasonManagementService.fetchById(auctionBase.getSeasonId());
        if (!validatedTournament.getId().equals(validatedSeason.getTournament().getId())) {
            throw new InvalidArgumentException("Season provided does not belong the tournament = " + validatedTournament.getTitle() + " tournamentId = " + validatedTournament.getId());
        }
        return AuctionValidContext.builder()
                .tournament(validatedTournament)
                .season(validatedSeason)
                .build();
    }
}

package io.two.bit.saint.shunya.validator;

import io.two.bit.saint.shunya.dto.AuctionValidContext;
import io.two.bit.saint.shunya.entity.Auction;
import io.two.bit.saint.shunya.entity.Season;
import io.two.bit.saint.shunya.entity.Tournament;
import io.two.bit.saint.shunya.exception.InvalidArgumentException;
import io.two.bit.saint.shunya.service.SeasonManagementService;
import io.two.bit.saint.shunya.service.TournamentService;
import io.two.bit.saint.shunya.utils.ComparisonUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.openapitools.model.AuctionBase;
import org.openapitools.model.AuctionUpdateRequest;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuctionValidator extends BaseValidator {
    private final TournamentService tournamentService;
    private final SeasonManagementService seasonManagementService;

    public AuctionValidContext validateAuctionRequest(AuctionBase auctionBase) {
        Tournament validatedTournament = tournamentService.fetchById(auctionBase.getTournamentId());
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

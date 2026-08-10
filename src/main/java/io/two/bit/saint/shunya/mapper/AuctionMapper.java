package io.two.bit.saint.shunya.mapper;

import io.two.bit.saint.shunya.dto.AuctionValidContext;
import io.two.bit.saint.shunya.entity.Auction;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.openapitools.model.AuctionBase;
import org.openapitools.model.AuctionResponse;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        uses = {TournamentMapper.class, SeasonMapper.class})
public interface AuctionMapper {
    Auction mapToAuctionEntity(AuctionValidContext auctionValidContext);

    AuctionResponse mapToAuctionResponse(Auction auction);
}

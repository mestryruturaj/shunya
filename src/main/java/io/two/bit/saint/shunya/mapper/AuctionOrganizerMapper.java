package io.two.bit.saint.shunya.mapper;

import io.two.bit.saint.shunya.entity.Auction;
import io.two.bit.saint.shunya.entity.AuctionOrganizer;
import io.two.bit.saint.shunya.entity.Organizer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuctionOrganizerMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "auction", source = "auction")
    @Mapping(target = "organizer", source = "organizer")
    AuctionOrganizer mapToAuctionOrganizer(Auction auction, Organizer organizer);
}

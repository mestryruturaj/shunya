package io.two.bit.saint.shunya.dto;

import io.two.bit.saint.shunya.entity.Auction;
import io.two.bit.saint.shunya.entity.AuctionOrganizer;
import io.two.bit.saint.shunya.entity.Organizer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AuctionOrganizerValidContext {
    private Auction auction;
    private List<Organizer> newOrganizers;
    private List<AuctionOrganizer> oldAuctionOrganizers;
}

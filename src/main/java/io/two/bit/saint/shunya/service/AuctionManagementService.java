package io.two.bit.saint.shunya.service;

import io.two.bit.saint.shunya.entity.Auction;
import org.openapitools.model.AuctionCreateRequest;
import org.openapitools.model.AuctionResponse;

public interface AuctionManagementService {
    AuctionResponse createAuction(AuctionCreateRequest auctionCreateRequest);

    AuctionResponse getAuctionById(Long id);

    Auction fetchById(Long id);
}

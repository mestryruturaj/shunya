package io.two.bit.saint.shunya.service;

import org.openapitools.model.AuctionCreateRequest;
import org.openapitools.model.AuctionResponse;

public interface AuctionManagementService {
    AuctionResponse createAuction(AuctionCreateRequest auctionCreateRequest);
}

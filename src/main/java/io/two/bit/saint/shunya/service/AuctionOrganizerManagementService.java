package io.two.bit.saint.shunya.service;

import org.openapitools.model.AuctionOrganizerCreateRequest;
import org.openapitools.model.AuctionOrganizerResponse;

public interface AuctionOrganizerManagementService {
    AuctionOrganizerResponse createAuctionOrganizer(Long auctionId, AuctionOrganizerCreateRequest auctionOrganizerCreateRequest);
}

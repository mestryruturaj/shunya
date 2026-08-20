package io.two.bit.saint.shunya.delegate;

import io.two.bit.saint.shunya.service.AuctionOrganizerManagementService;
import lombok.RequiredArgsConstructor;
import org.openapitools.api.AuctionOrganizerManagementApiDelegate;
import org.openapitools.model.AuctionOrganizerCreateRequest;
import org.openapitools.model.AuctionOrganizerResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuctionOrganizerManagementApiDelegateImpl implements AuctionOrganizerManagementApiDelegate {
    private final AuctionOrganizerManagementService auctionOrganizerManagementService;

    @Override
    public ResponseEntity<AuctionOrganizerResponse> createAuctionOrganizer(Long auctionId, AuctionOrganizerCreateRequest auctionOrganizerCreateRequest) {
        return new ResponseEntity(auctionOrganizerManagementService.createAuctionOrganizer(auctionId, auctionOrganizerCreateRequest), HttpStatus.CREATED);
    }
}

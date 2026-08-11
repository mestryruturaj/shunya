package io.two.bit.saint.shunya.delegate;

import io.two.bit.saint.shunya.service.AuctionManagementService;
import lombok.RequiredArgsConstructor;
import org.openapitools.api.AuctionManagementApiDelegate;
import org.openapitools.model.AuctionCreateRequest;
import org.openapitools.model.AuctionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuctionManagementApiDelegateImpl implements AuctionManagementApiDelegate {

    private final AuctionManagementService auctionManagementService;

    @Override
    public ResponseEntity<AuctionResponse> createAuction(AuctionCreateRequest auctionCreateRequest) {
        return new ResponseEntity<>(auctionManagementService.createAuction(auctionCreateRequest), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<AuctionResponse> getAuctionById(Long auctionId) {
        return new ResponseEntity<>(auctionManagementService.getAuctionById(auctionId), HttpStatus.OK);
    }
}

package io.two.bit.saint.shunya.delegate;

import io.two.bit.saint.shunya.service.AuctionManagementService;
import lombok.RequiredArgsConstructor;
import org.openapitools.api.AuctionManagementApiDelegate;
import org.openapitools.model.AuctionCreateRequest;
import org.openapitools.model.AuctionResponse;
import org.openapitools.model.AuctionUpdateRequest;
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

    @Override
    public ResponseEntity<AuctionResponse> updateAuctionById(Long auctionId, AuctionUpdateRequest auctionUpdateRequest) {
        return new ResponseEntity<>(auctionManagementService.updateAuctionById(auctionId, auctionUpdateRequest), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AuctionResponse> deleteAuctionById(Long auctionId) {
        return new ResponseEntity<>(auctionManagementService.deleteAuctionById(auctionId), HttpStatus.OK);
    }
}

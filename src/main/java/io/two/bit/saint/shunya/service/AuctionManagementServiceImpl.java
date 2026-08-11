package io.two.bit.saint.shunya.service;

import io.two.bit.saint.shunya.dao.AuctionRepository;
import io.two.bit.saint.shunya.dto.AuctionValidContext;
import io.two.bit.saint.shunya.entity.Auction;
import io.two.bit.saint.shunya.exception.InvalidArgumentException;
import io.two.bit.saint.shunya.mapper.AuctionMapper;
import io.two.bit.saint.shunya.validator.AuctionValidator;
import lombok.RequiredArgsConstructor;
import org.openapitools.model.AuctionBase;
import org.openapitools.model.AuctionCreateRequest;
import org.openapitools.model.AuctionResponse;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuctionManagementServiceImpl implements AuctionManagementService {
    private final AuctionValidator auctionValidator;
    private final AuctionMapper auctionMapper;
    private final AuctionRepository auctionRepository;

    public AuctionResponse createAuction(AuctionCreateRequest auctionCreateRequest) {
        AuctionValidContext auctionValidContext = auctionValidator.validateAuctionRequest(auctionCreateRequest);
        Auction unsavedAuction = auctionMapper.mapToAuctionEntity(auctionValidContext);
        unsavedAuction = enhanceAuctionEntity(unsavedAuction, auctionCreateRequest);

        Auction savedAuction = auctionRepository.save(unsavedAuction);
        return auctionMapper.mapToAuctionResponse(savedAuction);
    }

    private Auction enhanceAuctionEntity(Auction auction, AuctionBase auctionBase) {
        auction.setVenue(auctionBase.getVenue());
        auction.setTime(auctionBase.getTime());
        auction.setOrganizer(auctionBase.getOrganizer());
        return auction;
    }

    @Override
    public AuctionResponse getAuctionById(Long id) {
        Auction fetchedAuction = fetchById(id);
        return auctionMapper.mapToAuctionResponse(fetchedAuction);
    }

    @Override
    public Auction fetchById(Long id) {
        auctionValidator.validateIdField(id, Auction.class.getSimpleName());
        return auctionRepository.findById(id)
                .orElseThrow(() -> new InvalidArgumentException("Auction not found with id: " + id));
    }
}

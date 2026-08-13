package io.two.bit.saint.shunya.service;

import io.two.bit.saint.shunya.dao.AuctionRepository;
import io.two.bit.saint.shunya.dto.AuctionValidContext;
import io.two.bit.saint.shunya.entity.Auction;
import io.two.bit.saint.shunya.exception.InvalidArgumentException;
import io.two.bit.saint.shunya.mapper.AuctionMapper;
import io.two.bit.saint.shunya.utils.ComparisonUtils;
import io.two.bit.saint.shunya.validator.AuctionValidator;
import lombok.RequiredArgsConstructor;
import org.openapitools.model.AuctionBase;
import org.openapitools.model.AuctionCreateRequest;
import org.openapitools.model.AuctionResponse;
import org.openapitools.model.AuctionUpdateRequest;
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

    @Override
    public AuctionResponse updateAuctionById(Long auctionId, AuctionUpdateRequest auctionUpdateRequest) {
        Auction fetchedAuction = fetchById(auctionId);
        Auction updatedAuction = updateAuctionEntity(fetchedAuction, auctionUpdateRequest);
        Auction savedAuction = auctionRepository.save(updatedAuction);
        return auctionMapper.mapToAuctionResponse(savedAuction);
    }

    private Auction updateAuctionEntity(Auction existingAuction, AuctionUpdateRequest auctionUpdateRequest) {
        boolean isUpdated = false;
        if (!ComparisonUtils.equals(auctionUpdateRequest.getTournamentId(), existingAuction.getTournament().getId())
                || !ComparisonUtils.equals(auctionUpdateRequest.getSeasonId(), existingAuction.getSeason().getId())) {
            AuctionValidContext auctionValidContext = auctionValidator.validateAuctionRequest(auctionUpdateRequest);
            existingAuction.setTournament(auctionValidContext.getTournament());
            existingAuction.setSeason(auctionValidContext.getSeason());
            isUpdated = true;
        }
        if (!ComparisonUtils.equals(auctionUpdateRequest.getTime(), existingAuction.getTime())) {
            existingAuction.setTime(auctionUpdateRequest.getTime());
            isUpdated = true;
        }
        if (!ComparisonUtils.equals(auctionUpdateRequest.getVenue(), existingAuction.getVenue())) {
            existingAuction.setVenue(auctionUpdateRequest.getVenue());
            isUpdated = true;
        }
        if (!ComparisonUtils.equals(auctionUpdateRequest.getOrganizer(), existingAuction.getOrganizer())) {
            existingAuction.setOrganizer(auctionUpdateRequest.getOrganizer());
            isUpdated = true;
        }

        if (!isUpdated) {
            throw new InvalidArgumentException("Nothing to update in the request.");
        }
        return existingAuction;
    }

    @Override
    public AuctionResponse deleteAuctionById(Long auctionId) {
        Auction fetchedAuction = fetchById(auctionId);
        auctionRepository.delete(fetchedAuction);
        return auctionMapper.mapToAuctionResponse(fetchedAuction);
    }
}
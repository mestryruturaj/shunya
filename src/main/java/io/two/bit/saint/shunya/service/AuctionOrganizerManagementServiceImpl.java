package io.two.bit.saint.shunya.service;

import io.two.bit.saint.shunya.dao.AuctionOrganizerRepository;
import io.two.bit.saint.shunya.dto.AuctionOrganizerValidContext;
import io.two.bit.saint.shunya.entity.Auction;
import io.two.bit.saint.shunya.entity.AuctionOrganizer;
import io.two.bit.saint.shunya.entity.Organizer;
import io.two.bit.saint.shunya.exception.InvalidArgumentException;
import io.two.bit.saint.shunya.mapper.AuctionMapper;
import io.two.bit.saint.shunya.mapper.AuctionOrganizerMapper;
import io.two.bit.saint.shunya.mapper.OrganizerMapper;
import io.two.bit.saint.shunya.validator.AuctionOrganizerValidator;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.openapitools.model.AuctionOrganizerCreateRequest;
import org.openapitools.model.AuctionOrganizerResponse;
import org.openapitools.model.AuctionSummary;
import org.openapitools.model.OrganizerSummary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuctionOrganizerManagementServiceImpl implements AuctionOrganizerManagementService {
    private final AuctionOrganizerMapper auctionOrganizerMapper;
    private final AuctionOrganizerRepository auctionOrganizerRepository;
    private final AuctionMapper auctionMapper;
    private final OrganizerMapper organizerMapper;
    private final AuctionOrganizerValidator auctionOrganizerValidator;
    private final AuctionManagementService auctionManagementService;

    @Override
    public AuctionOrganizerResponse createAuctionOrganizer(Long auctionId, AuctionOrganizerCreateRequest auctionOrganizerCreateRequest) {
        AuctionOrganizerValidContext auctionOrganizerValidContext = auctionOrganizerValidator.validateAuctionOrganizerRequest(auctionId, auctionOrganizerCreateRequest);
        Auction fetchedAuction = auctionOrganizerValidContext.getAuction();
        List<Organizer> fetchedNewOrganizers = auctionOrganizerValidContext.getNewOrganizers();
        List<AuctionOrganizer> allAuctionOrganizers = new ArrayList<>(auctionOrganizerValidContext.getOldAuctionOrganizers());
        if (CollectionUtils.isNotEmpty(fetchedNewOrganizers)) {
            List<AuctionOrganizer> unsavedAuctionOrganizers = buildAuctionOrganizers(fetchedAuction, fetchedNewOrganizers);
            List<AuctionOrganizer> savedAuctionOrganizers = auctionOrganizerRepository.saveAll(unsavedAuctionOrganizers);
            allAuctionOrganizers.addAll(savedAuctionOrganizers);
        }

        return buildAuctionOrganizerResponse(fetchedAuction, allAuctionOrganizers);
    }

    private List<AuctionOrganizer> buildAuctionOrganizers(Auction auction, List<Organizer> organizers) {
        return organizers.stream()
                .map(organizer -> auctionOrganizerMapper.mapToAuctionOrganizer(auction, organizer))
                .toList();
    }

    private AuctionOrganizerResponse buildAuctionOrganizerResponse(Auction auction, List<AuctionOrganizer> auctionOrganizers) {
        AuctionOrganizerResponse auctionOrganizerResponse = new AuctionOrganizerResponse();
        if (CollectionUtils.isEmpty(auctionOrganizers)) {
            return auctionOrganizerResponse;
        }

        AuctionSummary auctionSummary = auctionMapper.mapToAuctionSummary(auction);
        List<OrganizerSummary> organizerSummaryList = auctionOrganizers.stream()
                .map(auctionOrganizer -> organizerMapper.mapToOrganizerSummary(auctionOrganizer.getOrganizer()))
                .toList();

        auctionOrganizerResponse.setAuction(auctionSummary);
        auctionOrganizerResponse.setOrganizers(organizerSummaryList);
        return auctionOrganizerResponse;
    }

    @Override
    public AuctionOrganizerResponse getAuctionOrganizersByAuctionId(Long auctionId) {
        Auction fetchedAuction = auctionManagementService.fetchById(auctionId);
        List<AuctionOrganizer> fetchedAuctionOrganizers = auctionOrganizerRepository.findAllByAuctionId(auctionId);
        if (CollectionUtils.isEmpty(fetchedAuctionOrganizers)) {
            throw new InvalidArgumentException("Organizers are not yet assigned to the Auction " + auctionId);
        }
        return buildAuctionOrganizerResponse(fetchedAuction, fetchedAuctionOrganizers);
    }
}

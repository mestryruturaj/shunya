package io.two.bit.saint.shunya.validator;

import io.two.bit.saint.shunya.dao.AuctionOrganizerRepository;
import io.two.bit.saint.shunya.dao.OrganizerRepository;
import io.two.bit.saint.shunya.dto.AuctionOrganizerValidContext;
import io.two.bit.saint.shunya.entity.Auction;
import io.two.bit.saint.shunya.entity.AuctionOrganizer;
import io.two.bit.saint.shunya.entity.Organizer;
import io.two.bit.saint.shunya.exception.InvalidArgumentException;
import io.two.bit.saint.shunya.service.AuctionManagementService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.openapitools.model.AuctionOrganizerCreateRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AuctionOrganizerValidator {
    private final AuctionOrganizerRepository auctionOrganizerRepository;
    private final AuctionManagementService auctionManagementService;
    private final OrganizerRepository organizerRepository;

    @Value("${auction.organizers.max-limit:5}")
    private Long maxOrganizersPerAuction;

    public AuctionOrganizerValidContext validateAuctionOrganizerRequest(Long auctionId, AuctionOrganizerCreateRequest auctionOrganizerCreateRequest) {
        Auction fetchedAuction = auctionManagementService.fetchById(auctionId);

        List<Long> requestedOrganizerIds = auctionOrganizerCreateRequest.getOrganizerIds();
        BaseValidator.validateEmptyList(requestedOrganizerIds, "requestedOrganizerIds");
        if (CollectionUtils.isNotEmpty(requestedOrganizerIds) && requestedOrganizerIds.size() > maxOrganizersPerAuction) {
            throw new InvalidArgumentException("AO01: Allowed organizers per auction are " + maxOrganizersPerAuction);
        }

        List<AuctionOrganizer> fetchedAuctionOrganizers = auctionOrganizerRepository.findAllByAuctionId(auctionId);
        Set<Long> existingOrganizerIds = fetchedAuctionOrganizers.stream()
                .map(auctionOrganizer -> auctionOrganizer.getOrganizer().getId())
                .collect(Collectors.toSet());
        Set<Long> newOrganizerIds = requestedOrganizerIds.stream()
                .filter(organizerId -> !existingOrganizerIds.contains(organizerId))
                .collect(Collectors.toSet());
        if (maxOrganizersPerAuction < existingOrganizerIds.size() + newOrganizerIds.size()) {
            throw new InvalidArgumentException("AO02: Allowed organizers per auction are " + maxOrganizersPerAuction);
        }

        List<Organizer> fetchedOrganizers = organizerRepository.findAllByIdIn(newOrganizerIds);
        if (fetchedOrganizers.size() != newOrganizerIds.size()) {
            List<Long> missingOrganizerIds = newOrganizerIds.stream()
                    .filter(newOrganizerIds::contains)
                    .toList();
            throw new InvalidArgumentException("Some of the requested organizers are invalid : " + missingOrganizerIds);
        }
        return AuctionOrganizerValidContext.builder()
                .auction(fetchedAuction)
                .newOrganizers(fetchedOrganizers)
                .oldAuctionOrganizers(fetchedAuctionOrganizers)
                .build();
    }
}

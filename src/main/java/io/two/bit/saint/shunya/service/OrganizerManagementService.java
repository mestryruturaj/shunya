package io.two.bit.saint.shunya.service;

import io.two.bit.saint.shunya.entity.Organizer;
import org.openapitools.model.OrganizerCreateRequest;
import org.openapitools.model.OrganizerResponse;

public interface OrganizerManagementService {
    OrganizerResponse createOrganizer(OrganizerCreateRequest organizerCreateRequest);

    Organizer fetchById(Long id);

    OrganizerResponse getOrganizerById(Long organizerId);

    OrganizerResponse deleteOrganizerById(Long organizerId);
}

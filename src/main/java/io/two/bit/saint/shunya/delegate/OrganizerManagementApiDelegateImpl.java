package io.two.bit.saint.shunya.delegate;

import io.two.bit.saint.shunya.service.OrganizerManagementService;
import lombok.RequiredArgsConstructor;
import org.openapitools.api.OrganizerManagementApiDelegate;
import org.openapitools.model.OrganizerCreateRequest;
import org.openapitools.model.OrganizerResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrganizerManagementApiDelegateImpl implements OrganizerManagementApiDelegate {
    private final OrganizerManagementService organizerManagementService;

    @Override
    public ResponseEntity<OrganizerResponse> createOrganizer(OrganizerCreateRequest organizerCreateRequest) {
        return new ResponseEntity<>(organizerManagementService.createOrganizer(organizerCreateRequest), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<OrganizerResponse> getOrganizerById(Long organizerId) {
        return new ResponseEntity<>(organizerManagementService.getOrganizerById(organizerId), HttpStatus.OK);
    }
}

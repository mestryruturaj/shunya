package io.two.bit.saint.shunya.service;

import io.two.bit.saint.shunya.dao.OrganizerRepository;
import io.two.bit.saint.shunya.entity.Organizer;
import io.two.bit.saint.shunya.entity.User;
import io.two.bit.saint.shunya.exception.InvalidArgumentException;
import io.two.bit.saint.shunya.mapper.OrganizerMapper;
import io.two.bit.saint.shunya.validator.OrganizerValidator;
import lombok.RequiredArgsConstructor;
import org.openapitools.model.OrganizerCreateRequest;
import org.openapitools.model.OrganizerResponse;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrganizerManagementServiceImpl implements OrganizerManagementService {

    private final UserService userService;
    private final OrganizerRepository organizerRepository;
    private final OrganizerValidator validator;
    private final OrganizerMapper organizerMapper;

    @Override
    public OrganizerResponse createOrganizer(OrganizerCreateRequest organizerCreateRequest) {
        User fetchedUser = userService.fetchbyId(organizerCreateRequest.getUserId());
        if (organizerRepository.existsByUser(fetchedUser)) {
            throw new InvalidArgumentException("Organizer with user id " + organizerCreateRequest.getUserId() + " already exists.");
        }

        Organizer unsavedOrganizer = organizerMapper.mapToOrganizerEntity(fetchedUser, organizerCreateRequest.getName());
        Organizer savedOrganizer = organizerRepository.save(unsavedOrganizer);
        return organizerMapper.mapToOrganizerResponse(savedOrganizer);
    }

    @Override
    public Organizer fetchById(Long id) {
        validator.validateIdField(id, Organizer.class.getSimpleName());
        return organizerRepository.findById(id)
                .orElseThrow(() -> new InvalidArgumentException("Organizer with id " + id + " does not exist."));
    }

    @Override
    public OrganizerResponse getOrganizerById(Long organizerId) {
        Organizer fetchedOrganizer = fetchById(organizerId);
        return organizerMapper.mapToOrganizerResponse(fetchedOrganizer);
    }
}

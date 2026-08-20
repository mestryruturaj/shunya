package io.two.bit.saint.shunya.mapper;

import io.two.bit.saint.shunya.entity.Organizer;
import io.two.bit.saint.shunya.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.openapitools.model.OrganizerResponse;
import org.openapitools.model.OrganizerSummary;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface OrganizerMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", source = "user")
    @Mapping(target = "name", source = "name")
    Organizer mapToOrganizerEntity(User user, String name);

    @Mapping(target = "userId", source = "organizer.user.id")
    OrganizerResponse mapToOrganizerResponse(Organizer organizer);

    @Mapping(target = "userId", source = "organizer.user.id")
    OrganizerSummary mapToOrganizerSummary(Organizer organizer);
}


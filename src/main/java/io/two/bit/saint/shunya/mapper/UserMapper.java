package io.two.bit.saint.shunya.mapper;

import io.two.bit.saint.shunya.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.openapitools.model.SignupRequest;
import org.openapitools.model.UserDto;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    public User toEntity(SignupRequest signupRequest);

    public UserDto toDto(User user);
}

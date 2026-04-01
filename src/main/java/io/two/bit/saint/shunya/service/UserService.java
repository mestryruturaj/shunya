package io.two.bit.saint.shunya.service;

import org.openapitools.model.SignupRequest;
import org.openapitools.model.UserDto;

public interface UserService {
    public UserDto signup(SignupRequest signupRequest);
}

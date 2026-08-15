package io.two.bit.saint.shunya.service;

import io.two.bit.saint.shunya.entity.User;
import org.openapitools.model.LoginRequest;
import org.openapitools.model.LoginResponse;
import org.openapitools.model.SignupRequest;
import org.openapitools.model.UserDto;

public interface UserService {
    public UserDto signup(SignupRequest signupRequest);

    public LoginResponse login(LoginRequest loginRequest);

    User fetchbyId(Long id);
}

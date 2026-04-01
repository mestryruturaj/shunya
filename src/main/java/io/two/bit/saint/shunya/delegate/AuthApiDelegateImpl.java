package io.two.bit.saint.shunya.delegate;

import io.two.bit.saint.shunya.service.UserService;
import org.openapitools.api.AuthApiDelegate;
import org.openapitools.model.SignupRequest;
import org.openapitools.model.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class AuthApiDelegateImpl implements AuthApiDelegate {
    private final UserService userService;

    public AuthApiDelegateImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<UserDto> signup(SignupRequest signupRequest) {
        return new ResponseEntity<>(userService.signup(signupRequest), HttpStatus.CREATED);
    }
}

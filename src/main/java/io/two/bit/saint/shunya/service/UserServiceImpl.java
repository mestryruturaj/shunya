package io.two.bit.saint.shunya.service;

import io.micrometer.common.util.StringUtils;
import io.two.bit.saint.shunya.dao.UserRepository;
import io.two.bit.saint.shunya.entity.User;
import io.two.bit.saint.shunya.mapper.UserMapper;
import io.two.bit.saint.shunya.validator.UserValidator;
import org.openapitools.model.LoginRequest;
import org.openapitools.model.LoginResponse;
import org.openapitools.model.SignupRequest;
import org.openapitools.model.UserDto;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserValidator userValidator;
    private final UserMapper userMapper;

    public static final String JWT_PLACEHOLDER = "JWT_PLACEHOLDER";

    public UserServiceImpl(UserRepository userRepository, UserValidator userValidator,
                           UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userValidator = userValidator;
        this.userMapper = userMapper;
    }

    @Override
    public UserDto signup(SignupRequest signupRequest) {
        userValidator.validateEmailAndMobileNumberUniqueness(
                signupRequest.getEmail(), signupRequest.getMobileNumber());
        User signedupUser = userMapper.toEntity(signupRequest);
        User savedUser = userRepository.save(signedupUser);
        return userMapper.toDto(savedUser);
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        User user = null;
        if (!StringUtils.isEmpty(loginRequest.getEmail())) {
            user = userRepository.findByEmail(loginRequest.getEmail())
                    .orElse(null);
        } else if (!StringUtils.isEmpty(loginRequest.getMobileNumber())) {
            user = userRepository.findByMobileNumber(loginRequest.getMobileNumber())
                    .orElse(null);
        }

        if (Objects.isNull(user)) {
            throw new IllegalArgumentException("Provided credentials does not belong to any user.");
        }
        if (!user.getPassword().equals(loginRequest.getPassword())) {
            throw new IllegalArgumentException("User credentials are incorrect.");
        }

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setUser(userMapper.toDto(user));
        loginResponse.setMessage("User successfully logged in.");
        loginResponse.setToken(JWT_PLACEHOLDER);
        return loginResponse;
    }
}

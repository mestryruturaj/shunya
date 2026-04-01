package io.two.bit.saint.shunya.service;

import io.two.bit.saint.shunya.dao.UserRepository;
import io.two.bit.saint.shunya.entity.User;
import io.two.bit.saint.shunya.mapper.UserMapper;
import io.two.bit.saint.shunya.validator.UserValidator;
import org.openapitools.model.SignupRequest;
import org.openapitools.model.UserDto;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserValidator userValidator;
    private final UserMapper userMapper;

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
}

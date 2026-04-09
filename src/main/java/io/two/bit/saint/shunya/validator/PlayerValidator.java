package io.two.bit.saint.shunya.validator;

import io.two.bit.saint.shunya.dao.PlayerRepository;
import io.two.bit.saint.shunya.dao.UserRepository;
import io.two.bit.saint.shunya.entity.User;
import org.apache.commons.lang3.ObjectUtils;
import org.openapitools.model.PlayerCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PlayerValidator {
    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private UserRepository userRepository;

    public User validateCreatePlayerRequestForUserAndPlayer(PlayerCreateRequest playerCreateRequest) {
        User user = null;
        if (ObjectUtils.isNotEmpty(playerCreateRequest.getUserId())) {
            user = userRepository.findById(playerCreateRequest.getUserId())
                    .orElseThrow(() -> new IllegalArgumentException("User with id " + playerCreateRequest.getUserId() + " does not exist"));
            if (playerRepository.existsByUserId(playerCreateRequest.getUserId())) {
                throw new IllegalArgumentException("Player with user id " + playerCreateRequest.getUserId() + " already exists");
            }
        } else if (ObjectUtils.isNotEmpty(playerCreateRequest.getEmail())) {
            user = userRepository.findByEmail(playerCreateRequest.getEmail())
                    .orElseThrow(() -> new IllegalArgumentException("User with email " + playerCreateRequest.getEmail() + " does not exist"));
        } else if (ObjectUtils.isNotEmpty(playerCreateRequest.getMobileNumber())) {
            user = userRepository.findByMobileNumber(playerCreateRequest.getMobileNumber())
                    .orElseThrow(() -> new IllegalArgumentException("User with mobile number " + playerCreateRequest.getMobileNumber() + " does not exist"));
        } else {
            throw new IllegalArgumentException("At least one of valid userId, email or phoneNumber must be provided");
        }

        return user;
    }
}

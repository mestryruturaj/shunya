package io.two.bit.saint.shunya.validator;

import io.two.bit.saint.shunya.dao.PlayerRepository;
import io.two.bit.saint.shunya.dao.UserRepository;
import io.two.bit.saint.shunya.entity.User;
import io.two.bit.saint.shunya.exception.InvalidArgumentException;
import org.openapitools.model.PlayerCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PlayerValidator {
    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private UserRepository userRepository;

    public User validatePlayerCreateRequest(PlayerCreateRequest playerCreateRequest) {
        if (playerCreateRequest.getUserId() == null) {
            throw new InvalidArgumentException("userId is required to create a player");
        }
        User user = userRepository.findById(playerCreateRequest.getUserId())
                .orElseThrow(() -> new InvalidArgumentException("No user found with the provided userId"));
        if (playerRepository.existsByUserId(playerCreateRequest.getUserId())) {
            throw new InvalidArgumentException("Player with user id " + playerCreateRequest.getUserId() + " already exists");
        }

        return user;
    }

    public void validatePlayerExistToUpdateOrDelete(Long playerId) {
        if (playerId == null) {
            throw new InvalidArgumentException("userId is required to update or delete a player");
        }
        if (!playerRepository.existsById(playerId)) {
            throw new InvalidArgumentException("Player with id " + playerId + " does not exist");
        }
    }

}

package io.two.bit.saint.shunya.validator;

import org.openapitools.model.PlayerCreateRequest;

public class PlayerValidator {
    private void validatePlayerCreateRequest(PlayerCreateRequest playerCreateRequest) {
            if (playerCreateRequest.getName() == null || playerCreateRequest.getName().isEmpty()) {
                throw new IllegalArgumentException("Player name is required");
            }
            if (playerCreateRequest.getEmail() == null || playerCreateRequest.getEmail().isEmpty()) {
                throw new IllegalArgumentException("Player email is required");
            }
            if (playerCreateRequest.getPassword() == null || playerCreateRequest.getPassword().isEmpty()) {
                throw new IllegalArgumentException("Player password is required");
            }
    }
}

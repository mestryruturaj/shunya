package io.two.bit.saint.shunya.service;

import org.openapitools.model.PlayerCreateRequest;
import org.openapitools.model.PlayerDto;
import org.openapitools.model.PlayerUpdateRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PlayerService {
    default PlayerDto createPlayer(PlayerCreateRequest playerCreateRequest) {
        return null;
    }

    default List<PlayerDto> getPlayers() {
        return null;
    }

    default PlayerDto updatePlayer(Long playerId,
                                                   PlayerUpdateRequest playerUpdateRequest) {
        return null;
    }

    default PlayerDto getPlayerById(String playerId) {
        return null;
    }

    default PlayerDto deletePlayer(String playerId) {
        return null;
    }
}

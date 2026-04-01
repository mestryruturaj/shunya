package io.two.bit.saint.shunya.service;

import org.openapitools.model.PlayerCreateRequest;
import org.openapitools.model.PlayerDto;
import org.openapitools.model.PlayerUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {
    @Override
    public PlayerDto createPlayer(PlayerCreateRequest playerCreateRequest) {
        return PlayerService.super.createPlayer(playerCreateRequest);
    }

    @Override
    public List<PlayerDto> getPlayers() {
        return PlayerService.super.getPlayers();
    }

    @Override
    public PlayerDto updatePlayer(PlayerUpdateRequest playerUpdateRequest) {
        return PlayerService.super.updatePlayer(playerUpdateRequest);
    }

    @Override
    public PlayerDto getPlayerById(String playerId) {
        return PlayerService.super.getPlayerById(playerId);
    }

    @Override
    public PlayerDto deletePlayer(String playerId) {
        return PlayerService.super.deletePlayer(playerId);
    }
}

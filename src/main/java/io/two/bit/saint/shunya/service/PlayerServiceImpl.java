package io.two.bit.saint.shunya.service;

import io.two.bit.saint.shunya.dao.PlayerRepository;
import io.two.bit.saint.shunya.entity.Player;
import io.two.bit.saint.shunya.entity.User;
import io.two.bit.saint.shunya.mapper.PlayerMapper;
import io.two.bit.saint.shunya.validator.PlayerValidator;
import org.openapitools.model.PlayerCreateRequest;
import org.openapitools.model.PlayerDto;
import org.openapitools.model.PlayerUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {
    @Autowired
    private PlayerValidator playerValidator;

    @Autowired
    private PlayerMapper playerMapper;

    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public PlayerDto createPlayer(PlayerCreateRequest playerCreateRequest) {
        User existingUser = playerValidator.validateCreatePlayerRequestForUserAndPlayer(playerCreateRequest);
        Player newPlayer = playerMapper.mapPlayerEntityFromPlayerCreateRequest(playerCreateRequest);
        newPlayer.setUser(existingUser);
        Player savedPlayer = playerRepository.save(newPlayer);
        return playerMapper.mapPlayerDtoFromPlayerEntity(savedPlayer);
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

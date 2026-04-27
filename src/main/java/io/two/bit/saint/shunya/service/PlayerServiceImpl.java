package io.two.bit.saint.shunya.service;

import io.two.bit.saint.shunya.dao.PlayerRepository;
import io.two.bit.saint.shunya.entity.Player;
import io.two.bit.saint.shunya.entity.User;
import io.two.bit.saint.shunya.exception.InvalidArgumentException;
import io.two.bit.saint.shunya.mapper.PlayerMapper;
import io.two.bit.saint.shunya.validator.PlayerValidator;
import org.openapitools.model.PlayerCreateRequest;
import org.openapitools.model.PlayerDto;
import org.openapitools.model.PlayerUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        User existingUser = playerValidator.validatePlayerCreateRequest(playerCreateRequest);
        Player newPlayer = playerMapper.mapToPlayerEntityFromPlayerCreateRequest(playerCreateRequest);
        newPlayer.setUser(existingUser);
        Player savedPlayer = playerRepository.save(newPlayer);
        return playerMapper.mapToPlayerDtoFromPlayerEntity(savedPlayer);
    }

    @Override
    public List<PlayerDto> getPlayers() {
        return playerRepository.findAll().stream()
                .map(player -> playerMapper.mapToPlayerDtoFromPlayerEntity(player))
                .toList();
    }

    @Override
    public PlayerDto updatePlayer(Long playerId, PlayerUpdateRequest playerUpdateRequest) {
        playerValidator.validatePlayerExistToUpdateOrDelete(playerId);
        Player player = playerMapper.mapToPlayerEntityFromPlayerUpdateRequest(playerUpdateRequest);
        player.setId(playerId);
        Player updatedPlayer = playerRepository.save(player);
        return playerMapper.mapToPlayerDtoFromPlayerEntity(updatedPlayer);
    }

    @Override
    public PlayerDto getPlayerById(Long playerId) {
        Optional<Player> playerOptional = playerRepository.findById(playerId);
        Player player = playerOptional.orElseThrow(() -> new InvalidArgumentException("Player with id " + playerId + " does not exist"));
        return playerMapper.mapToPlayerDtoFromPlayerEntity(player);
    }

    @Override
    public PlayerDto deletePlayer(Long playerId) {
        Optional<Player> playerOptional = playerRepository.findById(playerId);
        Player player = playerOptional.orElseThrow(() -> new InvalidArgumentException("Player with id " + playerId + " does not exist"));
        return playerMapper.mapToPlayerDtoFromPlayerEntity(player);
    }
}

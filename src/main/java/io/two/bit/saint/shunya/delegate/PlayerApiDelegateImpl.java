package io.two.bit.saint.shunya.delegate;

import io.two.bit.saint.shunya.service.PlayerService;
import org.openapitools.api.PlayerApiDelegate;
import org.openapitools.model.PlayerCreateRequest;
import org.openapitools.model.PlayerDto;
import org.openapitools.model.PlayerUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PlayerApiDelegateImpl implements PlayerApiDelegate {

    @Autowired
    private PlayerService playerService;

    /**
     * POST /player - Create a new player
     *
     * @param playerCreateRequest the player creation request
     * @return ResponseEntity containing the created PlayerDto with 201 status
     */
    @Override
    public ResponseEntity<PlayerDto> createPlayer(PlayerCreateRequest playerCreateRequest) {
        return ResponseEntity.ok(playerService.createPlayer(playerCreateRequest));
    }

    /**
     * GET /player - Get all players
     *
     * @return ResponseEntity containing list of PlayerDto with 200 status
     */
    @Override
    public ResponseEntity<List<PlayerDto>> getPlayers() {
        return ResponseEntity.ok(playerService.getPlayers());
    }

    /**
     * PUT /player - Update player information
     *
     * @param playerUpdateRequest the player update request
     * @return ResponseEntity containing updated PlayerDto with 200 status
     */
    @Override
    public ResponseEntity<PlayerDto> updatePlayer(Long playerId,
                                                  PlayerUpdateRequest playerUpdateRequest) {
        return ResponseEntity.ok(playerService.updatePlayer(playerId, playerUpdateRequest));
    }

    /**
     * GET /player/{playerId} - Get player by ID
     *
     * @param playerId unique identifier of the player
     * @return ResponseEntity containing PlayerDto with 200 status
     */
    @Override
    public ResponseEntity<PlayerDto> getPlayerById(Long playerId) {
        return ResponseEntity.ok(playerService.getPlayerById(playerId));
    }

    /**
     * DELETE /player/{playerId} - Delete player by ID
     *
     * @param playerId unique identifier of the player
     * @return ResponseEntity with 204 NO CONTENT status
     */
    @Override
    public ResponseEntity<PlayerDto> deletePlayerById(Long playerId) {
        return ResponseEntity.ok(playerService.deletePlayer(playerId));
    }
}

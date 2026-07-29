package io.two.bit.saint.shunya.validator;

import io.two.bit.saint.shunya.dao.PlayerRepository;
import io.two.bit.saint.shunya.dao.SeasonPlayerRepository;
import io.two.bit.saint.shunya.dao.SeasonRepository;
import io.two.bit.saint.shunya.dto.SeasonPlayerValidContext;
import io.two.bit.saint.shunya.dto.enums.RequestType;
import io.two.bit.saint.shunya.entity.Player;
import io.two.bit.saint.shunya.entity.Season;
import io.two.bit.saint.shunya.exception.InvalidArgumentException;
import lombok.RequiredArgsConstructor;
import org.openapitools.model.SeasonPlayerBase;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SeasonPlayerValidator extends BaseValidator {
    private final SeasonRepository seasonRepository;
    private final PlayerRepository playerRepository;
    private final SeasonPlayerRepository seasonPlayerRepository;

    public SeasonPlayerValidContext validateSeasonPlayer(SeasonPlayerBase seasonPlayerRequest, RequestType requestType) {
        Long seasonId = seasonPlayerRequest.getSeasonId();
        Long playerId = seasonPlayerRequest.getPlayerId();
        validateIdField(seasonId, Season.class.getSimpleName());
        validateIdField(playerId, Player.class.getSimpleName());

        Season season = seasonRepository.findById(seasonId)
                .orElseThrow(() -> new InvalidArgumentException("Season not found with id: " + seasonId));
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new InvalidArgumentException("Player not found with id: " + playerId));

        validateSeasonPlayerRequest(seasonPlayerRequest, requestType);
        return SeasonPlayerValidContext.builder()
                .season(season)
                .player(player)
                .build();
    }

    private void validateSeasonPlayerRequest(SeasonPlayerBase seasonPlayerRequest, RequestType requestType) {
        if (RequestType.CREATE.equals(requestType)) {
            if (seasonPlayerRepository.existsBySeasonIdAndPlayerId(seasonPlayerRequest.getSeasonId(), seasonPlayerRequest.getPlayerId())) {
                throw new InvalidArgumentException("Season player already exists for seasonId: " + seasonPlayerRequest.getSeasonId() + " and playerId: " + seasonPlayerRequest.getPlayerId());
            }
        }
    }
}

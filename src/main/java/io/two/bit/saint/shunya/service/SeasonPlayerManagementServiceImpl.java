package io.two.bit.saint.shunya.service;

import io.two.bit.saint.shunya.dao.SeasonPlayerRepository;
import io.two.bit.saint.shunya.dto.SeasonPlayerValidContext;
import io.two.bit.saint.shunya.dto.enums.RequestType;
import io.two.bit.saint.shunya.entity.Player;
import io.two.bit.saint.shunya.entity.Season;
import io.two.bit.saint.shunya.entity.SeasonPlayer;
import io.two.bit.saint.shunya.exception.InvalidArgumentException;
import io.two.bit.saint.shunya.mapper.PlayerMapper;
import io.two.bit.saint.shunya.mapper.SeasonMapper;
import io.two.bit.saint.shunya.validator.SeasonPlayerValidator;
import lombok.RequiredArgsConstructor;
import org.openapitools.model.SeasonPlayerCreateRequest;
import org.openapitools.model.SeasonPlayerResponse;
import org.openapitools.model.SeasonPlayersResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeasonPlayerManagementServiceImpl implements SeasonPlayerManagementService {
    private final SeasonPlayerValidator seasonPlayerValidator;
    private final SeasonPlayerRepository seasonPlayerRepository;
    private final SeasonMapper seasonMapper;
    private final PlayerMapper playerMapper;
    private final SeasonManagementService seasonManagementService;

    @Override
    public SeasonPlayerResponse createSeasonPlayer(SeasonPlayerCreateRequest seasonPlayerCreateRequest) {
        SeasonPlayerValidContext seasonPlayerValidContext = seasonPlayerValidator.validateSeasonPlayer(seasonPlayerCreateRequest, RequestType.CREATE);

        SeasonPlayer unsavedSeasonPlayer = SeasonPlayer.builder()
                .season(seasonPlayerValidContext.getSeason())
                .player(seasonPlayerValidContext.getPlayer())
                .build();
        SeasonPlayer savedSeasonPlayer = seasonPlayerRepository.save(unsavedSeasonPlayer);

        return buildSeasonPlayerResponse(savedSeasonPlayer);
    }

    private SeasonPlayerResponse buildSeasonPlayerResponse(SeasonPlayer seasonPlayer) {
        SeasonPlayerResponse seasonPlayerResponse = new SeasonPlayerResponse();
        seasonPlayerResponse.setSeason(seasonMapper.mapToSeasonInfoFromSeasonEntity(seasonPlayer.getSeason()));
        seasonPlayerResponse.setPlayer(playerMapper.mapToPlayerInfoFromPlayerEntity(seasonPlayer.getPlayer()));
        seasonPlayerResponse.setId(seasonPlayer.getId());
        return seasonPlayerResponse;
    }

    @Override
    public SeasonPlayerResponse getSeasonPlayerById(Long seasonPlayerId) {
        seasonPlayerValidator.validateIdField(seasonPlayerId, SeasonPlayer.class.getSimpleName());

        SeasonPlayer fetchedSeasonPlayer = seasonPlayerRepository.findById(seasonPlayerId)
                .orElseThrow(() -> new InvalidArgumentException("Season player not found with id: " + seasonPlayerId));

        return buildSeasonPlayerResponse(fetchedSeasonPlayer);
    }

    @Override
    public SeasonPlayersResponse getSeasonPlayersBySeasonId(Long seasonId) {
        Season fetchedSeason = seasonManagementService.fetchById(seasonId);
        List<Player> fetchedPlayersBySeasonId = seasonPlayerRepository.findPlayersBySeasonId(seasonId);

        return buildSeasonPlayersResponse(fetchedSeason, fetchedPlayersBySeasonId);
    }

    private SeasonPlayersResponse buildSeasonPlayersResponse(Season season, List<Player> players) {
        SeasonPlayersResponse seasonPlayersResponse = new SeasonPlayersResponse();
        seasonPlayersResponse.setSeason(seasonMapper.mapToSeasonInfoFromSeasonEntity(season));
        seasonPlayersResponse.setPlayers(players.stream()
                .map(playerMapper::mapToPlayerInfoFromPlayerEntity)
                .toList());
        return seasonPlayersResponse;
    }
}

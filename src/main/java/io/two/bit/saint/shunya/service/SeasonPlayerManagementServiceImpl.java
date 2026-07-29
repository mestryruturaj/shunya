package io.two.bit.saint.shunya.service;

import io.two.bit.saint.shunya.dao.SeasonPlayerRepository;
import io.two.bit.saint.shunya.dto.SeasonPlayerValidContext;
import io.two.bit.saint.shunya.dto.enums.RequestType;
import io.two.bit.saint.shunya.entity.SeasonPlayer;
import io.two.bit.saint.shunya.mapper.PlayerMapper;
import io.two.bit.saint.shunya.mapper.SeasonMapper;
import io.two.bit.saint.shunya.validator.SeasonPlayerValidator;
import lombok.RequiredArgsConstructor;
import org.openapitools.model.SeasonPlayerCreateRequest;
import org.openapitools.model.SeasonPlayerResponse;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SeasonPlayerManagementServiceImpl implements SeasonPlayerManagementService {
    private final SeasonPlayerValidator seasonPlayerValidator;
    private final SeasonPlayerRepository seasonPlayerRepository;
    private final SeasonMapper seasonMapper;
    private final PlayerMapper playerMapper;

    @Override
    public SeasonPlayerResponse createSeasonPlayer(SeasonPlayerCreateRequest seasonPlayerCreateRequest) {
        SeasonPlayerValidContext seasonPlayerValidContext = seasonPlayerValidator.validateSeasonPlayer(seasonPlayerCreateRequest, RequestType.CREATE);

        SeasonPlayer unsavedSeasonPlayer = SeasonPlayer.builder()
                .season(seasonPlayerValidContext.getSeason())
                .player(seasonPlayerValidContext.getPlayer())
                .build();
        SeasonPlayer savedSeasonPlayer = seasonPlayerRepository.save(unsavedSeasonPlayer);

        SeasonPlayerResponse seasonPlayerResponse = new SeasonPlayerResponse();
        seasonPlayerResponse.setSeason(seasonMapper.mapToSeasonInfoFromSeasonEntity(savedSeasonPlayer.getSeason()));
        seasonPlayerResponse.setPlayer(playerMapper.mapToPlayerInfoFromPlayerEntity(savedSeasonPlayer.getPlayer()));
        seasonPlayerResponse.setId(savedSeasonPlayer.getId());

        return seasonPlayerResponse;
    }
}

package io.two.bit.saint.shunya.service;

import io.two.bit.saint.shunya.dao.TournamentPlayerRepository;
import io.two.bit.saint.shunya.dto.TournamentPlayerValidContext;
import io.two.bit.saint.shunya.entity.TournamentPlayer;
import io.two.bit.saint.shunya.exception.InvalidArgumentException;
import io.two.bit.saint.shunya.mapper.PlayerMapper;
import io.two.bit.saint.shunya.mapper.TournamentMapper;
import io.two.bit.saint.shunya.mapper.TournamentPlayerMapper;
import io.two.bit.saint.shunya.validator.TournamentPlayerValidator;
import lombok.RequiredArgsConstructor;
import org.openapitools.model.PlayerDto;
import org.openapitools.model.TournamentPlayerCreateRequest;
import org.openapitools.model.TournamentPlayerResponse;
import org.openapitools.model.TournamentResponse;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TournamentPlayerManagementServiceImpl implements TournamentPlayerManagementService {

    private final TournamentPlayerValidator tournamentPlayerValidator;
    private final TournamentMapper tournamentMapper;
    private final PlayerMapper playerMapper;
    private final TournamentPlayerRepository tournamentPlayerRepository;
    private final TournamentPlayerMapper tournamentPlayerMapper;

    @Override
    public TournamentPlayerResponse createTournamentPlayer(TournamentPlayerCreateRequest tournamentPlayerCreateRequest) {
        Long tournamentId = tournamentPlayerCreateRequest.getTournamentId();
        Long playerId = tournamentPlayerCreateRequest.getPlayerId();
        TournamentPlayerValidContext tournamentPlayerValidContext = tournamentPlayerValidator.validateTournamentPlayer(tournamentId, playerId);

        TournamentPlayer savedTournamentPlayer = tournamentPlayerRepository.save(
                tournamentPlayerMapper.mapToTournamentPlayerFromTournamentPlayerValidContext(tournamentPlayerValidContext));

        return buildTournamentPlayerResponse(savedTournamentPlayer);
    }

    private TournamentPlayerResponse buildTournamentPlayerResponse(TournamentPlayer tournamentPlayer) {
        TournamentResponse tournamentResponse = tournamentMapper.mapToTournamentResponseFromTournament(tournamentPlayer.getTournament());
        PlayerDto playerDto = playerMapper.mapToPlayerDtoFromPlayerEntity(tournamentPlayer.getPlayer());

        TournamentPlayerResponse tournamentPlayerResponse = new TournamentPlayerResponse();
        tournamentPlayerResponse.setTournamentPlayerId(tournamentPlayer.getId());
        tournamentPlayerResponse.setTournament(tournamentResponse);
        tournamentPlayerResponse.setPlayer(playerDto);
        return tournamentPlayerResponse;
    }

    @Override
    public TournamentPlayerResponse getTournamentPlayersByTournamentId(Long tournamentPlayerId) {
        TournamentPlayer validTournamentPlayer = tournamentPlayerRepository.findById(tournamentPlayerId)
                .orElseThrow(() -> new InvalidArgumentException("Tournament player not found with id: " + tournamentPlayerId));

        return buildTournamentPlayerResponse(validTournamentPlayer);
    }
}
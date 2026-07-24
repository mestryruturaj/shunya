package io.two.bit.saint.shunya.service;

import io.two.bit.saint.shunya.dao.TournamentPlayerRepository;
import io.two.bit.saint.shunya.dto.TournamentPlayerValidContext;
import io.two.bit.saint.shunya.entity.Player;
import io.two.bit.saint.shunya.entity.Tournament;
import io.two.bit.saint.shunya.entity.TournamentPlayer;
import io.two.bit.saint.shunya.exception.InvalidArgumentException;
import io.two.bit.saint.shunya.mapper.PlayerMapper;
import io.two.bit.saint.shunya.mapper.TournamentMapper;
import io.two.bit.saint.shunya.mapper.TournamentPlayerMapper;
import io.two.bit.saint.shunya.validator.TournamentPlayerValidator;
import io.two.bit.saint.shunya.validator.TournamentValidator;
import lombok.RequiredArgsConstructor;
import org.openapitools.model.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TournamentPlayerManagementServiceImpl implements TournamentPlayerManagementService {

    private final TournamentValidator tournamentValidator;
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
    public TournamentPlayerResponse getTournamentPlayerById(Long tournamentPlayerId) {
        TournamentPlayer validTournamentPlayer = tournamentPlayerRepository.findById(tournamentPlayerId)
                .orElseThrow(() -> new InvalidArgumentException("Tournament player not found with id: " + tournamentPlayerId));

        return buildTournamentPlayerResponse(validTournamentPlayer);
    }

    public TournamentPlayersResponse getTournamentPlayersByTournamentId(Long tournamentId) {
        // Validate the tournament ID
        Tournament existingTournament = tournamentValidator.validateAndFetchTournament(tournamentId);

        // Fetch the list of TournamentPlayer entities for the given tournament ID
        List<Player> playersInTournament = tournamentPlayerRepository.findPlayersByTournamentId(tournamentId);

        return buildTournamentPlayersResponse(existingTournament, playersInTournament);
    }

    private TournamentPlayersResponse buildTournamentPlayersResponse(Tournament existingTournament, List<Player> playersInTournament) {
        TournamentPlayersResponse tournamentPlayersResponse = new TournamentPlayersResponse();
        tournamentPlayersResponse.setTournament(tournamentMapper.mapToTournamentResponseFromTournament(existingTournament));
        tournamentPlayersResponse.setPlayers(playersInTournament.stream()
                .map(playerMapper::mapToPlayerDtoFromPlayerEntity)
                .toList());
        return tournamentPlayersResponse;
    }
}
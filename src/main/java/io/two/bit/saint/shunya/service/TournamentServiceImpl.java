package io.two.bit.saint.shunya.service;

import io.two.bit.saint.shunya.dao.TournamentRepository;
import io.two.bit.saint.shunya.entity.Tournament;
import io.two.bit.saint.shunya.exception.InvalidArgumentException;
import io.two.bit.saint.shunya.mapper.TournamentMapper;
import lombok.RequiredArgsConstructor;
import org.openapitools.model.TournamentCreateRequest;
import org.openapitools.model.TournamentResponse;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TournamentServiceImpl implements TournamentService {
    private final TournamentRepository tournamentRepository;
    private final TournamentMapper tournamentMapper;

    @Override
    public TournamentResponse createTournament(TournamentCreateRequest tournamentCreateRequest) {
        Tournament tournament = tournamentMapper.mapToTournamentFromTournamentCreateRequest(tournamentCreateRequest);
        Tournament savedTournament = tournamentRepository.save(tournament);
        return tournamentMapper.mapToTournamentResponseFromTournament(savedTournament);
    }

    @Override
    public TournamentResponse getTournamentById(Long tournamentId) {
        Optional<Tournament> tournamentOptional = tournamentRepository.findById(tournamentId);
        if (tournamentOptional.isPresent()) {
            return tournamentMapper.mapToTournamentResponseFromTournament(tournamentOptional.get());
        } else {
            throw new InvalidArgumentException("Tournament not found with id: " + tournamentId);
        }
    }
}

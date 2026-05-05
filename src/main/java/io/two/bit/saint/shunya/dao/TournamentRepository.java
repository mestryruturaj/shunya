package io.two.bit.saint.shunya.dao;

import io.two.bit.saint.shunya.entity.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TournamentRepository extends JpaRepository<Tournament, Long> {
}

package io.two.bit.saint.shunya.repository;

import io.two.bit.saint.shunya.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for Player entity
 * Provides CRUD operations and custom queries for Player persistence
 */
@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
}


package io.two.bit.saint.shunya.dao;

import io.two.bit.saint.shunya.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    boolean existsByUserId(Long userId);

    Optional<Player> findByUserId(Long userId);
}

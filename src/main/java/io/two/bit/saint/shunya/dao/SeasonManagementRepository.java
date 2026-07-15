package io.two.bit.saint.shunya.dao;

import io.two.bit.saint.shunya.entity.Season;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeasonManagementRepository extends JpaRepository<Season, Long> {
}

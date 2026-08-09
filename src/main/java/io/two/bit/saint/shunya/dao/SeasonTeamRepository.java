package io.two.bit.saint.shunya.dao;


import io.two.bit.saint.shunya.entity.SeasonTeam;
import io.two.bit.saint.shunya.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SeasonTeamRepository extends JpaRepository<SeasonTeam, Long> {

    @Query("SELECT st.team FROM SeasonTeam st WHERE st.season.id = :seasonId")
    List<Team> findAllTeamsBySeasonId(@Param("seasonId") Long seasonId);
}

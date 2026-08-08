package io.two.bit.saint.shunya.dto;

import io.two.bit.saint.shunya.entity.Season;
import io.two.bit.saint.shunya.entity.Team;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SeasonTeamValidContext {
    private Season season;
    private Team team;
}

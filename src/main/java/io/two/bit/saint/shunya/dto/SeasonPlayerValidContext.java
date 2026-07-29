package io.two.bit.saint.shunya.dto;

import io.two.bit.saint.shunya.entity.Player;
import io.two.bit.saint.shunya.entity.Season;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SeasonPlayerValidContext {
    private Season season;
    private Player player;
}

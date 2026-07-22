package io.two.bit.saint.shunya.dto;

import io.two.bit.saint.shunya.entity.Player;
import io.two.bit.saint.shunya.entity.Tournament;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TournamentPlayerValidContext {
    private Tournament tournament;
    private Player player;
}

package io.two.bit.saint.shunya.dto;

import io.two.bit.saint.shunya.entity.Season;
import io.two.bit.saint.shunya.entity.Tournament;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AuctionValidContext {
    private Tournament tournament;
    private Season season;
}

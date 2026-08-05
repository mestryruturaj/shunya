package io.two.bit.saint.shunya.dto;

import io.two.bit.saint.shunya.entity.Player;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeamValidContext {
    private String teamName;
    private Player captain;
    private Player viceCaptain;
}

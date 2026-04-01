package io.two.bit.saint.shunya;

import io.two.bit.saint.shunya.entity.Player;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.openapitools.model.PlayerCreateRequest;
import org.openapitools.model.PlayerUpdateRequest;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface PlayerMapper {
    Player toEntity(PlayerCreateRequest playerCreateRequest);

    Player toEntity(PlayerUpdateRequest playerUpdateRequest);

    // MapStruct will automatically use this for the profileImageUrl field
    default String mapUriToString(java.net.URI value) {
        return value != null ? value.toString() : null;
    }
}

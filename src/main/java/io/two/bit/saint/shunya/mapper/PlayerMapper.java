package io.two.bit.saint.shunya.mapper;

import io.two.bit.saint.shunya.entity.Player;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.openapitools.model.PlayerCreateRequest;
import org.openapitools.model.PlayerDto;
import org.openapitools.model.PlayerUpdateRequest;

import java.net.URI;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface PlayerMapper {
    @Named("mapImageUriToString")
    default String mapImageUriToString(URI imageUrl) {
        return imageUrl != null ? imageUrl.toString() : null;
    }

    @Mapping(target = "profileImageUrl", source = "profileImageUrl", qualifiedByName = "mapImageUriToString")
    public Player mapToPlayerEntityFromPlayerCreateRequest(PlayerCreateRequest playerCreateRequest);

    public PlayerDto mapToPlayerDtoFromPlayerEntity(Player player);

    @Mapping(target = "profileImageUrl", source = "profileImageUrl", qualifiedByName = "mapImageUriToString")
    public Player mapToPlayerEntityFromPlayerUpdateRequest(PlayerUpdateRequest playerUpdateRequest);
}

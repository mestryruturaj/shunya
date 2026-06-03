package io.two.bit.saint.shunya.enums.season;

/**
 * Enum for ground types used in cricket seasons
 */
public enum GroundTypeEnum {
    TURF("Turf ground"),
    GRASS("Grass ground");

    private final String description;

    GroundTypeEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return this.name();
    }
}


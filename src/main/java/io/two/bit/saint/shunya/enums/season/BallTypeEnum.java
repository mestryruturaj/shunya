package io.two.bit.saint.shunya.enums.season;

/**
 * Enum for types of balls used in cricket seasons
 */
public enum BallTypeEnum {
    LEATHER("Leather ball - standard cricket ball"),
    TENNIS("Tennis ball - used in informal cricket");

    private final String description;

    BallTypeEnum(String description) {
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


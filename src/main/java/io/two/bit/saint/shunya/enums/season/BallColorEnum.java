package io.two.bit.saint.shunya.enums.season;

/**
 * Enum for colors of balls used in cricket seasons
 */
public enum BallColorEnum {
    RED("Red ball - traditional test cricket"),
    WHITE("White ball - used in limited overs cricket"),
    PINK("Pink ball - used in day-night matches");

    private final String description;

    BallColorEnum(String description) {
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


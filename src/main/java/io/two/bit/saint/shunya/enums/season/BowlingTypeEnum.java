package io.two.bit.saint.shunya.enums.season;

/**
 * Enum for bowling types allowed in cricket seasons
 */
public enum BowlingTypeEnum {
    OVERARM("Overarm bowling"),
    HALF_PITCH("Half pitch bowling"),
    UNDERARM("Underarm bowling");

    private final String description;

    BowlingTypeEnum(String description) {
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


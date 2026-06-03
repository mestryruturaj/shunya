package io.two.bit.saint.shunya.enums.season;

/**
 * Enum for status of a cricket season
 */
public enum SeasonStatusEnum {
    SCHEDULED("Season is scheduled to begin"),
    ONGOING("Season is currently ongoing"),
    COMPLETED("Season has been completed");

    private final String description;

    SeasonStatusEnum(String description) {
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


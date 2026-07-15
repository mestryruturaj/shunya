package io.two.bit.saint.shunya.enums.season;

/**
 * Enum for cricket format types (game durations)
 */
public enum FormatTypeEnum {
    T20("T20 - 20 overs per side"),
    T10("T10 - 10 overs per side"),
    FIFTY_OVERS("50 overs per side");

    private final String description;

    FormatTypeEnum(String description) {
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


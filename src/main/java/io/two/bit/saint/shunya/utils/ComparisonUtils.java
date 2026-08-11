package io.two.bit.saint.shunya.utils;

import java.util.Objects;

public class ComparisonUtils {
    public static <T> boolean equals(T expected, T source) {
        if (Objects.isNull(expected) && Objects.isNull(source)) {
            return true;
        }
        return Objects.nonNull(expected) && Objects.nonNull(source) && expected.equals(source);
    }
}

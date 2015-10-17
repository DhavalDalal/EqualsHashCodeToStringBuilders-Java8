package com.softwareartisan;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static java.util.Objects.*;
import static java.util.stream.Collectors.joining;

public class EqualsHashCodeToStringBuilder<T> {
    private final T left;
    private final String className;
    private List<Function> properties = new ArrayList<>();

    public EqualsHashCodeToStringBuilder(T left) {
        requireNonNull(left, "Left operand must be supplied");
        this.left = left;
        this.className = left.getClass().getSimpleName();
    }

    public <U> EqualsHashCodeToStringBuilder<T> with(Function<T, U> property) {
        requireNonNull(property, String.format("Please specify %s's property", className));
        properties.add(property);
        return this;
    }

    private <U> boolean isEqual(U leftValue, U rightValue) {
        if (areNull(leftValue, rightValue)) {
            return true;
        }
        if (isNull(leftValue) && nonNull(rightValue)) {
            return false;
        }
        return leftValue.equals(rightValue);
    }

    private <U> boolean areNull(U leftValue, U rightValue) {
        return isNull(leftValue) && isNull(rightValue);
    }

    public boolean equals(Object other) {
        if (isNull(other)) {
            return false;
        }

        if (left == other) {
            return true;
        }

        if (left.getClass() != other.getClass()) {
            return false;
        }
        T right = (T) other;

        return properties
                .stream()
                .map(property -> isEqual(property.apply(left), property.apply(right)))
                .allMatch(x -> x == true);
    }

    public int hashCode() {
        return properties
                .stream()
                .map(property -> {
                    final Object value = property.apply(left);
                    return isNull(value) ? 0 : value.hashCode();
                })
                .reduce(1, (hashCode, value) -> 31 * hashCode + value);
    }

    @Override
    public String toString() {
        return properties
                .stream()
                .map(property -> {
                    final Object value = property.apply(left);
                    return isNull(value) ? "null" : value.toString();
                })
                .collect(joining(", ", String.format("%s(", className), ")"));
    }
}

package com.softwareartisan;

public class Range {
    private final Integer from;
    private final Integer to;

    public Range(Integer from, Integer to) {
        this.from = from;
        this.to = to;
    }

    public Integer getFrom() {
        return from;
    }

    public Integer getTo() {
        return to;
    }

    public Boolean contains(Integer number) {
        return from <= number && number <= to;
    }

    public Boolean overlap(Range other) {
        return contains(other.from) || contains(other.to);
    }

    public Boolean inside(Range other) {
        return (from >= other.from) && (to <= other.to);
    }

    public Range resizeTo(Integer newTo) {
        return new Range(from, newTo);
    }

    public Range resizeFrom(Integer newFrom) {
        return new Range(newFrom, to);
    }
}

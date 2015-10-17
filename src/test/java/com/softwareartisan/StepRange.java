package com.softwareartisan;

/**
 * StepRange is just extended from Range for test purposes only.
 * This is not a correct implementation from sub-typing perspective.
 */
public class StepRange extends Range {
    private final Integer step;

    public StepRange(Integer from, Integer to, Integer step) {
        super(from, to);
        this.step = step;
    }

    public Integer getStep() {
        return step;
    }
}

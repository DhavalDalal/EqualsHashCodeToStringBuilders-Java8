package com.softwareartisan;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class EqualsBuilderSpecs {
    @Test
    public void evaluatesEqualValuesToTrue() {
        //Given
        Range left = new Range(5, 10);
        Range right = new Range(5, 10);
        //When
        Boolean equality = new EqualsHashCodeToStringBuilder<>(left)
                .with(Range::getFrom)
                .with(Range::getTo)
                .equals(right);
        //Then
        assertThat(equality, is(true));
    }

    @Test
    public void evaluatesUnequalValuesToFalse() {
        //Given
        Range left = new Range(5, 7);
        Range right = new Range(5, 10);
        //When
        Boolean equality = new EqualsHashCodeToStringBuilder<>(left)
                .with(Range::getFrom)
                .with(Range::getTo)
                .equals(right);
        //Then
        assertThat(equality, is(false));
    }


    @Test
    public void evaluatesNullComparisonsToTrue() {
        //Given
        Range left = new Range(null, 7);
        Range right = new Range(null, 10);

        //When
        Boolean result = new EqualsHashCodeToStringBuilder<>(left)
                .with(Range::getFrom)
                .equals(right);
        //Then
        assertThat(result, is(true));
    }

    @Test
    public void evaluatesNullAndValidValueToFalse() {
        //Given
        Range left = new Range(null, 7);
        Range right = new Range(5, 10);

        //When
        Boolean equality = new EqualsHashCodeToStringBuilder<>(left)
                .with(Range::getFrom)
                .with(Range::getTo)
                .equals(right);

        //Then
        assertThat(equality, is(false));
    }

    @Test
    public void evaluatesValidValueAndNullToFalse() {
        //Given
        Range left = new Range(5, 7);
        Range right = new Range(null, 7);
        //When
        Boolean equality = new EqualsHashCodeToStringBuilder<>(left)
                .with(Range::getFrom)
                .with(Range::getTo)
                .equals(right);

        //Then
        assertThat(equality, is(false));
    }

    @Test
    public void equatesValidObjectToNullObjectAsFalse() {
        //Given
        Range left = new Range(5, 7);
        Object right = null;

        Boolean equality = new EqualsHashCodeToStringBuilder<>(left)
                .with(Range::getFrom)
                .equals(right);

        //Then
        assertThat(equality, is(false));
    }

    @Test
    public void equatesOneTypeToOtherTypeAsFalse() {
        //Given
        Range left = new Range(5, 7);
        StepRange right = new StepRange(5, 7, 2);
        //When
        Boolean equality = new EqualsHashCodeToStringBuilder<>(left)
                .with(Range::getFrom)
                .equals(right);

        //Then
        assertThat(equality, is(false));
    }

    @Test
    public void requiresNonNullLeftOperand() {
        //Given
        Range left = null;
        Range right = null;
        //When
        try {
            new EqualsHashCodeToStringBuilder<>(left)
                    .with(Range::getFrom)
                    .equals(right);
        } catch (NullPointerException e) {
            assertThat(e.getMessage(), is("Left operand must be supplied"));
        }
    }

    @Test
    public void requiresPropertyToEvaluateEquality() {
        //Given
        Range left = new Range(5, 7);
        Range right = new Range(5, 7);

        try {
            new EqualsHashCodeToStringBuilder<>(left)
                    .with(null)
                    .equals(right);
        } catch (NullPointerException e) {
            assertThat(e.getMessage(), is("Please specify Range's property"));
        }
    }

    @Test
    public void shoutsWhenNullIsPassed() {
        //When
        try {
            new EqualsHashCodeToStringBuilder<Range>(null)
                    .with(r -> r.getFrom())
                    .with(r -> r.getTo())
                    .equals(new Range(3, 4));
        } catch (NullPointerException e) {
            assertThat(e.getMessage(), is("Left operand must be supplied"));
        }
    }
}
package com.softwareartisan;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ToStringBuilderSpecs {
    @Test
    public void createsToStringRepresentation() {
        //Given
        Range range = new Range(3, 5);
        //When
        String result = new EqualsHashCodeToStringBuilder<>(range)
                .with(r -> r.getFrom())
                .with(r -> r.getTo())
                .toString();
        //Then
        assertThat(result, is("Range(3, 5)"));
    }

    @Test
    public void classNameForObjectWithNoProperties() {
        //Given
        Range range = new Range(3, 5);
        //When
        String result = new EqualsHashCodeToStringBuilder<>(range)
                .toString();
        //Then
        assertThat(result, is("Range()"));
    }
}

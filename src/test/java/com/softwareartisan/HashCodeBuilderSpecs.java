package com.softwareartisan;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class HashCodeBuilderSpecs {
    @Test
    public void generatesHashCode() {
        //Given
        Range range = new Range(3, 5);
        //When
        int hashCode = new EqualsHashCodeToStringBuilder<>(range)
                .with(r -> r.getFrom())
                .with(r -> r.getTo())
                .hashCode();
        //Then
        assertThat(hashCode, is(1059));
    }


    @Test
    public void hashValue1ForObjectWithNoProperties() {
        //Given
        Range range = new Range(3, 5);
        //When
        int hashCode = new EqualsHashCodeToStringBuilder<>(range)
                .hashCode();
        //Then
        assertThat(hashCode, is(1));
    }


}

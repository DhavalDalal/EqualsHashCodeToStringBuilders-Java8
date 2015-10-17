package examples;

import com.softwareartisan.EqualsHashCodeToStringBuilder;

public class Money {
    public final String currency;
    public final double amount;
    private final EqualsHashCodeToStringBuilder<Money> builder;

    Money(String currency, double amount) {
        this.currency = currency;
        this.amount = amount;
        this.builder = new EqualsHashCodeToStringBuilder<>(this)
                .with(m -> m.currency)
                .with(m -> m.amount);
    }

    public String getCurrency() {
        return currency;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return builder.toString();
    }

    @Override
    public boolean equals(Object that) {
        return builder.equals(that);
    }

    @Override
    public int hashCode() {
        return builder.hashCode();
    }
}


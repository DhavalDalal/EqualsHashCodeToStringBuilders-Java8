package examples;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

public class MoneyClient {
    public static void main(String[] args) {
        Money inr10 = new Money("INR", 10);
        System.out.println("inr10.toString() = " + inr10.toString());

        System.out.println("inr10.equals(inr10) = " + inr10.equals(inr10));
        System.out.println("(inr10 == inr10) ? = " + (inr10 == inr10));

        final Money inr5 = new Money("INR", 5);
        System.out.println("inr10.equals(inr5) = " + inr10.equals(inr5));
        System.out.println("(inr10 == inr5) ? = " + (inr10 == inr5));

        System.out.println("inr10.equals(null) = " + inr10.equals(null));

        final Money gbp5 = new Money("GBP", 5);
        System.out.println("inr10.equals(gbp5) = " + inr10.equals(gbp5));

        Set<Money> set = new HashSet<Money>() {{
            add(inr5);
            add(inr10);
        }};

        System.out.println("set.contains(inr5) = " + set.contains(inr5));
        System.out.println("set.contains(gbp5) = " + set.contains(gbp5));

        Map<Money, Integer> wallet = new HashMap<Money, Integer>() {{
            put(inr5, 10);
            put(inr10, 20);
        }};


        AtomicReference<Double> total = new AtomicReference(0d);
        wallet.forEach((value, howMany) ->
                        total.getAndUpdate(old -> old + value.getAmount() * howMany)
        );

        System.out.println("bag.containsKey(inr5) = " + wallet.containsKey(inr5));
        System.out.println("bag.containsKey(gbp5) = " + wallet.containsKey(gbp5));
        System.out.println("total amount in wallet = " + total);
    }
}

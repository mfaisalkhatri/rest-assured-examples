package restfulecommerce.tutorial.data;

import net.datafaker.Faker;

public class OrderDataBuilder {

    public static OrderData getOrderData () {

        Faker faker = new Faker ();
        int productAmount = (faker.number ()
            .numberBetween (1, 1999));
        int qty = faker.number ()
            .numberBetween (1, 10);
        int grossAmt = qty * productAmount;
        int taxAmt = (int) (grossAmt * 0.10);
        int totalAmt = grossAmt + taxAmt;

        return OrderData.builder ()
            .userId (String.valueOf (faker.number ()
                .numberBetween (301, 499)))
            .productId (String.valueOf (faker.number ()
                .numberBetween (201, 533)))
            .productName (faker.commerce ()
                .productName ())
            .productAmount (productAmount)
            .qty (qty)
            .taxAmt (taxAmt)
            .totalAmt (totalAmt)
            .build ();
    }
}
package data.restfulecommerce;

import net.datafaker.Faker;

public class OrderDataBuilder {

    private static final Faker FAKER = new Faker ();

    public static OrderData getOrderData () {
        return OrderData.builder ()
            .userId (String.valueOf (FAKER.number ()
                .numberBetween (200, 399)))
            .productId (String.valueOf (FAKER.number ()
                .numberBetween (1, 2000)))
            .productName (FAKER.internet ()
                .domainName ())
            .productAmount (FAKER.number ()
                .numberBetween (1, 499))
            .qty (FAKER.number ()
                .numberBetween (1, 15))
            .taxAmt (FAKER.number ()
                .numberBetween (1, 399))
            .totalAmt (FAKER.number ()
                .numberBetween (100, 1999))
            .build ();
    }

    public static OrderData getPartialOrder () {
        return OrderData.builder ()
            .productName (FAKER.commerce ()
                .productName ())
            .productAmount (FAKER.number ()
                .numberBetween (188, 559))
            .qty (FAKER.number ()
                .numberBetween (1, 15))
            .build ();
    }

}

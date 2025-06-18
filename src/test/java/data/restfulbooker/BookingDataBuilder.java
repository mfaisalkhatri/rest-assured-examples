package data.restfulbooker;

import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import net.datafaker.Faker;
/**
 * Created By Faisal Khatri on 19-02-2022
 */
public class BookingDataBuilder {

    private static final Faker FAKER = new Faker ();

    public static BookingData getBookingData () {
        return BookingData.builder ()
            .firstname (FAKER.name ()
                .firstName ())
            .lastname (FAKER.name ()
                .lastName ())
            .totalprice (FAKER.number ()
                .numberBetween (1, 2000))
            .depositpaid (true)
            .bookingdates (BookingDates.builder ()
                .checkin(FAKER.timeAndDate()
                    .past(20, TimeUnit.DAYS,"yyyy-MM-dd"))
                .checkout(FAKER.timeAndDate()
                    .future(5, TimeUnit.DAYS, "yyyy-MM-dd")).build())
            .additionalneeds ("Breakfast")
            .build ();

    }

    public static PartialBookingData getPartialBookingData () {
        return PartialBookingData.builder ()
            .firstname (FAKER.name ()
                .firstName ())
            .totalprice (FAKER.number ()
                .numberBetween (100, 5000))
            .build ();
    }
}
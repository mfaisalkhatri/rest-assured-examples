package data;

import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

/**
 * Created By Faisal Khatri on 19-02-2022
 */
public class BookingDataBuilder {

    public BookingData bookingDataBuilder () {
        Faker faker = Faker.instance ();
        SimpleDateFormat formatter = new SimpleDateFormat ("YYYY-MM-dd");
        return BookingData.builder ()
                .firstname (faker.name ()
                        .firstName ())
                .lastname (faker.name ()
                        .lastName ())
                .totalprice (faker.number ()
                        .numberBetween (1, 2000))
                .depositpaid (true)
                .bookingdates (BookingDates.builder ()
                        .checkin (formatter.format (faker.date ()
                                .past (20, TimeUnit.DAYS)))
                        .checkout (formatter.format (faker.date ()
                                .future (5, TimeUnit.DAYS)))
                        .build ())
                .additionalneeds ("Breakfast")
                .build ();

    }

    public PartialBookingData partialBookingBuilder () {
        Faker faker = Faker.instance ();
        return PartialBookingData.builder ()
                .firstname (faker.name ()
                        .firstName ())
                .totalprice (faker.number ()
                        .numberBetween (100, 5000))
                .build ();
    }
}
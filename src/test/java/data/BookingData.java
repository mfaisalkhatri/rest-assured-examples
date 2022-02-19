package data;

import lombok.Builder;
import lombok.Data;

/**
 * Created By Faisal Khatri on 18-02-2022
 */
@Data
@Builder
public class BookingData {
    private String firstname;
    private String lastname;
    private int totalprice;
    private  boolean depositpaid;
    private  BookingDates bookingdates;
    private  String additionalneeds;
}
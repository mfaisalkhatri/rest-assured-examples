package data.restfulbooker;

import lombok.Builder;
import lombok.Getter;

/**
 * Created By Faisal Khatri on 18-02-2022
 */
@Getter
@Builder
public class BookingData {
    private String       firstname;
    private String       lastname;
    private int          totalprice;
    private boolean      depositpaid;
    private BookingDates bookingdates;
    private String       additionalneeds;
}
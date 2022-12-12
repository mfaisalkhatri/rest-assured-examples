package data.restfulbooker;

import lombok.Builder;
import lombok.Getter;

/**
 * Created By Faisal Khatri on 18-02-2022
 */
@Getter
@Builder
public class BookingDates {
    private String checkin;
    private String checkout;
}
package data.restfulbooker;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.Date;

/**
 * Created By Faisal Khatri on 18-02-2022
 */
@Getter
@Builder
public class BookingDates {
   private  String checkin;
   private String checkout;
}
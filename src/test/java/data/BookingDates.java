package data;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * Created By Faisal Khatri on 18-02-2022
 */
@Data
@Builder
public class BookingDates {
   private  String checkin;
   private String checkout;
}
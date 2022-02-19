package data;

import lombok.Builder;
import lombok.Data;

/**
 * Created By Faisal Khatri on 19-02-2022
 */
@Data
@Builder
public class PartialBookingData {
    String firstname;
    int    totalprice;
}
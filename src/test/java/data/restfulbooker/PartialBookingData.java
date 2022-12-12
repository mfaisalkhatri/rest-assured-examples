package data.restfulbooker;

import lombok.Builder;
import lombok.Getter;

/**
 * Created By Faisal Khatri on 19-02-2022
 */
@Getter
@Builder
public class PartialBookingData {
    String firstname;
    int    totalprice;
}
package data;

import lombok.Builder;
import lombok.Data;

/**
 * Created By Faisal Khatri on 20-11-2021
 */
@Data
@Builder
public class UserData {

    private String name;
    private String job;

}
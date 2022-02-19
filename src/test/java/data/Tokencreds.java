package data;

import lombok.Builder;
import lombok.Data;

/**
 * Created By Faisal Khatri on 19-02-2022
 */
@Builder
@Data
public class Tokencreds {

    private String username;
    private String password;

}
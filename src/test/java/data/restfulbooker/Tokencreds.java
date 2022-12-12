package data.restfulbooker;

import lombok.Builder;
import lombok.Getter;

/**
 * Created By Faisal Khatri on 19-02-2022
 */
@Builder
@Getter
public class Tokencreds {

    private String username;
    private String password;

}
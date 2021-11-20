package io.github.mfaisalkhatri;

import lombok.Data;
/**
 * Created By Faisal Khatri on 19-11-2021
 */
@Data
public class AuthenticationPojo {

    private String email;
    private String password;

    /**
     * Created By Faisal Khatri on 19-11-2021
     *
     * @param email
     * @param password
     */
    public AuthenticationPojo (String email, String password) {
        this.email = email;
        this.password = password;
    }

}
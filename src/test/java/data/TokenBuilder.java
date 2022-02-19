package data;

/**
 * Created By Faisal Khatri on 19-02-2022
 */
public class TokenBuilder {

    public Tokencreds tokenBuilder () {
        return Tokencreds.builder ()
                .username ("admin")
                .password ("password123")
                .build ();
    }

}
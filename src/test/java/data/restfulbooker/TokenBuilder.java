package data.restfulbooker;

/**
 * Created By Faisal Khatri on 19-02-2022
 */
public class TokenBuilder {

    private static final String USER_NAME = System.getenv ("rbUsername");
    private static final String PASSWORD = System.getenv ("rbPassword");

    public static Tokencreds getToken () {

        return Tokencreds.builder ()
            .username (USER_NAME)
            .password (PASSWORD)
            .build ();
    }
}
package data.restfulecommerce;

public class AuthDataBuilder {

    private static String username = System.getenv ("restful_ecommerce_username");
    private static String password = System.getenv ("restful_ecommerce_password");

    public static AuthData getAuthData () {
        return AuthData.builder ()
            .username (username)
            .password (password)
            .build ();
    }
}
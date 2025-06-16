package data.restfulecommerce;

public class AuthDataBuilder {

    private static final String ECOMM_USER_NAME = System.getenv ("restful_ecommerce_username");
    private static final String ECOMM_PASSWORD = System.getenv ("restful_ecommerce_password");

    public static AuthData getAuthData () {
        return AuthData.builder ()
            .username (ECOMM_USER_NAME)
            .password (ECOMM_PASSWORD)
            .build ();
    }
}
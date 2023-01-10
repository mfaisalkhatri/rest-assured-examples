package authentication;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * @author Faisal Khatri
 * @since 1/10/2023
 **/
public class AuthenticationTests {

    private static RequestSpecBuilder    requestSpecBuilder;
    private static ResponseSpecBuilder   responseSpecBuilder;
    private static ResponseSpecification responseSpecification;
    private static RequestSpecification  requestSpecification;

    @BeforeClass
    public void setupSpecBuilder () {
        requestSpecBuilder = new RequestSpecBuilder ().addFilter (new RequestLoggingFilter ())
            .addFilter (new ResponseLoggingFilter ());
        responseSpecBuilder = new ResponseSpecBuilder ().expectStatusCode (200);

        responseSpecification = responseSpecBuilder.build ();
        requestSpecification = requestSpecBuilder.build ();

    }

    @Test
    public void testAPIKeyAuthentication () {
        int id = 2172797;
        given ().when ()
            .spec (requestSpecification)
            .queryParam ("apiKey", "e4c66f5be087d70d2ba00f3c84a067a1")
            .queryParam ("id", id)
            .get ("https://api.openweathermap.org/data/2.5/weather")
            .then ()
            .spec (responseSpecification)
            .body ("id", equalTo (id));
    }

    @Test
    public void testBasicAuth () {
        given ().spec (requestSpecification)
            .auth ()
            .basic ("postman", "password")
            .get ("https://postman-echo.com/basic-auth")
            .then ()
            .spec (responseSpecification)
            .body ("$", hasKey ("authenticated"))
            .body ("authenticated", equalTo (true));
    }

    @Test
    public void testPreemptiveAuth() {
        given ().spec (requestSpecification)
            .auth ()
            .preemptive ()
            .basic ("postman", "password")
            .get ("https://postman-echo.com/basic-auth")
            .then ()
            .spec (responseSpecification)
            .body ("$", hasKey ("authenticated"))
            .body ("authenticated", equalTo (true));
    }

    @Test
    public void testDigestAuth() {
        given ().spec (requestSpecification)
            .auth ()
            .digest ("postman", "password")
            .get ("https://postman-echo.com/basic-auth")
            .then ()
            .spec (responseSpecification)
            .body ("$", hasKey ("authenticated"))
            .body ("authenticated", equalTo (true));
    }
}

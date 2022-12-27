package in.reqres;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

/**
 * @author Faisal Khatri
 * @since 12/27/2022
 **/
public class TestRequestWithConfig extends SetupConfiguration {
    private static final String URL = "https://reqres.in/api/users/";

    @Test
    public void testGetRequest () {
        String responseBody = given ().when ()
            .post ("https://apimocha.com/checkt/rover")
            .then ()
            .statusCode (200)
            .and ()
            .assertThat ()
            .body ("type", equalTo ("dog"))
            .and ()
            .extract ()
            .response ()
            .asString ();

        System.out.println (responseBody);

    }

}

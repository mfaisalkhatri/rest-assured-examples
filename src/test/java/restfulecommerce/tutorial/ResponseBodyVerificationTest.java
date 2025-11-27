package restfulecommerce.tutorial;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

import org.testng.annotations.Test;

public class ResponseBodyVerificationTest {

    @Test
    public void testResponseWithMatchers () {

        given ().when ()
            .queryParam ("id", 1)
            .log ()
            .all ()
            .get ("http://localhost:3004/getOrder")
            .then ()
            .log ()
            .all ()
            .statusCode (200)
            .and ()
            .assertThat ()
            .body ("message", equalTo ("Order found!!"))
            .body ("orders[0].id", equalTo (1));
    }
}

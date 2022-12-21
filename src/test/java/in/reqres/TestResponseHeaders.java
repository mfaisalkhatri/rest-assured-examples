package in.reqres;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

/**
 * @author Faisal Khatri
 * @since 12/21/2022
 **/
public class TestResponseHeaders extends SetupConfig {

    @Test
    public void responseHeadersTest () {
        given ().when ()
            .with ()
            .queryParam ("page", 2)
            .get ("/api/users")
            .then ()
            .statusCode (200)
            .and ()
            .assertThat ()
            .body ("page", equalTo(2))
            .header ("Content-Type", equalTo ("application/json; charset=utf-8"))
            .header ("Content-Encoding", equalTo ("gzip"));
    }
}

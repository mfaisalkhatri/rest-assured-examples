package in.reqres;

import static io.restassured.RestAssured.expect;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

public class TestExpectations {

    @Test
    public void testUsingExpect () {
        expect ().that ()
            .header ("x-api-key", "reqres-free-v1")
            .when ()
            .get ("https://reqres.in/api/users/2")
            .then ()
            .log ()
            .all ()
            .statusCode (200);
    }

    @Test
    public void testBodyUsingExpect () {
        expect ().header ("x-api-key", "reqres-free-v1")
            .body ("data.email", equalTo ("janet.weaver@reqres.in"))
            .body ("data.id", equalTo(2))
            .when ()
            .get ("https://reqres.in/api/users/2")
            .then ()
            .log ()
            .all ()
            .statusCode (200);
    }

}

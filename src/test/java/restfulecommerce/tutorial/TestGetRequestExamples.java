package restfulecommerce.tutorial;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

public class TestGetRequestExamples {

    @Test
    public void testGetAllOrders () {
        given ().when ()
            .get ("")
            .then ()
            .statusCode (200);
    }

    @Test
    public void testGetOrderWithQueryParam () {
        given ().when ()
            .log ()
            .all ()
            .queryParam ("id", 1)
            .get ("http://localhost:3004/getOrder")
            .then ()
            .log ()
            .all ()
            .statusCode (200);
    }
}

package restfulecommerce.tutorial;

import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

public class TestResponseLogging {

    @Test
    public void testLogResponseStatus () {

        final String order = "[{\"user_id\": \"1\"," + "\"product_id\": \"1\"," + "\"product_name\": \"iPhone\"," + "\"product_amount\": 500.00," + "\"qty\": 1," + "\"tax_amt\": 5.99," + "\"total_amt\": 505.99}]";

        given ().contentType (ContentType.JSON)
            .when ()
            .body (order)
            .post ("http://localhost:3004/addOrder")
            .then ()
            .log ()
            .status ();
    }

    @Test
    public void testLogResponseHeaders () {

        given ().when ()
            .queryParam ("id", 1)
            .get ("http://localhost:3004/getOrder")
            .then ()
            .log ()
            .headers ();
    }

    @Test
    public void testLogResponseBody () {

        given ().when ()
            .queryParam ("id", 1)
            .get ("http://localhost:3004/getOrder")
            .then ()
            .log ()
            .body ();
    }

    @Test
    public void testLogResponseAll () {

        given ().when ()
            .queryParam ("id", 1)
            .get ("http://localhost:3004/getOrder")
            .then ()
            .log ()
            .all ();
    }

}

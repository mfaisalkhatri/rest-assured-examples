package restfulecommerce.tutorial;

import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

public class TestRequestLogging {

    @Test
    public void testWithoutLogs () {
        given ().when ()
            .get ("http://localhost:3004/getAllOrders")
            .then ()
            .statusCode (200);
    }

    @Test
    public void testLogRequestURI () {

        final String order = "[{\"user_id\": \"1\"," + "\"product_id\": \"1\"," + "\"product_name\": \"iPhone\"," + "\"product_amount\": 500.00," + "\"qty\": 1," + "\"tax_amt\": 5.99," + "\"total_amt\": 505.99}]";

        given ().contentType (ContentType.JSON)
            .when ()
            .log ()
            .uri ()
            .body (order)
            .post ("http://localhost:3004/addOrder")
            .then ()
            .statusCode (201);
    }

    @Test
    public void testLogRequestParams () {

        given ().when ()
            .log ()
            .params ()
            .queryParam ("id", 1)
            .get ("http://localhost:3004/getOrder")
            .then ()
            .statusCode (200);
    }

    @Test
    public void testLogRequestBody () {

        final String order = "[{\"user_id\": \"1\"," + "\"product_id\": \"1\"," + "\"product_name\": \"iPhone\"," + "\"product_amount\": 500.00," + "\"qty\": 1," + "\"tax_amt\": 5.99," + "\"total_amt\": 505.99}]";

        given ().contentType (ContentType.JSON)
            .when ()
            .log ()
            .body ()
            .body (order)
            .post ("http://localhost:3004/addOrder")
            .then ()
            .statusCode (201);
    }

    @Test
    public void testLogRequestMethod () {

        final String order = "[{\"user_id\": \"1\"," + "\"product_id\": \"1\"," + "\"product_name\": \"iPhone\"," + "\"product_amount\": 500.00," + "\"qty\": 1," + "\"tax_amt\": 5.99," + "\"total_amt\": 505.99}]";

        given ().contentType (ContentType.JSON)
            .when ()
            .log ()
            .method ()
            .body (order)
            .post ("http://localhost:3004/addOrder")
            .then ()
            .statusCode (201);
    }

    @Test
    public void testLogRequestHeaders () {

        given ().when ()
            .log ()
            .headers ()
            .queryParam ("id", 1)
            .get ("http://localhost:3004/getOrder")
            .then ()
            .statusCode (200);
    }

    @Test
    public void testLogRequestAll () {

        final String order = "[{\"user_id\": \"1\"," + "\"product_id\": \"1\"," + "\"product_name\": \"iPhone\"," + "\"product_amount\": 500.00," + "\"qty\": 1," + "\"tax_amt\": 5.99," + "\"total_amt\": 505.99}]";

        given ().contentType (ContentType.JSON)
            .when ()
            .log ()
            .all ()
            .body (order)
            .post ("http://localhost:3004/addOrder")
            .then ()
            .statusCode (201);
    }
}
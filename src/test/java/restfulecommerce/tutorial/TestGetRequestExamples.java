package restfulecommerce.tutorial;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;
import java.util.Map;

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
            .statusCode (200)
            .and ()
            .body ("orders[0].id", equalTo (1));
    }

    @Test
    public void testGetOrderWithMultipleQueryParam () {
        given ().when ()
            .log ()
            .all ()
            .queryParams ("id", 1, "user_id", "1", "product_id", "1")
            .get ("http://localhost:3004/getOrder")
            .then ()
            .log ()
            .all ()
            .statusCode (200)
            .and ()
            .body ("orders[0].id", equalTo (1));
    }

    @Test
    public void testGetOrderWithMultipleQueryParameters () {
        given ().when ()
            .log ()
            .all ()
            .queryParam ("id", 1)
            .queryParam ("user_id", "1")
            .queryParam ("product_id", "1")
            .get ("http://localhost:3004/getOrder")
            .then ()
            .log ()
            .all ()
            .statusCode (200)
            .and ()
            .body ("orders[0].id", equalTo (1));
    }

    @Test
    public void testGetOrderWithMultipleQueryParamWithMap () {
        Map<String, Object> queryParams = new HashMap<> ();
        queryParams.put ("id", 1);
        queryParams.put ("user_id", "1");
        queryParams.put ("product_id", "1");

        given ().when ()
            .log ()
            .all ()
            .queryParams (queryParams)
            .get ("http://localhost:3004/getOrder")
            .then ()
            .log ()
            .all ()
            .statusCode (200)
            .and ()
            .body ("orders[0].id", equalTo (1));
    }

    @Test
    public void testGetBookingWithPathParam () {
        given ().when ()
            .log ()
            .all ()
            .pathParam ("id", 3)
            .get ("https://restful-booker.herokuapp.com/booking/{id}")
            .then ()
            .log ()
            .all ()
            .statusCode (200);
    }

}

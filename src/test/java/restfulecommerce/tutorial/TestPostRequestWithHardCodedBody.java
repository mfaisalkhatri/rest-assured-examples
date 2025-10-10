package restfulecommerce.tutorial;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

public class TestPostRequestWithHardCodedBody {

    @Test
    public void testCreateOrder () {

        String order = "[{\"user_id\": \"1\"," + "\"product_id\": \"1\"," + "\"product_name\": \"iPhone\"," + "\"product_amount\": 500.00," + "\"qty\": 1," + "\"tax_amt\": 5.99," + "\"total_amt\": 505.99}]";

        given ().contentType (ContentType.JSON)
            .when ()
            .log ()
            .all ()
            .body (order)
            .post ("http://localhost:3004/addOrder")
            .then ()
            .log ()
            .all ()
            .statusCode (201)
            .and ()
            .assertThat ()
            .body ("message", equalTo ("Orders added successfully!"));
    }
}
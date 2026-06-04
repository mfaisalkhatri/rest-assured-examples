package restful.ecommerce;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.List;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

public class TestPostWithRecords {
    public record OrderData(String user_id, String product_id, String product_name, int product_amount, int qty,
                            int tax_amt, int total_amt) {
    }

    private static final String URL = "http://localhost:3004";

    @Test
    public void testCreateUser () {

        final OrderData orderData = new OrderData ("USR005", "PRD107", "Samsung inch HD Monitor", 17000, 1, 3000,
            20000);

        final List<OrderData> orderList = new ArrayList<> ();
        orderList.add (orderData);

        given ().contentType (ContentType.JSON)
            .body (orderList)
            .when ()
            .log ()
            .all ()
            .post (URL + "/addOrder")
            .then ()
            .statusCode (201)
            .and ()
            .assertThat ()
            .body ("message", equalTo ("Orders added successfully!"));
    }
}
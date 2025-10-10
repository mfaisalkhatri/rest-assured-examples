package restfulecommerce.tutorial;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

import java.util.ArrayList;
import java.util.List;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import restfulecommerce.tutorial.data.Orders;

public class TestPostRequestWithPOJO {

    @Test
    public void testCreateOrder () {
        Orders orderOne = new Orders ("3", "4", "Google Pixel 9 Pro", 659.87, 1, 65.97, 725.84);
        Orders[] orders = { orderOne };

        given ().contentType (ContentType.JSON)
            .when ()
            .log ()
            .all ()
            .body (orders)
            .post ("http://localhost:3004/addOrder")
            .then ()
            .log ()
            .all ()
            .statusCode (201)
            .and ()
            .assertThat ()
            .body ("message", equalTo ("Orders added successfully!"));

    }

    @Test
    public void testCreateOrderWithList () {
        Orders orderOne = new Orders ("3", "4", "Google Pixel 10 Pro", 759.87, 1, 75.97, 835.84);
        List<Orders> orders = new ArrayList<> ();
        orders.add (orderOne);

        given ().contentType (ContentType.JSON)
            .when ()
            .log ()
            .all ()
            .body (orders)
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
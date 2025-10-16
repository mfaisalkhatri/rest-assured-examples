package restfulecommerce.tutorial;

import static data.restfulecommerce.OrderDataBuilder.getOrderData;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.List;

import data.restfulecommerce.OrderData;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

public class TestPostRequestWithBuilderPattern {

    @Test
    public void testCreateOrders () {
        List<OrderData> orderDataList = new ArrayList<> ();
        for (int i = 0; i < 4; i++) {
            orderDataList.add (getOrderData ());
        }

        given ().contentType (ContentType.JSON)
            .when ()
            .log ()
            .all ()
            .body (orderDataList)
            .post ("http://localhost:3004/addOrder")
            .then ()
            .statusCode (201)
            .and ()
            .assertThat ()
            .body ("message", equalTo ("Orders added successfully!"));

    }

}


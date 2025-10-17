package restfulecommerce.tutorial;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static restfulecommerce.tutorial.data.OrderDataBuilder.getOrderData;

import java.util.ArrayList;
import java.util.List;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import restfulecommerce.tutorial.data.OrderData;

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
            .body ("message", equalTo ("Orders added successfully!"))
            .and ()
            .assertThat ()
            .body ("orders[1].user_id", equalTo (orderDataList.get (1)
                .getUserId ()), "orders[3].total_amt", equalTo (orderDataList.get (3)
                .getTotalAmt ()));
    }
}
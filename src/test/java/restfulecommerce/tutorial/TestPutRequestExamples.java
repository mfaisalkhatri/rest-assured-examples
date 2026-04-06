package restfulecommerce.tutorial;

import static data.restfulecommerce.OrderDataBuilder.getOrderData;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import data.restfulecommerce.OrderData;
import io.restassured.http.ContentType;
import org.json.JSONObject;
import org.testng.annotations.Test;

public class TestPutRequestExamples {

    private String token;

    @Test
    public void testUpdateOrder () {

        int orderId = 1;
        OrderData updatedOrder = getOrderData ();

        String responseBody = given ().contentType (ContentType.JSON)
            .header ("Authorization", token)
            .when ()
            .log ()
            .all ()
            .body (updatedOrder)
            .put ("http://localhost:3004/updateOrder/" + orderId)
            .then ()
            .log ()
            .all ()
            .statusCode (200)
            .and ()
            .assertThat ()
            .body ("message", equalTo ("Order updated successfully!"))
            .extract ()
            .response ()
            .asString ();

        JSONObject responseObject = new JSONObject (responseBody);
        JSONObject orderObject = responseObject.getJSONObject ("order");

        assertThat (orderObject.get ("id"), equalTo (orderId));
        assertThat (orderObject.get ("user_id"), equalTo (updatedOrder.getUserId ()));
        assertThat (orderObject.get ("product_id"), equalTo (updatedOrder.getProductId ()));
        assertThat (orderObject.get ("product_name"), equalTo (updatedOrder.getProductName ()));
        assertThat (orderObject.get ("product_amount"), equalTo (updatedOrder.getProductAmount ()));
        assertThat (orderObject.get ("qty"), equalTo (updatedOrder.getQty ()));
        assertThat (orderObject.get ("tax_amt"), equalTo (updatedOrder.getTaxAmt ()));
        assertThat (orderObject.get ("total_amt"), equalTo (updatedOrder.getTotalAmt ()));
    }

    @Test
    public void testTokenGeneration () {
        String requestBody = """
            {
              "username": "admin",
              "password": "secretPass123"
            }""";

        token = given ().contentType (ContentType.JSON)
            .when ()
            .body (requestBody)
            .post ("http://localhost:3004/auth")
            .then ()
            .statusCode (201)
            .and ()
            .body ("token", notNullValue ())
            .extract ()
            .path ("token");

    }
}
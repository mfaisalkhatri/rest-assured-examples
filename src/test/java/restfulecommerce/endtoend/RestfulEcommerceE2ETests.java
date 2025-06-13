package restfulecommerce.endtoend;

import static data.restfulecommerce.OrderDataBuilder.getOrderData;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import java.util.ArrayList;
import java.util.List;

import data.restfulecommerce.OrderData;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class RestfulEcommerceE2ETests extends BaseTest {

    private List<OrderData> orderList;
    private int             orderId;

    @BeforeTest
    public void testSetup () {
        this.orderList = new ArrayList<> ();
    }

    @Test
    public void testCreateOrder () {

        int totalOrder = 6;
        for (int i = 0; i < totalOrder; i++) {
            orderList.add (getOrderData ());
        }

        String responseBody = given ().body (orderList)
            .when ()
            .post ("/addOrder")
            .then ()
            .statusCode (201)
            .and ()
            .assertThat ()
            .body ("message", equalTo ("Orders added successfully!"))
            .extract ()
            .body ()
            .asString ();

        JSONObject responseObject = new JSONObject (responseBody);
        JSONArray orderArray = responseObject.getJSONArray ("orders");

        assertThat (orderArray.getJSONObject (0)
            .get ("id"), notNullValue ());
        orderId = orderArray.getJSONObject (0)
            .getInt ("id");

        OrderData orderData = orderList.get (0);

        assertThat (orderArray.getJSONObject (0)
            .get ("user_id"), equalTo (orderData.getUserId ()));
        assertThat (orderArray.getJSONObject (0)
            .get ("product_id"), equalTo (orderData.getProductId ()));
        assertThat (orderArray.getJSONObject (0)
            .get ("product_name"), equalTo (orderData.getProductName ()));
        assertThat (orderArray.getJSONObject (0)
            .get ("product_amount"), equalTo (orderData.getProductAmount ()));
        assertThat (orderArray.getJSONObject (0)
            .get ("qty"), equalTo (orderData.getQty ()));
        assertThat (orderArray.getJSONObject (0)
            .get ("tax_amt"), equalTo (orderData.getTaxAmt ()));
        assertThat (orderArray.getJSONObject (0)
            .get ("total_amt"), equalTo (orderData.getTotalAmt ()));
    }

    @Test
    public void testGetOrder () {
        String responseBody = given ().when ()
            .queryParam ("id", orderId)
            .get ("/getOrder")
            .then ()
            .statusCode (200)
            .and ()
            .assertThat ()
            .body ("message", equalTo ("Order found!!"))
            .extract ()
            .body ()
            .asString ();

        JSONObject responseObject = new JSONObject (responseBody);
        JSONArray orderArray = responseObject.getJSONArray ("orders");

        assertThat (orderArray.getJSONObject (0)
            .get ("id"), equalTo (orderId));
    }

    @Test
    public void testUpdateOrder() {
        OrderData updatedOrder = getOrderData ();
        String responseBody = given ().when ()
            .pathParam ("id", orderId)
            .body (updatedOrder)
            .put ("/updateOrder")
            .then ()
            .statusCode (200)
            .and ()
            .assertThat ()
            .body ("message", equalTo ("Order updated successfully!"))
            .extract ()
            .body ()
            .asString ();

        JSONObject responseObject = new JSONObject (responseBody);
        JSONArray orderArray = responseObject.getJSONArray ("orders");

        assertThat (orderArray.getJSONObject (0)
            .get ("id"), equalTo (orderId));
        assertThat (orderArray.getJSONObject (0)
            .get ("user_id"), equalTo (updatedOrder.getUserId ()));
        assertThat (orderArray.getJSONObject (0)
            .get ("product_id"), equalTo (updatedOrder.getProductId ()));
        assertThat (orderArray.getJSONObject (0)
            .get ("product_name"), equalTo (updatedOrder.getProductName ()));
        assertThat (orderArray.getJSONObject (0)
            .get ("product_amount"), equalTo (updatedOrder.getProductAmount ()));
        assertThat (orderArray.getJSONObject (0)
            .get ("qty"), equalTo (updatedOrder.getQty ()));
        assertThat (orderArray.getJSONObject (0)
            .get ("tax_amt"), equalTo (updatedOrder.getTaxAmt ()));
        assertThat (orderArray.getJSONObject (0)
            .get ("total_amt"), equalTo (updatedOrder.getTotalAmt ()));
    }
}

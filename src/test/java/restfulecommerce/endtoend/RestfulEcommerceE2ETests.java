package restfulecommerce.endtoend;

import static data.restfulecommerce.AuthDataBuilder.getAuthData;
import static data.restfulecommerce.OrderDataBuilder.getOrderData;
import static data.restfulecommerce.OrderDataBuilder.getPartialOrder;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import java.util.ArrayList;
import java.util.List;

import data.restfulecommerce.AuthData;
import data.restfulecommerce.OrderData;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class RestfulEcommerceE2ETests extends BaseTest {

    private       int             orderId;
    private       List<OrderData> orderList;
    private       String          token;
    private final int             totalOrders = 6;

    @Test
    public void testCreateOrder () {

        for (int i = 0; i < totalOrders; i++) {
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
    public void testCreateToken () {
        AuthData authData = getAuthData ();
        token = given ().when ()
            .body (authData)
            .post ("/auth")
            .then ()
            .assertThat ()
            .statusCode (201)
            .and ()
            .assertThat ()
            .body ("message", equalTo ("Authentication Successful!"))
            .body ("token", notNullValue ())
            .extract ()
            .path ("token");
    }

    @Test
    public void testDeleteAllOrders () {
        given ().when ()
            .header ("Authorization", token)
            .delete ("/deleteAllOrders")
            .then ()
            .statusCode (204);
    }

    @Test
    public void testDeleteOrder () {
        given ().when ()
            .header ("Authorization", token)
            .delete ("/deleteOrder/" + orderId)
            .then ()
            .statusCode (204);
    }

    @Test
    public void testGetAllOrders () {

        String responseBody = given ().when ()
            .queryParam ("id", orderId)
            .get ("/getAllOrders")
            .then ()
            .statusCode (200)
            .and ()
            .assertThat ()
            .body ("message", equalTo ("Orders fetched successfully!"))
            .extract ()
            .body ()
            .asString ();

        JSONObject responseObject = new JSONObject (responseBody);
        JSONArray orderArray = responseObject.getJSONArray ("orders");

        assertThat (orderArray.getJSONObject (0)
            .get ("id"), equalTo (orderId));

        assertThat (orderArray.length (), equalTo (totalOrders));

    }

    @Test
    public void testGetDeletedOrder () {
        given ().when ()
            .queryParam ("id", orderId)
            .get ("/getOrder/")
            .then ()
            .statusCode (404);
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

    @BeforeTest
    public void testSetup () {
        this.orderList = new ArrayList<> ();
    }

    @Test
    public void testUpdateOrder () {
        OrderData updatedOrder = getOrderData ();
        String responseBody = given ().header ("Authorization", token)
            .when ()
            .body (updatedOrder)
            .put ("/updateOrder/" + orderId)
            .then ()
            .statusCode (200)
            .and ()
            .assertThat ()
            .body ("message", equalTo ("Order updated successfully!"))
            .extract ()
            .body ()
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
    public void updatePartialOrderTest () {
        OrderData updatedOrder = getPartialOrder ();
        String responseBody = given ().when ()
            .header ("Authorization", token)
            .body (updatedOrder)
            .patch ("/partialUpdateOrder/" + orderId)
            .then ()
            .statusCode (200)
            .and ()
            .assertThat ()
            .body ("message", equalTo ("Order updated successfully!"))
            .extract ()
            .body ()
            .asString ();

        JSONObject responseObject = new JSONObject (responseBody);
        JSONObject orderObject = responseObject.getJSONObject ("order");

        assertThat (orderObject.get ("id"), equalTo (orderId));
        assertThat (orderObject.get ("product_name"), equalTo (updatedOrder.getProductName ()));
        assertThat (orderObject.get ("product_amount"), equalTo (updatedOrder.getProductAmount ()));
        assertThat (orderObject.get ("qty"), equalTo (updatedOrder.getQty ()));
    }
}
package restfulecommerce.tutorial;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.skyscreamer.jsonassert.JSONAssert;
import org.testng.annotations.Test;
import restfulecommerce.tutorial.pojo.OrderResponse;

public class ResponseBodyVerificationTest {

    @Test
    public void testResponseWithMatchers () {

        given ().when ()
            .queryParam ("id", 1)
            .log ()
            .all ()
            .get ("http://localhost:3004/getOrder")
            .then ()
            .log ()
            .all ()
            .statusCode (200)
            .and ()
            .assertThat ()
            .body ("message", equalTo ("Order found!!"))
            .body ("orders[0].id", equalTo (1));
    }

    @Test
    public void testExtractResponseAndVerify () {
        Response response = given ().when ()
            .log ()
            .all ()
            .get ("http://localhost:3004/getAllOrders")
            .then ()
            .log ()
            .all ()
            .statusCode (200)
            .extract ()
            .response ();

        String orderMessage = response.jsonPath ()
            .getString ("message");
        assertEquals (orderMessage, "Orders fetched successfully!");
        String orderTwoUserId = response.jsonPath ()
            .getString ("orders[1].user_id");
        assertEquals (orderTwoUserId, "484");

        JsonPath jsonPath = new JsonPath (response.asString ());
        assertEquals (jsonPath.getString ("orders[0].product_name"), "Small Marble Shoes");
    }

    @Test
    public void testExtractResponseWithGson () {
        String response = given ().when ()
            .log ()
            .all ()
            .get ("http://localhost:3004/getAllOrders")
            .then ()
            .log ()
            .all ()
            .statusCode (200)
            .extract ()
            .response ()
            .asString ();

        Gson gson = new Gson ();
        JsonObject responseObject = gson.fromJson (response, JsonObject.class);
        String orderText = responseObject.get ("message")
            .getAsString ();
        assertEquals (orderText, "Orders fetched successfully!");

        JsonArray orderArray = responseObject.getAsJsonArray ("orders");
        String orderThreeProductName = orderArray.get (2)
            .getAsJsonObject ()
            .get ("product_name")
            .getAsString ();
        assertEquals (orderThreeProductName, "Mediocre Aluminum Coat");
    }

    @Test
    public void testMultipleResponseConditions () {
        given ().when ()
            .log ()
            .all ()
            .get ("http://localhost:3004/getAllOrders")
            .then ()
            .log ()
            .all ()
            .statusCode (200)
            .and ()
            .assertThat ()
            .body ("message", equalTo ("Orders fetched successfully!"), "orders[0].user_id", equalTo ("466"),
                "orders[0].product_id", equalTo ("357"), "orders[0].product_name", equalTo ("Small Marble Shoes"),
                "orders[0].product_amount", equalTo (1558));
    }

    @Test
    public void testDeserializeResponseWithPojo () {
        OrderResponse orderResponse = given ().when ()
            .log ()
            .all ()
            .get ("http://localhost:3004/getAllOrders")
            .then ()
            .log ()
            .all ()
            .statusCode (200)
            .extract ()
            .body ()
            .as (OrderResponse.class);

        assertEquals (orderResponse.getMessage (), "Orders fetched successfully!");
        assertEquals (orderResponse.getOrders ()
            .get (0)
            .getId (), 1);
        assertEquals (orderResponse.getOrders ()
            .get (1)
            .getTaxAmt (), 1228);

    }

    @Test
    public void testResponseFromJsonFile () {
        String actualResponse = given ().when ()
            .log ()
            .all ()
            .get ("http://localhost:3004/getAllOrders")
            .then ()
            .log ()
            .all ()
            .statusCode (200)
            .extract ()
            .response ()
            .asString ();

        String expectedJson = null;
        try {
            expectedJson = Files.readString (Paths.get (Objects.requireNonNull (this.getClass ()
                    .getClassLoader ()
                    .getResource ("AllOrders.json"))
                .getPath ()));
        } catch (IOException e) {
            throw new RuntimeException (e);
        }

        JSONAssert.assertEquals (expectedJson, actualResponse, true);

    }
}

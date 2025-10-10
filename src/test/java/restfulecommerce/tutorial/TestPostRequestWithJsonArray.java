package restfulecommerce.tutorial;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

public class TestPostRequestWithJsonArray {

    @Test
    public void testCreateOrder () {
        JsonObject orderOne = new JsonObject ();
        orderOne.addProperty ("user_id", "1");
        orderOne.addProperty ("product_id", "1");
        orderOne.addProperty ("product_name", "MacBook Pro");
        orderOne.addProperty ("product_amount", 9998.23);
        orderOne.addProperty ("qty", 1);
        orderOne.addProperty ("tax_amt", 999.8);
        orderOne.addProperty ("total_amt", 10998.03);

        JsonArray orderArray = new JsonArray ();
        orderArray.add (orderOne);

        given ().contentType (ContentType.JSON)
            .when ()
            .log ()
            .all ()
            .body (orderArray.toString ())
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
    public void testCreateOrderWithCollections () {
        Map<String, Object> orderOne = new HashMap<> ();
        orderOne.put ("user_id", "2");
        orderOne.put ("product_id", "2");
        orderOne.put ("product_name", "Xiaomi Pad 7");
        orderOne.put ("product_amount", 899.67);
        orderOne.put ("qty", 1);
        orderOne.put ("tax_amt", 89.96);
        orderOne.put ("total_amt", 989.63);

        List<Map<String, Object>> orderList = new ArrayList<> ();
        orderList.add (orderOne);

        given ().contentType (ContentType.JSON)
            .when ()
            .log ()
            .all ()
            .body (orderList)
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

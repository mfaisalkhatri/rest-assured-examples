package restfulecommerce.tutorial;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import restfulecommerce.tutorial.data.Orders;

public class TestPostRequestWithJsonFile {

    public List<Orders> getOrdersFromJson (String fileName) {
        InputStream inputStream = this.getClass ()
            .getClassLoader ()
            .getResourceAsStream (fileName);
        if (inputStream == null) {
            throw new IllegalArgumentException ("File not found!!");
        }
        Gson gson = new Gson ();
        try (BufferedReader reader = new BufferedReader (new InputStreamReader (inputStream))) {
            Type listType = new TypeToken<List<Orders>> () {
            }.getType ();
            return gson.fromJson (reader, listType);
        } catch (IOException e) {
            throw new RuntimeException ("Error Reading the JSON file" + fileName, e);
        }
    }

    @Test
    public void testCreateOrders () {

        List<Orders> orders = getOrdersFromJson ("new_orders.json");
        orders.forEach (System.out::println);

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

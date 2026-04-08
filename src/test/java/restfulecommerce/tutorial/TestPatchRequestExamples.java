package restfulecommerce.tutorial;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import com.google.gson.JsonObject;
import io.restassured.http.ContentType;
import net.datafaker.Faker;
import org.testng.annotations.Test;

public class TestPatchRequestExamples {

    private String token;

    @Test
    public void testPartialUpdateOrder () {
        int orderId = 2;

        Faker faker = new Faker ();
        String productId = String.valueOf (faker.number ()
            .numberBetween (1, 2000));
        String productName = faker.commerce ()
            .productName ();

        JsonObject orderDetail = new JsonObject ();
        orderDetail.addProperty ("product_id", productId);
        orderDetail.addProperty ("product_name", productName);

        given ().contentType (ContentType.JSON)
            .header ("Authorization", token)
            .when ()
            .log ()
            .all ()
            .body (orderDetail.toString ())
            .patch ("http://localhost:3004/partialUpdateOrder/" + orderId)
            .then ()
            .log ()
            .all ()
            .statusCode (200)
            .and ()
            .assertThat ()
            .body ("message", equalTo ("Order updated successfully!"), "order.product_id", equalTo (productId),
                "order.product_name", equalTo (productName));
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
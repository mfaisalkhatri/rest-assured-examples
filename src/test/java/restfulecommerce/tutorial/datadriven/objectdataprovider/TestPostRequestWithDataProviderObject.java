package restfulecommerce.tutorial.datadriven.objectdataprovider;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.List;

import io.restassured.http.ContentType;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestPostRequestWithDataProviderObject {

    @DataProvider (name = "orderData")
    public Object[][] getOrderData () {
        return new Object[][] { { new Order ("1", "90", "Colgate Gel", 109, 1, 10, 119, 201) },
            { new Order ("1", "13", "Perfume", 299, 1, 30, 329, 201) },
            { new Order ("4", "79", "Fresh Milk", 12, 1, 1, 13, 201) },
            { new Order ("6", "81", "Yogurt", 16, 2, 3, 35, 201) },
            { new Order ("2", "63", "Bath Soap", 5, 5, 3, 28, 201) } };
    }

    @Test (dataProvider = "orderData")
    public void testCreateOrder (Order order) {
        List<Order> orders = new ArrayList<> ();
        orders.add (order);
        given ().contentType (ContentType.JSON)
            .when ()
            .log ()
            .all ()
            .body (orders)
            .post ("http://localhost:3004/addOrder")
            .then ()
            .log ()
            .all ()
            .statusCode (order.getExpectedStatus ())
            .and ()
            .assertThat ()
            .body ("message", equalTo ("Orders added successfully!"));
    }
}
package restfulecommerce.tutorial.datadriven.jsondataprovider.pojoimplementation;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import io.restassured.http.ContentType;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestPostRequestWithJSON {

    @DataProvider (name = "orderData")
    public Iterator<Object[]> getOrderData () {
        List<Order> orderList = JsonReader.getOrderData ("orders_data.json");
        List<Object[]> data = new ArrayList<> ();
        for (Order order : orderList) {
            data.add (new Object[] { order });
        }
        return data.iterator ();
    }

    @Test (dataProvider = "orderData")
    public void testCreateOrder (Order order) {
        List<Order> orderData = List.of (order);
        given ().contentType (ContentType.JSON)
            .when ()
            .log ()
            .all ()
            .body (orderData)
            .post ("http://localhost:3004/addOrder")
            .then ()
            .log ()
            .all ()
            .statusCode (201)
            .assertThat ()
            .body ("message", equalTo ("Orders added successfully!"));
    }
}
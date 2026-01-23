package restfulecommerce.tutorial.datadriven.csvdataprovider;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.Iterator;
import java.util.List;

import io.restassured.http.ContentType;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestPostRequestWithCsv {

    @DataProvider (name = "orderData")
    public Iterator<Object[]> getOrders () {
        List<Order> orderList = CSVReader.getOrderData ("order_data.csv");
        return orderList.stream ()
            .map (order -> new Object[] { order })
            .iterator ();
    }

    @Test (dataProvider = "orderData")
    public void testCreateOrder (Order order) {
        List<Order> orderList = List.of (order);
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
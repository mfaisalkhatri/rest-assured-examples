package restfulecommerce.tutorial.datadriven.exceldataprovider;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static restfulecommerce.tutorial.datadriven.exceldataprovider.ExcelDynamicReader.getData;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import io.restassured.http.ContentType;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestPostRequestWithExcel {

    @DataProvider (name = "orderData")
    public Iterator<Object[]> getOrderData () {
        List<Map<String, Object>> table = ExcelDynamicReader.readExcelAsTable ("order_test_data.xlsx", "TestData");
        List<Order> orderData = getData (table, Order.class);

        List<Object[]> data = new ArrayList<> ();
        for (Order order : orderData) {
            data.add (new Object[] { order });
        }
        return data.iterator ();
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
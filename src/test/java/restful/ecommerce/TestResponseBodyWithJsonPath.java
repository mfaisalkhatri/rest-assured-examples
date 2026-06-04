package restful.ecommerce;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.util.List;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.path.json.JsonPath;

import io.restassured.response.ResponseBody;
import org.testng.annotations.Test;

/**
 * @author Faisal Khatri
 * @since 12/21/2022
 **/
@Epic ("Rest Assured POC - Example Tests")
@Feature ("Performing different API Tests using Rest-Assured")
public class TestResponseBodyWithJsonPath extends SetupSpecification {

    @Test
    @Description ("Example Test for extracting the response data using jsonpath")
    @Severity (SeverityLevel.MINOR)
    @Story ("Extracting response data using JsonPath")
    public void testResponseWithJsonPath () {
        final int orderId = 2;
        final ResponseBody response = given ().when ()
            .get ("/getAllOrders")
            .then ()
            .statusCode (200)
            .and ()
            .extract ()
            .response ()
            .getBody ();

        //Getting value of a respective field from response
        final JsonPath jsonPath = response.jsonPath ();
        assertEquals (jsonPath.getString ("message"), "Orders fetched successfully!");

        //List all objects inside the array
        final List<String> orderArray = jsonPath.getList ("orders");
        System.out.println ("Orders array " + orderArray);

        //Listing first object values
        System.out.println (jsonPath.getJsonObject ("orders[0]")
            .toString ());

        //listing specific field values of all objects inside the array
        final List<String> listOfUserIds = jsonPath.getList ("orders.user_id");
        System.out.println ("List of user ids in order array " + listOfUserIds);

        //listing only a required field value from a particular object
        final String productNameOfSecondOrder = jsonPath.getString ("orders[1].product_name");
        System.out.println ("First Name in second object " + productNameOfSecondOrder);
        assertEquals (productNameOfSecondOrder, "coffee toffee");
    }
}

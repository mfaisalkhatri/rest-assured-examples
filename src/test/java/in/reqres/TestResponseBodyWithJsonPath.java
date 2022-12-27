package in.reqres;

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
    public void testResponseWithJsonPath() {
        ResponseBody response = given ().when ()
            .queryParam ("page", "2")
            .get ("/api/users")
            .then ()
            .statusCode (200).and ().extract ().response ().getBody ();

        //Getting value of a respective field from response
        JsonPath jsonPath = response.jsonPath ();
        assertEquals(jsonPath.getInt ("page"), 2);


        //List all objects inside the array
        List<String > dataArray = jsonPath.getList ("data");
        System.out.println ("Data array " +dataArray);

        //Listing first object values
        System.out.println (jsonPath.getJsonObject ("data[0]").toString ());

        //listing specific field values of all objects inside the array
        List<String> listOfFirstNames = jsonPath.getList ("data.first_name");
        System.out.println ("List of first names in data array " +listOfFirstNames);

        //listing only a required field value from a particular object
        String firstNameInSecondObject = jsonPath.getString ("data[1].first_name");
        System.out.println ("First Name in second object " +firstNameInSecondObject);
        assertEquals (firstNameInSecondObject, "Lindsay");

    }

}

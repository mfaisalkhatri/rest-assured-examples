package in.reqres;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.util.List;
import io.restassured.path.json.JsonPath;

import io.restassured.response.ResponseBody;
import org.testng.annotations.Test;

/**
 * @author Faisal Khatri
 * @since 12/21/2022
 **/
public class TestResponseBodyWithJsonPath extends SetupConfig{


    @Test
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

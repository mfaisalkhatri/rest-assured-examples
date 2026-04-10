package restfulecommerce.tutorial;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

import io.restassured.response.Response;
import org.testng.annotations.Test;

public class TestResponseExamples {

    @Test
    public void testStatusCode () {
        given ().when ()
            .get ("http://localhost:3004/getAllOrders")
            .then ()
            .statusCode (200);

        Response response = given ().when ()
            .get ("http://localhost:3004/getAllOrders")
            .then ()
            .extract ()
            .response ();

        int statusCode = response.getStatusCode ();
        assertEquals (200, statusCode);

        int getStatusCode = given ().when ()
            .get ("http://localhost:3004/getAllOrders")
            .then ()
            .extract ()
            .statusCode ();
        assertEquals (200, getStatusCode);
    }

    @Test
    public void testResponseContentType () {
        given ().when ()
            .get ("http://localhost:3004/getAllOrders")
            .then ()
            .statusCode (200)
            .contentType ("application/json; charset=utf-8");

        Response response = given ().when ()
            .get ("http://localhost:3004/getAllOrders")
            .then ()
            .extract ()
            .response ();

        assertEquals ("application/json; charset=utf-8", response.getContentType ());

        String contentType = given ().when ()
            .get ("http://localhost:3004/getAllOrders")
            .then ()
            .extract ()
            .contentType ();

        assertEquals ("application/json; charset=utf-8", contentType);
    }

    @Test
    public void testResponseHeader () {
        given ().when ()
            .get ("http://localhost:3004/getAllOrders")
            .then ()
            .header ("Content-Type", "application/json; charset=utf-8")
            .and ()
            .statusCode (200);

        Response response = given ().when ()
            .get ("http://localhost:3004/getAllOrders")
            .then ()
            .extract ()
            .response ();

        assertEquals ("application/json; charset=utf-8", response.getHeader ("Content-Type"));
        System.out.println ("content-length: " + response.getHeader ("Content-Length"));

        String header = given ().when ()
            .get ("http://localhost:3004/getAllOrders")
            .then ()
            .extract ()
            .header ("Content-Type");

        assertEquals ("application/json; charset=utf-8", header);
    }
}
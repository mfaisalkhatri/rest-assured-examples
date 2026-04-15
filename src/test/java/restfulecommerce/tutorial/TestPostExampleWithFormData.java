package restfulecommerce.tutorial;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import io.restassured.response.Response;
import org.testng.annotations.Test;

public class TestPostExampleWithFormData {

    @Test
    public void testFormData () {
        Response response = given ().header ("content-type", "application/x-www-form-urlencoded; charset=utf-8")
            .log ()
            .all ()
            .formParam ("username", "faisal")
            .formParam ("password", "1234")
            .when ()
            .post ("https://postman-echo.com/post")
            .then ()
            .log ()
            .all ()
            .statusCode (200)
            .body ("form.username", equalTo ("faisal"))
            .body ("form.password", equalTo ("1234"))
            .extract ()
            .response ();

        System.out.println (response.asPrettyString ());

    }

}

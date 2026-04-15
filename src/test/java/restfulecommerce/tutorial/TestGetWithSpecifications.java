package restfulecommerce.tutorial;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestGetWithSpecifications {

    private RequestSpecification  request;
    private ResponseSpecification response;

    @BeforeClass
    public void setup () {
        request = new RequestSpecBuilder ().setBaseUri ("http://localhost:3004/getOrder")
            .addFilter (new RequestLoggingFilter ())
            .addFilter (new ResponseLoggingFilter ())
            .addQueryParam ("id", 1)
            .build ();

        response = new ResponseSpecBuilder ().expectStatusCode (200)
            .expectBody ("orders[0].id", equalTo (1))
            .build ();

    }

    @Test
    public void testGetOrderWithQueryParam () {
        given ().when ()
            .spec (request)
            .get ()
            .then ()
            .spec (response);
    }

    @Test
    public void testGetOrderWithMultipleQueryParam () {
        given ().when ()
            .spec (request)
            .queryParams ("user_id", 1, "product_id", 1)
            .get ()
            .then ()
            .spec (response);
    }

}

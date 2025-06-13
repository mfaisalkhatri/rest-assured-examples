package restfulecommerce.endtoend;

import static org.hamcrest.Matchers.lessThan;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    @BeforeClass
    public void configSetup () {
        RequestSpecification requestSpecification = new RequestSpecBuilder ().setBaseUri (
                "http://localhost:3004")
            .addHeader ("Content-Type", "application/json")
            .addHeader ("Accept", "application/json")
            .addFilter (new RequestLoggingFilter ())
            .addFilter (new ResponseLoggingFilter ())
            .build ();

        ResponseSpecification responseSpecification = new ResponseSpecBuilder ().expectResponseTime (lessThan (20000L))
            .build ();

        RestAssured.requestSpecification = requestSpecification;
        RestAssured.responseSpecification = responseSpecification;
    }
}
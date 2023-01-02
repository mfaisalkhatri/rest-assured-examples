package assertionexamples;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.emptyArray;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToCompressingWhiteSpace;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.startsWith;

import java.sql.Time;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * @author Faisal Khatri
 * @since 12/30/2022
 **/
public class StringRelatedAssertionTests {

    private static RequestSpecBuilder    requestSpecBuilder;
    private static ResponseSpecBuilder   responseSpecBuilder;
    private static ResponseSpecification responseSpecification;
    private static RequestSpecification  requestSpecification;

    @BeforeClass
    public void setupSpecBuilder () {
        requestSpecBuilder = new RequestSpecBuilder ().setBaseUri ("https://reqres.in/api/users/")
            .addQueryParam ("page", 2)
            .addFilter (new RequestLoggingFilter ())
            .addFilter (new ResponseLoggingFilter ());
        responseSpecBuilder = new ResponseSpecBuilder ().expectStatusCode (200)
            .expectBody ("page", equalTo (2));

        responseSpecification = responseSpecBuilder.build ();
        requestSpecification = requestSpecBuilder.build ();

    }

    @Test
    public void testStringAssertions () {

        given ().spec (requestSpecification)
            .get ()
            .then ()
            .spec (responseSpecification)
            .assertThat ()
            .body ("data[0].first_name", equalTo ("Michael"))
            .body ("data[0].first_name", equalToIgnoringCase ("MICHael"))
            .body ("data[0].email", containsString ("michael.lawson"))
            .body ("data[0].last_name", startsWith ("L"))
            .body ("data[0].last_name", endsWith ("n"))
            .body ("data[1].first_name", equalToCompressingWhiteSpace ("    Lindsay "));
    }

    @Test
    public void testNotNullAssertions () {
        given ().spec (requestSpecification)
            .get ()
            .then ()
            .spec (responseSpecification)
            .and ()
            .assertThat ()
            .body ("data[0].first_name", is (Matchers.notNullValue ()));

    }

    @Test
    public void testHasKeyAssertion () {
        given ().spec (requestSpecification)
            .get ()
            .then ()
            .spec (responseSpecification)
            .and ()
            .assertThat ()
            .body ("data[0]", hasKey ("email"))
            .body ("support", hasKey ("url"))
            .body ("$", hasKey ("page"))
            .body ("$", hasKey ("total"));
    }

    @Test
    public void testNotAssertions () {
        given ().spec (requestSpecification)
            .get ()
            .then ()
            .spec (responseSpecification)
            .and ()
            .assertThat ()
            .body ("data", not (emptyArray ()))
            .body ("data[0].first_name", not (equalTo ("George")))
            .body ("data.size()", greaterThan (5));
    }

    @Test
    public void testMultipleAssertStatement () {
        given ().spec (requestSpecification)
            .get ()
            .then ()
            .spec (responseSpecification)
            .and ()
            .assertThat ()
            .body ("page", equalTo (2), "data[0].first_name", equalTo ("Michael"), "support.url", is (notNullValue ()));

    }

    @AfterMethod
    public void getTestExecutionTime (ITestResult result) {
        String methodName = result.getMethod ().getMethodName ();
        long totalExecutionTime = (result.getEndMillis () - result.getStartMillis ());
        System.out.println ("Total Execution time: " +totalExecutionTime +" milliseconds" + " for method " +methodName);
    }
}
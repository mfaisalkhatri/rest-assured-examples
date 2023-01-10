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

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * @author Faisal Khatri
 * @since 12/30/2022
 **/
@Epic ("Rest Assured POC - Example Tests")
@Feature ("Performing different API Tests using Rest-Assured")
@Story ("String related Assertions using Hamcrest in rest assured")
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
    @Description ("Example Test for performing String related assertions using Hamcrest")
    @Severity (SeverityLevel.NORMAL)
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
    @Description ("Example Test for performing Not null assertions using Hamcrest")
    @Severity (SeverityLevel.NORMAL)
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
    @Description ("Example Test for performing has key assertions using Hamcrest")
    @Severity (SeverityLevel.NORMAL)
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
    @Description ("Example Test for performing Not assertions using Hamcrest")
    @Severity (SeverityLevel.NORMAL)
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
    @Description ("Example Test for multiple assertions in single statement using Hamcrest")
    @Severity (SeverityLevel.NORMAL)
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
        String methodName = result.getMethod ()
            .getMethodName ();
        long totalExecutionTime = (result.getEndMillis () - result.getStartMillis ());
        System.out.println (
            "Total Execution time: " + totalExecutionTime + " milliseconds" + " for method " + methodName);
    }
}
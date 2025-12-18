package assertionexamples;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.hamcrest.Matchers.not;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/**
 * @author Faisal Khatri
 * @since 12/30/2022
 **/
@Epic ("Rest Assured POC - Example Tests")
@Feature ("Performing different API Tests using Rest-Assured")
@Story ("Number related Assertions using Hamcrest in rest assured")
public class NumberRelatedAssertionTests {

    private static final String URL = "https://api.restful-api.dev/objects";

    @Test
    @Description ("Example Test for performing number related assertions using Hamcrest")
    @Severity (SeverityLevel.NORMAL)
    public void testNumberAssertions () {

        given ().when ()
            .queryParam ("id", 3)
            .get (URL)
            .then ()
            .statusCode (200)
            .and ()
            .assertThat ()
            .body ("[0].id", equalTo ("3"))
            .body ("[0].data['capacity GB']", greaterThan (500))
            .body ("[0].data['capacity GB']", greaterThanOrEqualTo (512))
            .body ("[0].data['capacity GB']", lessThan (550))
            .body ("[0].data['capacity GB']", lessThanOrEqualTo (512));
    }

    @Test
    @Description ("Example Test for performing Greater than assertions using Hamcrest")
    @Severity (SeverityLevel.NORMAL)
    public void testGreaterThanAssertions () {

        given ().when ()
            .queryParam ("id", 3)
            .get (URL)
            .then ()
            .statusCode (200)
            .and ()
            .assertThat ()
            .body ("[0].data['capacity GB']", greaterThan (500))
            .body ("[0].data['capacity GB']", greaterThanOrEqualTo (512));
    }

    @Test
    @Description ("Example Test for performing Less than assertions using Hamcrest")
    @Severity (SeverityLevel.NORMAL)
    public void testLessThanAssertions () {

        given ().when ()
            .queryParam ("id", 5)
            .log ()
            .all ()
            .get (URL)
            .then ()
            .log ()
            .all ()
            .statusCode (200)
            .and ()
            .assertThat ()
            .body ("[0].data.price", lessThan (700f))
            .body ("[0].data.price", lessThanOrEqualTo (689.99f));
    }

    @Test
    @Description ("Example Test for performing size, item, and not equal assertions")
    @Severity (SeverityLevel.NORMAL)
    public void testHasSizeOrItemOrNotEqual () {
        given ().when ()
            .queryParam ("id", 3)
            .queryParam ("id", 5)
            .get (URL)
            .then ()
            .statusCode (200)
            .and ()
            .assertThat ()
            .body ("$", hasSize (2))
            .body ("name", hasItem ("Apple iPhone 12 Pro Max"))
            .body ("[1].id", not (equalTo ("6")));
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
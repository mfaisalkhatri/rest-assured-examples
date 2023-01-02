package assertionexamples;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

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

    private static String URL = "https://reqres.in/api/users/";

    @Test
    @Description ("Example Test for performing number related assertions using Hamcrest")
    @Severity (SeverityLevel.NORMAL)
    public void testNumberAssertions () {

        given ().when ()
            .queryParam ("page", 2)
            .get (URL)
            .then ()
            .statusCode (200)
            .and ()
            .assertThat ()
            .body ("page", equalTo(2))
            .body ("per_page", greaterThan (4))
            .body ("per_page", greaterThanOrEqualTo (6))
            .body ("total", lessThan (14))
            .body ("total_pages", lessThanOrEqualTo (3));
    }

    @Test
    @Description ("Example Test for performing Greater than assertions using Hamcrest")
    @Severity (SeverityLevel.NORMAL)
    public void testGreaterThanAssertions () {

        given ().when ()
            .queryParam ("page", 2)
            .get (URL)
            .then ()
            .statusCode (200)
            .and ()
            .assertThat ()
            .body ("per_page", greaterThan (4))
            .body ("per_page", greaterThanOrEqualTo (6));
    }


    @Test
    @Description ("Example Test for performing Less than assertions using Hamcrest")
    @Severity (SeverityLevel.NORMAL)
    public void testLessThanAssertions () {

        given ().when ()
            .queryParam ("page", 2)
            .get (URL)
            .then ()
            .statusCode (200)
            .and ()
            .assertThat ()
            .body ("total", lessThan (14))
            .body ("total_pages", lessThanOrEqualTo (3));
    }

    @AfterMethod
    public void getTestExecutionTime (ITestResult result) {
        String methodName = result.getMethod ().getMethodName ();
        long totalExecutionTime = (result.getEndMillis () - result.getStartMillis ());
        System.out.println ("Total Execution time: " +totalExecutionTime +" milliseconds" + " for method " +methodName);
    }

}

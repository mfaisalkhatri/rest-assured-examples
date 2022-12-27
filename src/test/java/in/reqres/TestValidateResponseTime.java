package in.reqres;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

/**
 * @author Faisal Khatri
 * @since 12/21/2022
 **/
@Epic ("Rest Assured POC - Example Tests")
@Feature ("Performing different API Tests using Rest-Assured")
public class TestValidateResponseTime extends SetupSpecification {

    @Test
    @Description ("Example Test for validating response time using rest assured")
    @Severity (SeverityLevel.MINOR)
    @Story ("Validate Response Time using rest assured")
    public void testResponseTime () {
        given ().when ()
            .queryParam ("page", "2")
            .get ("/api/users")
            .then ()
            .statusCode (200)
            .and ()
            .time (Matchers.lessThan (1000L))
            .and ()
            .assertThat ()
            .body ("page", equalTo(2));
    }
}

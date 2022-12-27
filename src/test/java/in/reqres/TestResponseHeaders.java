package in.reqres;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

/**
 * @author Faisal Khatri
 * @since 12/21/2022
 **/
@Epic ("Rest Assured POC - Example Tests")
@Feature ("Performing different API Tests using Rest-Assured")
public class TestResponseHeaders extends SetupSpecification {

    @Test
    @Description ("Example Test for verifying the response headers using rest assured")
    @Severity (SeverityLevel.MINOR)
    @Story ("Checking response headers using rest-assured")
    public void responseHeadersTest () {
        given ().when ()
            .with ()
            .queryParam ("page", 2)
            .get ("/api/users")
            .then ()
            .statusCode (200)
            .and ()
            .assertThat ()
            .body ("page", equalTo(2))
            .header ("Content-Type", equalTo ("application/json; charset=utf-8"))
            .header ("Content-Encoding", equalTo ("gzip"));
    }
}

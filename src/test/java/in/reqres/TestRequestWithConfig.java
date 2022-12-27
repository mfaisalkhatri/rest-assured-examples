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
 * @since 12/27/2022
 **/
@Epic ("Rest Assured POC - Example Tests")
@Feature ("Performing different API Tests using Rest-Assured")
public class TestRequestWithConfig extends SetupConfiguration {

    @Test
    @Description ("Example Test for executing GET request using rest assured configuration")
    @Severity (SeverityLevel.CRITICAL)
    @Story ("Writing API Tests using rest assured configurations")
    public void testGetRequest () {
        String responseBody = given ().when ()
            .post ("https://apimocha.com/checkt/rover")
            .then ()
            .statusCode (200)
            .and ()
            .assertThat ()
            .body ("type", equalTo ("dog"))
            .and ()
            .extract ()
            .response ()
            .asString ();

        System.out.println (responseBody);

    }

}

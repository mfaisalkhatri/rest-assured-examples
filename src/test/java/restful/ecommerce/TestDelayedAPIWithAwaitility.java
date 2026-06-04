package restful.ecommerce;

import static io.restassured.RestAssured.get;
import static org.awaitility.Awaitility.await;
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
public class TestDelayedAPIWithAwaitility extends AwaitilityBase {

    @Test
    @Description ("Example Test for executing GET request using awaitility")
    @Severity (SeverityLevel.NORMAL)
    @Story ("Writing API Tests using awaitility")
    public void testDelayedPostAPI () {

        await ().until (() -> get ("http://localhost:3004/getAllOrders").then ()
            .statusCode (200)
            .and ()
            .extract ()
            .path ("orders[1].user_id")
            .toString (), equalTo ("USR002"));
    }
}

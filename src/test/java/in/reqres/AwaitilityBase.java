package in.reqres;

import static java.util.concurrent.TimeUnit.SECONDS;

import io.restassured.RestAssured;
import org.awaitility.Awaitility;
import org.testng.annotations.BeforeClass;

/**
 * @author Faisal Khatri
 * @since 12/27/2022
 **/
public class AwaitilityBase {

    @BeforeClass
    public void setupAwaitility () {
        Awaitility.reset ();
        Awaitility.setDefaultPollDelay (5, SECONDS);
        Awaitility.setDefaultPollInterval (2, SECONDS);
        Awaitility.setDefaultTimeout (10, SECONDS);
    }
}

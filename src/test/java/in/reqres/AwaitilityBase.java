package in.reqres;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

import java.util.concurrent.TimeUnit;

import org.awaitility.Awaitility;
import org.testng.annotations.BeforeClass;

/**
 * @author Faisal Khatri
 * @since 12/27/2022
 **/
public class AwaitilityBase {

    @BeforeClass
    public void setupAwaitility() {
        Awaitility.reset ();
        Awaitility.setDefaultPollDelay (3, SECONDS);
        Awaitility.setDefaultPollInterval (2, SECONDS);
        Awaitility.setDefaultTimeout (10, SECONDS);
    }
}

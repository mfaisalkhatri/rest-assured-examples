package in.reqres;

import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import org.testng.annotations.BeforeClass;

/**
 * @author Faisal Khatri
 * @since 12/27/2022
 **/
public class SetupConfiguration {

    @BeforeClass
    public void setup () {
        RestAssured.config = RestAssuredConfig.config ()
            .httpClient (HttpClientConfig.httpClientConfig ()
                .setParam ("http.connection.timeout", 10000)
                .setParam ("http.connection.request.timeout", 10000)
                .setParam ("http.socket.timeout", 10000));

    }
}

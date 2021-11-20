package io.github.mfaisalkhatri;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


/**
 * Created By Faisal Khatri on 20-11-2021
 */
public class TestGetRequestWithRestAssuredConfig extends SetupConfig{

    @Test
    public void getRequestTestwithRestAssuredConfig() {
        given()
                .when()
                .get("/api/users/2")
                .then()
                .statusCode(200)
                .and()
                .assertThat()
                .body("data.first_name", equalTo("Janet"));
    }
}
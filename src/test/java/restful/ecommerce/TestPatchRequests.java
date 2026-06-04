/*      Copyright 2022 Mohammad Faisal Khatri

        Licensed under the Apache License, Version 2.0 (the "License");
        you may not use this file except in compliance with the License.
        You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

        Unless required by applicable law or agreed to in writing, software
        distributed under the License is distributed on an "AS IS" BASIS,
        WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
        See the License for the specific language governing permissions and
        limitations under the License.
*/

package restful.ecommerce;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import data.restful.ecommerce.AuthenticationPojo;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created By Faisal Khatri on 20-11-2021
 */
@Epic ("Rest Assured POC - Example Tests")
@Feature ("Performing different API Tests using Rest-Assured")
public class TestPatchRequests {

    private static final Logger LOG = LogManager.getLogger (TestPatchRequests.class);
    private static final String URL = "http://localhost:3004";
    private              String token;

    @BeforeClass
    public void setup () {
        getToken ();
    }

    @Test ()
    @Description ("Example Test for executing PATCH request using rest assured ")
    @Severity (SeverityLevel.CRITICAL)
    @Story ("Execute Patch requests using rest-assured")
    public void patchRequestTests () {

        final int orderId = 2;
        final String partialOrderUpdate = """
                {
                "product_id": "4",
                "product_name": "coffee toffee",
                "product_amount": 30
                }
            """;
        final String response = given ().header ("Authorization", this.token)
            .contentType (ContentType.JSON)
            .body (partialOrderUpdate)
            .when ()
            .log ()
            .all ()
            .patch (URL + "/partialUpdateOrder/" + orderId)
            .then ()
            .log ()
            .all ()
            .assertThat ()
            .statusCode (200)
            .and ()
            .assertThat ()
            .body ("message", equalTo ("Order updated successfully!"))
            .body ("order.id", equalTo (orderId))
            .body ("order.product_name", equalTo ("coffee toffee"))
            .and ()
            .extract ()
            .response ()
            .body ()
            .asString ();

        LOG.info (response);
    }

    private void getToken () {
        final AuthenticationPojo requestBody = new AuthenticationPojo ("admin", "secretPass123");
        this.token = given ().contentType (ContentType.JSON)
            .body (requestBody)
            .when ()
            .header ("accept", "application/json")
            .post (URL + "/auth")
            .then ()
            .assertThat ()
            .statusCode (201)
            .body ("message", equalTo ("Authentication Successful!"))
            .and ()
            .body ("token", notNullValue ())
            .and ()
            .extract ()
            .path ("token");
    }
}
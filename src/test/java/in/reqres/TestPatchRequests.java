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

package in.reqres;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

/**
 * Created By Faisal Khatri on 20-11-2021
 */
@Epic ("Rest Assured POC - Example Tests")
@Feature ("Performing different API Tests using Rest-Assured")
public class TestPatchRequests {

    private static final Logger LOG = LogManager.getLogger (TestPatchRequests.class);
    private static final String URL = "https://reqres.in";

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
        final String response = given ().contentType (ContentType.JSON)
            .body (partialOrderUpdate)
            .when ()
            .patch (URL + "/partialUpdateOrder/" + orderId)
            .then ()
            .assertThat ()
            .statusCode (200)
            .and ()
            .assertThat ()
            .body ("message", equalTo ("Order updated successfully!\""))
            .and ()
            .assertThat ()
            .body ("order.id", equalTo (orderId))
            .and ()
            .assertThat ()
            .body ("product_name.id", equalTo ("coffee toffee"))
            .and ()
            .extract ()
            .response ()
            .body ()
            .asString ();

        LOG.info (response);
    }
}
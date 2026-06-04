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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Created By Faisal Khatri on 19-11-2021
 */
@Epic ("Rest Assured POC - Example Tests")
@Feature ("Performing different API Tests using Rest-Assured")
public class TestGetRequests {

    private static final String URL = "http://localhost:3004/getOrder";
    private static final Logger LOG = LogManager.getLogger (TestGetRequests.class);

    @DataProvider (name = "getUserData")
    public Iterator<Object[]> getUsers () {
        final List<Object[]> getData = new ArrayList<> ();
        getData.add (new Object[] { 2 });
        return getData.iterator ();
    }

    @Test (dataProvider = "getUserData")
    @Description ("Example Test for executing GET request using rest assured")
    @Severity (SeverityLevel.CRITICAL)
    @Story ("Execute Get requests using rest-assured")
    public void getRequestTest (final int orderId) {
        given ().when ()
            .get (URL + "?id=" + orderId)
            .then ()
            .statusCode (200)
            .and ()
            .assertThat ()
            .body ("orders[0].id", equalTo (orderId));

        final int statusCode = given ().when ()
            .queryParam ("id", orderId)
            .get (URL + orderId)
            .statusCode ();
        LOG.info (statusCode);

        final String responseBody = given ().when ()
            .get (URL + orderId)
            .getBody ()
            .asString ();
        LOG.info (responseBody);
    }

    @Test (dataProvider = "getUserData")
    @Description ("Example Test for executing GET request using rest assured with query params")
    @Severity (SeverityLevel.CRITICAL)
    @Story ("Execute Get requests using rest-assured")
    public void getRequestTestWithQueryParam (final int orderId) {
        given ().when ()
            .queryParam ("id", orderId)
            .get (URL)
            .then ()
            .statusCode (200)
            .and ()
            .assertThat ()
            .body ("message", equalTo ("Order found!!"))
            .and ()
            .body ("orders[0].id", equalTo (orderId), "orders[0].product_name", equalTo ("coffee toffee"));

        final String responseBody = given ().when ()
            .queryParam ("id", orderId)
            .get (URL)
            .getBody ()
            .asString ();
        LOG.info (responseBody);

        final JSONObject jsonObject = new JSONObject (responseBody);
        final JSONArray dataArray = jsonObject.getJSONArray ("orders");
        final JSONObject dataObject = dataArray.getJSONObject (0);
        final String userId = dataObject.get ("user_id")
            .toString ();

        LOG.info (userId);
    }
}
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

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import data.reqres.AuthenticationPojo;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Created By Faisal Khatri on 20-11-2021
 */
@Epic ("Rest Assured POC - Example Tests")
@Feature ("Performing different API Tests using Rest-Assured")
@Story ("Perform Authentication using rest-assured")
public class TestAuthentication {

    private static final String URL = "http://localhost:3004";
    private static final Logger LOG = LogManager.getLogger (TestAuthentication.class);

    /**
     * Created by Faisal Khatri on 20-11-2021
     *
     * @return test data
     */
    @DataProvider
    public Iterator<Object[]> getAuthenticationData () {
        final List<Object[]> getTestData = new ArrayList<> ();
        getTestData.add (new Object[] { "admin", "secretPass123" });
        return getTestData.iterator ();
    }

    /**
     * Created by Faisal on 20-11-2021
     *
     * @param username
     * @param password
     */
    @Test (dataProvider = "getAuthenticationData")
    @Description ("Example Test for performing authentication using rest assured")
    @Severity (SeverityLevel.NORMAL)
    public void testAuthenticationToken (final String username, final String password) {
        final AuthenticationPojo requestBody = new AuthenticationPojo (username, password);

        given ().contentType (ContentType.JSON)
            .body (requestBody)
            .when ()
            .log ()
            .all ()
            .header ("accept", "application/json")
            .post (URL + "/auth")
            .then ()
            .assertThat ()
            .statusCode (201)
            .log ()
            .all ()
            .body ("message", equalTo ("Authentication Successful!"))
            .and ()
            .body ("token", notNullValue ());

    }
    
    public static Map<String, Object> getToken (final String username, final String password) {
        final AuthenticationPojo requestBody = new AuthenticationPojo (username, password);
        final String response = given ().contentType (ContentType.JSON)
            .body (requestBody)
            .when ()
            .log ()
            .all ()
            .header ("accept", "application/json")
            .post (URL + "/auth")
            .then ()
            .assertThat ()
            .statusCode (201)
            .log ()
            .all ()
            .body ("message", equalTo ("Authentication Successful!"))
            .and ()
            .body ("token", notNullValue ())
            .and ()
            .extract ()
            .response ()
            .asString ();

        final JSONObject responseObject = new JSONObject (response);
        final Map<String, Object> responseMap = new HashMap<> ();
        responseMap.put ("token", responseObject.getString ("token"));
        return responseMap;
    }

    /**
     * Created by Faisal on 20-11-2021
     *
     * @param username
     * @param password
     */
    @Test (dataProvider = "getAuthenticationData")
    @Severity (SeverityLevel.NORMAL)
    @Description ("Example Test for printing token by getting token after executing the post authentication request")
    public void testAuthToken (final String username, final String password) {
        LOG.info (MessageFormat.format ("Token is {0}", getToken (username, password).get ("token")));
    }
}
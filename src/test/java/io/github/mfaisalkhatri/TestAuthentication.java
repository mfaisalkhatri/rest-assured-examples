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

package io.github.mfaisalkhatri;

import data.reqres.AuthenticationPojo;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.Iterator;
import org.json.JSONObject;
import io.restassured.http.ContentType;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

/**
 * Created By Faisal Khatri on 20-11-2021
 */
public class TestAuthentication {

    private static final String URL = "https://reqres.in";
    private static final Logger                      LOG = LogManager.getLogger (TestAuthentication.class);

    /**
     * Created by Faisal Khatri on 20-11-2021
     *
     * @return test data
     */
    @DataProvider
    public Iterator<Object []> getAuthenticationData () {
        final List<Object []> getTestData = new ArrayList<> ();
        getTestData.add (new Object [] { "eve.holt@reqres.in", "pistol" });
        return getTestData.iterator ();
    }

    /**
     * Created by Faisal on 20-11-2021
     *
     * @param email
     * @param password
     */
    @Test (dataProvider = "getAuthenticationData")
    public void testAuthenticationToken (String email, String password) {
        final AuthenticationPojo requestBody = new AuthenticationPojo (email, password);

        given ().contentType (ContentType.JSON)
            .body (requestBody)
            .when ()
            .log ()
            .all ()
            .post (URL + "/api/register")
            .then ()
            .assertThat ()
            .statusCode (200)
            .log ()
            .all ()
            .body ("id", notNullValue ())
            .and ()
            .body ("token", notNullValue ());

    }

    /**
     * Created by Faisal on 20-11-2021
     *
     * @param email
     * @param password
     * @return auth details
     */
    public static Map<String, Object> getToken (String email, String password) {
        final AuthenticationPojo requestBody = new AuthenticationPojo (email, password);
        final String response = given ().contentType (ContentType.JSON)
            .body (requestBody)
            .when ()
            .log ()
            .all ()
            .post (URL + "/api/register")
            .then ()
            .assertThat ()
            .statusCode (200)
            .log ()
            .all ()
            .body ("id", notNullValue ())
            .and ()
            .body ("token", notNullValue ())
            .and ()
            .extract ()
            .response ()
            .asString ();

        final JSONObject responseObject = new JSONObject (response);
        final Map<String, Object> responseMap = new HashMap<> ();
        responseMap.put ("id", responseObject.getInt ("id"));
        responseMap.put ("token", responseObject.getString ("token"));
        return responseMap;
    }

    /**
     * Created by Faisal on 20-11-2021
     *
     * @param email
     * @param password
     */
    @Test (dataProvider = "getAuthenticationData")
    public void testAuthToken (String email, String password) {
        LOG.info ("Token is" + getToken (email, password).get ("token")
            .toString ());

    }

}
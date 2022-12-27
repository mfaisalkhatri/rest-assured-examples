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
import org.json.simple.parser.ParseException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Created By Faisal Khatri on 19-11-2021
 */
@Epic ("Rest Assured POC - Example Tests")
@Feature ("Performing different API Tests using Rest-Assured")
public class TestGetRequests {

    private static final String URL = "https://reqres.in/api/users/";
    private static final Logger LOG = LogManager.getLogger (TestGetRequests.class);

    /**
     * Created By Faisal Khatri on 19-11-2021
     *
     * @return testData
     */
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
    public void getRequestTest (final int userId)  {
        given ().when ()
            .get (URL + userId)
            .then ()
            .statusCode (200)
            .and ()
            .assertThat ()
            .body ("data.id", equalTo (userId));

        final int statusCode = given ().when ()
            .get (URL + userId)
            .statusCode ();
        LOG.info (statusCode);

        final String responseBody = given ().when ()
            .get (URL + userId)
            .getBody ()
            .asString ();
        LOG.info (responseBody);
    }

    @Test (dataProvider = "getUserData")
    @Description ("Example Test for executing GET request using rest assured with query params")
    @Severity (SeverityLevel.CRITICAL)
    @Story ("Execute Get requests using rest-assured")
    public void getRequestTestWithQueryParam (final int userPage) {
        given ().when ()
            .queryParam ("page", userPage)
            .get (URL)
            .then ()
            .statusCode (200)
            .and ()
            .assertThat ()
            .body ("page", equalTo (userPage))
            .and ()
            .body ("data[0].first_name", equalTo ("Michael"));

        final String responseBody = given ().when ()
            .queryParam ("page", userPage)
            .get (URL)
            .getBody ()
            .asString ();
        LOG.info (responseBody);

        JSONObject jsonObject = new JSONObject (responseBody);
        JSONArray dataArray = jsonObject.getJSONArray ("data");
        JSONObject dataObject = dataArray.getJSONObject (0);
        String first_name = dataObject.get ("first_name")
            .toString ();
        LOG.info (first_name);

    }
}
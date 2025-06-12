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

import data.reqres.PostData;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Created By Faisal Khatri on 20-11-2021
 */
@Epic ("Rest Assured POC - Example Tests")
@Feature ("Performing different API Tests using Rest-Assured")

public class TestPutRequests {

    private static final Logger LOG = LogManager.getLogger (TestPutRequests.class);
    private static final String URL = "https://reqres.in";

    /**
     * Created By Faisal Khatri on 20-11-2021
     *
     * @return test data for put requests
     */
    @DataProvider (name = "putData")
    public Iterator<Object[]> putData () {
        final List<Object[]> putData = new ArrayList<> ();
        putData.add (new Object[] { 2, "Michael", "QA Lead" });
        putData.add (new Object[] { 958, "Yuan", "Project Architect" });
        return putData.iterator ();
    }

    /**
     * Created By Faisal Khatri on 20-11-2021 Executing Put Request using Rest Assured.
     *
     * @param id
     * @param name
     * @param job
     */
    @Test (dataProvider = "putData")
    @Description ("Example Test for executing PUT request using rest assured")
    @Severity (SeverityLevel.CRITICAL)
    @Story ("Execute Post requests using rest-assured")
    public void putRequestsTests (final int id, final String name, final String job) {

        final PostData postData = new PostData (name, job);
        final String response = given ().contentType (ContentType.JSON)
            .header ("x-api-key","reqres-free-v1")
            .body (postData)
            .when ()
            .put (URL + "/api/users/" + id)
            .then ()
            .assertThat ()
            .statusCode (200)
            .and ()
            .assertThat ()
            .body ("name", equalTo (name))
            .and ()
            .assertThat ()
            .body ("job", equalTo (job))
            .and ()
            .extract ()
            .response ()
            .body ()
            .asString ();

        LOG.info (response);

    }
}
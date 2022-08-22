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

import data.reqres.PostData;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import io.restassured.http.ContentType;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

/**
 * Created By Faisal Khatri on 20-11-2021
 */
public class TestPatchRequests {

    private static final Logger LOG = LogManager.getLogger (TestPatchRequests.class);
    private static final String URL = "https://reqres.in";

    /**
     * Created By Faisal Khatri on 20-11-2021
     *
     * @return test data for patch requests
     */
    @DataProvider (name = "patchData")
    public Iterator<Object[]> patchData () {
        final List<Object[]> patchData = new ArrayList<> ();
        patchData.add (new Object[] { 2, "Michael", "QA Lead" });
        patchData.add (new Object[] { 958, "Yuan", "Project Architect" });
        return patchData.iterator ();
    }

    /**
     * Executing Put Request using Rest Assured.
     *
     * @param id
     * @param name
     * @param job
     *
     * @since Mar 8, 2020
     */
    @Test (dataProvider = "patchData")
    public void patchRequestTests (final int id, final String name, final String job) {

        final PostData postData = new PostData (name, job);
        final String response = given ().contentType (ContentType.JSON)
            .body (postData)
            .when ()
            .patch (URL + "/api/users/" + id)
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
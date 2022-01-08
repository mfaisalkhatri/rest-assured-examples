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

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

/**
 * Created By Faisal Khatri on 19-11-2021
 */
public class TestGetRequests {

    private static final String URL = "https://reqres.in/api/users/";
    Logger                      log = LogManager.getLogger (TestGetRequests.class);

    /**
     * Created By Faisal Khatri on 19-11-2021
     *
     * @return testData
     */
    @DataProvider (name = "getUserData")
    public Iterator<Object []> getUsers () {
        final List<Object []> getData = new ArrayList<> ();
        getData.add (new Object [] { 2 });
        return getData.iterator ();
    }

    @Test (dataProvider = "getUserData")
    public void getRequestTest (final int userId) {
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
        this.log.info (statusCode);

        final String responseBody = given ().when ()
            .get (URL + userId)
            .getBody ()
            .asString ();
        this.log.info (responseBody);
    }

    @Test (dataProvider = "getUserData")
    public void getRequestTestWithQueryParam (final int userPage) {
        given ().when ()
            .queryParam ("page", userPage)
            .get (URL)
            .then ()
            .statusCode (200)
            .and ()
            .assertThat ()
            .body ("page", equalTo (userPage));

        final String responseBody = given ().when ()
            .queryParam ("page", userPage)
            .get (URL)
            .getBody ()
            .asString ();
        this.log.info (responseBody);
    }

}
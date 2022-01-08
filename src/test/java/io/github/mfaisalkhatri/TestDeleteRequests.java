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

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

import static io.restassured.RestAssured.given;

/**
 * Created By Faisal Khatri on 20-11-2021
 */
public class TestDeleteRequests {

    private static final String URL = "https://reqres.in/api/users/";

    /**
     * Created By Faisal Khatri on 20-11-2021
     *
     * @return deleteUserData using rest assured
     */
    @DataProvider (name = "deleteUserRestAssured")
    public Iterator<Object []> deleteRestUsers () {
        final List<Object []> deleteData = new ArrayList<> ();
        deleteData.add (new Object [] { 2 });
        return deleteData.iterator ();
    }

    /**
     * Executing delete requests using Rest-assured. Created By Faisal Khatri on
     * 20-11-2021
     *
     * @param userId
     */
    @Test (dataProvider = "deleteUserRestAssured")
    public void deleteRequestTests (final int userId) {
        given ().when ()
            .delete (URL + userId)
            .then ()
            .assertThat ()
            .statusCode (204);
    }
}
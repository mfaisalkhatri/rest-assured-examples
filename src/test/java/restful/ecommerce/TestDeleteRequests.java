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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import data.restful.ecommerce.AuthenticationPojo;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Created By Faisal Khatri on 20-11-2021
 */
@Epic ("Rest Assured POC - Example Tests")
@Feature ("Performing different API Tests using Rest-Assured")
public class TestDeleteRequests {

    private static final String URL = "http://localhost:3004";
    private              String token;

    @BeforeClass
    public void setup () {
        getToken ();
    }

    @DataProvider (name = "deleteOrder")
    public Iterator<Object[]> deleteRestUsers () {
        final List<Object[]> deleteData = new ArrayList<> ();
        deleteData.add (new Object[] { 4 });
        return deleteData.iterator ();
    }

    @Test (dataProvider = "deleteOrder")
    @Description ("Example Test for executing DELETE request using rest assured")
    @Severity (SeverityLevel.NORMAL)
    @Story ("Execute Delete requests using rest-assured")
    public void deleteRequestTests (final int orderId) {
        given ().header ("Authorization", this.token)
            .when ()
            .delete (URL + "/deleteOrder/" + orderId)
            .then ()
            .assertThat ()
            .statusCode (204);
    }

    @Test ()
    @Description ("Testing Delete All order EndPoint")
    @Severity (SeverityLevel.NORMAL)
    @Story ("Execute Delete requests using rest-assured")
    public void deleteAllOrderTests () {
        given ().header ("Authorization", this.token)
            .when ()
            .delete (URL + "/deleteOrderAllOrders")
            .then ()
            .assertThat ()
            .statusCode (204);
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
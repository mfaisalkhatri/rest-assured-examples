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

@Epic ("Rest Assured POC - Example Tests")
@Feature ("Performing different API Tests using Rest-Assured")
public class TestPostRequests {

    private static final Logger LOG = LogManager.getLogger (TestPostRequests.class);
    private static final String URL = "http://localhost:3004";

    @DataProvider (name = "postData")
    public Iterator<Object[]> postData () {
        final List<Object[]> postData = new ArrayList<> ();
        postData.add (new Object[] { "USR001", "PRD101", "Wireless Mouse", 1200, 2, 432, 2832 });
        postData.add (new Object[] { "USR002", "PRD102", "Mechanical Keyboard", 3500, 1, 630, 4130 });
        postData.add (new Object[] { "USR003", "PRD103", "USB-C Hub", 1800, 3, 972, 6372 });
        postData.add (new Object[] { "USR004", "PRD104", "27 Inch Monitor", 15000, 1, 2700, 17700 });
        return postData.iterator ();
    }

    @Test (dataProvider = "postData")
    @Description ("Example Test for executing POST request using rest assured")
    @Severity (SeverityLevel.CRITICAL)
    @Story ("Execute Post requests using rest-assured")
    public void testPostRequests (final String userId, final String productId, final String productName,
        final int productAmount, final int qty, final int taxAmt, final int totalAmt) {

        final PostData postData = new PostData (userId, productId, productName, productAmount, qty, taxAmt, totalAmt);

        final List<PostData> orders = new ArrayList<> ();
        orders.add (postData);

        final String response = given ().contentType (ContentType.JSON)
            .body (orders)
            .when ()
            .log ()
            .all ()
            .post (URL + "/addOrder")
            .then ()
            .log ()
            .all ()
            .assertThat ()
            .statusCode (201)
            .and ()
            .assertThat ()
            .body ("message", equalTo ("Orders added successfully!"))
            .and ()
            .assertThat ()
            .and ()
            .extract ()
            .response ()
            .body ()
            .asString ();
        
        LOG.info (response);

    }
}
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
import java.util.List;

import data.restfulecommerce.OrderData;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import net.datafaker.Faker;
import org.testng.annotations.Test;

/**
 * Created By Faisal Khatri on 20-11-2021
 */

@Epic ("Rest Assured POC - Example Tests")
@Feature ("Performing different API Tests using Rest-Assured")
public class PostRequestBuilderExampleTest extends SetupSpecification {

    @Test
    @Description ("Example of using Builder Pattern to pass test data in tests")
    @Severity (SeverityLevel.BLOCKER)
    @Story ("Builder Pattern Example using rest assured")
    public void postTestUsingBuilderPattern () {
        final OrderData orderData = orderDataBuilder ();
        final List<OrderData> orderlist = new ArrayList<> ();
        orderlist.add (orderData);
        given ().body (orderlist)
            .when ()
            .post ("/addOrder")
            .then ()
            .statusCode (201)
            .and ()
            .assertThat ()
            .body ("orders[5].user_id", equalTo (orderData.getUserId ()))
            .body ("orders[5].product_id", equalTo (orderData.getProductId ()))
            .body ("orders[5].product_name", equalTo (orderData.getProductName ()))
            .body ("orders[5].total_amt", equalTo (orderData.getTotalAmt ()));
    }

    private OrderData orderDataBuilder () {
        final Faker faker = new Faker ();
        return OrderData.builder ()
            .userId (String.valueOf (faker.number ()
                .numberBetween (200, 399)))
            .productId (String.valueOf (faker.number ()
                .numberBetween (1, 2000)))
            .productName (faker.internet ()
                .domainName ())
            .productAmount (faker.number ()
                .numberBetween (1, 499))
            .qty (faker.number ()
                .numberBetween (1, 15))
            .taxAmt (faker.number ()
                .numberBetween (1, 399))
            .totalAmt (faker.number ()
                .numberBetween (100, 1999))
            .build ();
    }
}
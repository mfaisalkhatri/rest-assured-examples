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

import data.restfulbooker.UserData;
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
public class PostRequestBuilderExample extends SetupSpecification {

    @Test
    @Description ("Example of using Builder Pattern to pass test data in tests")
    @Severity (SeverityLevel.BLOCKER)
    @Story ("Builder Pattern Example using rest assured")
    public void postTestUsingBuilderPattern () {
        UserData userData = userDataBuilder ();
        given ().body (userData)
            .when ()
            .post ("/api/users")
            .then ()
            .statusCode (201)
            .and ()
            .assertThat ()
            .body ("name", equalTo (userData.getName ()))
            .body ("job", equalTo (userData.getJob ()));

    }

    private UserData userDataBuilder () {
        Faker faker = new Faker ();
        return UserData.builder ()
            .name (faker.name ()
                .firstName ())
            .job (faker.company ()
                .profession ())
            .build ();
    }

}
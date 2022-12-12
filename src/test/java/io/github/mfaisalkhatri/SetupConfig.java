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

import static org.hamcrest.Matchers.lessThan;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeClass;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

/**
 * Created By Faisal Khatri on 20-11-2021
 */

public class SetupConfig {

    @BeforeClass
    @Description ("Set up the base test for running regression tests")
    @Severity (SeverityLevel.BLOCKER)
    @Epic ("Rest Assured POC - Example Tests")
    @Feature ("Performing different API Tests using Rest-Assured")
    public void setup () {

        RequestSpecification request = new RequestSpecBuilder ().addHeader ("Content-Type", "application/json")
            .setBaseUri ("https://reqres.in/")
            .addHeader ("Accept", "application/json")
            .addFilter (new RequestLoggingFilter ())
            .addFilter (new ResponseLoggingFilter ())
            .build ();

        ResponseSpecification response = new ResponseSpecBuilder ().expectResponseTime (lessThan (5000L))
            .build ();

        RestAssured.requestSpecification = request;
        RestAssured.responseSpecification = response;
    }

}
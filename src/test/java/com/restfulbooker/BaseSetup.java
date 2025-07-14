package com.restfulbooker;

import static org.hamcrest.Matchers.lessThan;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

/**
 * Created By Faisal Khatri on 18-02-2022
 */
public class BaseSetup {
    @Parameters ("agent")
    @BeforeClass
    public void setup (String agent) {
        String baseUri = agent.equals ("githubActions")
                         ? "http://localhost:3001"
                         : "https://restful-booker.herokuapp.com";
        RequestSpecification requestSpecification = new RequestSpecBuilder ().setBaseUri (baseUri)
            .addHeader ("Content-Type", "application/json")
            .addHeader ("Accept", "application/json")
            .addFilter (new RequestLoggingFilter ())
            .addFilter (new ResponseLoggingFilter ())
            .build ();

        ResponseSpecification responseSpecification = new ResponseSpecBuilder ().expectResponseTime (lessThan (20000L))
            .build ();

        RestAssured.requestSpecification = requestSpecification;
        RestAssured.responseSpecification = responseSpecification;

    }
}
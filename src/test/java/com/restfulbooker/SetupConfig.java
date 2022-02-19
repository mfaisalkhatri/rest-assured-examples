package com.restfulbooker;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;

import static org.hamcrest.Matchers.lessThan;

/**
 * Created By Faisal Khatri on 18-02-2022
 */
public class SetupConfig {

    @BeforeClass
    public void setup () {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";

        RequestSpecification request = new RequestSpecBuilder().addHeader ("Content-Type", "application/json")
                .addHeader ("Accept", "application/json")
                .addFilter (new RequestLoggingFilter())
                .addFilter (new ResponseLoggingFilter())
                .build ();

        ResponseSpecification response = new ResponseSpecBuilder().expectResponseTime (lessThan (20000L))
                .build ();

        RestAssured.requestSpecification = request;
        RestAssured.responseSpecification = response;

    }
}
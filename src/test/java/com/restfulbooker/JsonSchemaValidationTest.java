package com.restfulbooker;

import static data.restfulbooker.BookingDataBuilder.getBookingData;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.File;
import java.io.InputStream;

import data.restfulbooker.BookingData;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.testng.annotations.Test;

/**
 * @author Faisal Khatri
 * @since 8/24/2022
 **/
public class JsonSchemaValidationTest extends BaseSetup {

    @Test
    public void testCreateBookingJsonSchema() {

        BookingData newBooking = getBookingData ();

        InputStream file =  getClass().getClassLoader().getResourceAsStream("createbookingjsonschema.json");
        String response = given().body (newBooking)
            .when().post("/booking").getBody ().asString ();

        assertThat(response, matchesJsonSchema(file));
    }
    

}

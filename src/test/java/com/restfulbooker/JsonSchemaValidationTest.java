package com.restfulbooker;

import static data.restfulbooker.BookingDataBuilder.getBookingData;
import static data.restfulbooker.BookingDataBuilder.getPartialBookingData;
import static data.restfulbooker.TokenBuilder.getToken;
import static io.restassured.RestAssured.given;

import java.io.InputStream;

import data.restfulbooker.BookingData;
import data.restfulbooker.PartialBookingData;
import data.restfulbooker.Tokencreds;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * @author Faisal Khatri
 * @since 8/24/2022
 **/
public class JsonSchemaValidationTest extends BaseSetup {

    private BookingData        newBooking;
    private BookingData        updatedBooking;
    private PartialBookingData partialUpdateBooking;
    private Tokencreds         tokenCreds;
    private int                bookingId;

    @BeforeTest
    public void testSetup () {
        newBooking = getBookingData ();
        updatedBooking = getBookingData ();
        partialUpdateBooking = getPartialBookingData ();
        tokenCreds = getToken ();
    }

    @Test
    public void testCreateBookingJsonSchema () {

        BookingData newBooking = getBookingData ();
        InputStream createBookingJsonSchema = getClass ().getClassLoader ()
            .getResourceAsStream ("createbookingjsonschema.json");
        bookingId = given ().body (newBooking)
            .when ()
            .post ("/booking")
            .then ()
            .statusCode (200)
            .and()
            .assertThat ()
            .body (JsonSchemaValidator.matchesJsonSchema (createBookingJsonSchema))
            .and ()
            .extract ()
            .path ("bookingid");
    }

    @Test
    public void testGetBookingJsonSchema () {

        InputStream getBookingJsonSchema = getClass ().getClassLoader ()
            .getResourceAsStream ("getbookingjsonschema.json");

        given ().when ()
            .get ("/booking/" + bookingId)
            .then ()
            .statusCode (200)
            .assertThat ()
            .body (JsonSchemaValidator.matchesJsonSchema (getBookingJsonSchema));
    }

    @Test
    public void testUpdateBookingJsonSchema() {
        InputStream updateBookingJsonSchema = getClass ().getClassLoader ()
            .getResourceAsStream ("updatebookingjsonschema.json");

        given ().when ().body (updatedBooking)
            .get ("/booking/" + bookingId)
            .then ()
            .statusCode (200)
            .assertThat ()
            .body (JsonSchemaValidator.matchesJsonSchema (updateBookingJsonSchema));
    }

    @Test
    public void testUpdatePartialBookingJsonSchema() {
        InputStream updatePartialBookingJsonSchema = getClass ().getClassLoader ()
            .getResourceAsStream ("updatepartialbookingjsonschema.json");

        given ().when ().body (partialUpdateBooking)
            .get ("/booking/" + bookingId)
            .then ()
            .statusCode (200)
            .assertThat ()
            .body (JsonSchemaValidator.matchesJsonSchema (updatePartialBookingJsonSchema));
    }

    @Test
    public void testCreateJsonSchema() {
        InputStream createTokenJsonSchema = getClass ().getClassLoader ()
            .getResourceAsStream ("createtokenjsonschema.json");

        given ().body (tokenCreds)
            .when ()
            .post ("/auth")
            .then ()
            .statusCode (200)
            .and ()
            .assertThat ()
            .body (JsonSchemaValidator.matchesJsonSchema (createTokenJsonSchema));
    }
}

package com.restfulbooker;

import static data.restfulbooker.BookingDataBuilder.*;
import static data.restfulbooker.TokenBuilder.getToken;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import data.restfulbooker.BookingData;
import data.restfulbooker.PartialBookingData;
import data.restfulbooker.Tokencreds;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Created By Faisal Khatri on 18-02-2022
 */
@Epic ("Rest Assured POC - Example Tests")
@Feature ("Writing End to End tests using rest-assured")
public class RestfulBookerE2ETests extends BaseSetup {

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
    @Description ("Example test for creating new booking - Post request")
    @Severity (SeverityLevel.BLOCKER)
    @Story ("End to End tests using rest-assured")
    @Step("Create new booking")
    public void createBookingTest () {
        bookingId = given ().body (newBooking)
            .when ()
            .post ("/booking")
            .then ()
            .statusCode (200)
            .and ()
            .assertThat ()
            .body ("bookingid", notNullValue ())
            .body ("booking.firstname", equalTo (newBooking.getFirstname ()))
            .body ("booking.lastname", equalTo (newBooking.getLastname ()))
            .body ("booking.totalprice", equalTo (newBooking.getTotalprice ()))
            .body ("booking.depositpaid", equalTo (newBooking.isDepositpaid ()))
            .body ("booking.bookingdates.checkin", equalTo (newBooking.getBookingdates ()
                .getCheckin ()))
            .body ("booking.bookingdates.checkout", equalTo (newBooking.getBookingdates ()
                .getCheckout ()))
            .body ("booking.additionalneeds", equalTo (newBooking.getAdditionalneeds ()))
            .extract ()
            .path ("bookingid");
    }

    @Test
    @Description ("Example test for retrieving a booking - Get request")
    @Severity (SeverityLevel.CRITICAL)
    @Story ("End to End tests using rest-assured")
    @Step("Get a the newly created booking")
    public void getBookingTest () {
        given ().get ("/booking/" + bookingId)
            .then ()
            .statusCode (200)
            .and ()
            .assertThat ()
            .body ("firstname", equalTo (newBooking.getFirstname ()))
            .body ("lastname", equalTo (newBooking.getLastname ()))
            .body ("totalprice", equalTo (newBooking.getTotalprice ()))
            .body ("depositpaid", equalTo (newBooking.isDepositpaid ()))
            .body ("bookingdates.checkin", equalTo (newBooking.getBookingdates ()
                .getCheckin ()))
            .body ("bookingdates.checkout", equalTo (newBooking.getBookingdates ()
                .getCheckout ()))
            .body ("additionalneeds", equalTo (newBooking.getAdditionalneeds ()));

    }

    @Test
    @Description ("Example test for updating a booking - Put request")
    @Severity (SeverityLevel.NORMAL)
    @Story ("End to End tests using rest-assured")
    @Step("Update the booking")
    public void updateBookingTest () {
        given ().body (updatedBooking)
            .when ()
            .header ("Cookie", "token=" + generateToken ())
            .put ("/booking/" + bookingId)
            .then ()
            .statusCode (200)
            .and ()
            .assertThat ()
            .body ("firstname", equalTo (updatedBooking.getFirstname ()))
            .body ("lastname", equalTo (updatedBooking.getLastname ()))
            .body ("totalprice", equalTo (updatedBooking.getTotalprice ()))
            .body ("depositpaid", equalTo (updatedBooking.isDepositpaid ()))
            .body ("bookingdates.checkin", equalTo (updatedBooking.getBookingdates ()
                .getCheckin ()))
            .body ("bookingdates.checkout", equalTo (updatedBooking.getBookingdates ()
                .getCheckout ()))
            .body ("additionalneeds", equalTo (updatedBooking.getAdditionalneeds ()));

    }

    @Test
    @Description ("Example test for updating a booking partially- Patch request")
    @Severity (SeverityLevel.NORMAL)
    @Story ("End to End tests using rest-assured")
    @Step("Update the booking partially")
    public void updatePartialBookingTest () {
        given ().body (partialUpdateBooking)
            .when ()
            .header ("Cookie", "token=" + generateToken ())
            .patch ("/booking/" + bookingId)
            .then ()
            .statusCode (200)
            .and ()
            .assertThat ()
            .body ("firstname", equalTo (partialUpdateBooking.getFirstname ()))
            .body ("lastname", equalTo (updatedBooking.getLastname ()))
            .body ("totalprice", equalTo (partialUpdateBooking.getTotalprice ()))
            .body ("depositpaid", equalTo (updatedBooking.isDepositpaid ()))
            .body ("bookingdates.checkin", equalTo (updatedBooking.getBookingdates ()
                .getCheckin ()))
            .body ("bookingdates.checkout", equalTo (updatedBooking.getBookingdates ()
                .getCheckout ()))
            .body ("additionalneeds", equalTo (updatedBooking.getAdditionalneeds ()));

    }

    @Test
    @Description ("Example test for deleting a booking - Delete request")
    @Severity (SeverityLevel.NORMAL)
    @Story ("End to End tests using rest-assured")
    @Step("Delete the booking")
    public void deleteBookingTest () {
        given ().header ("Cookie", "token=" + generateToken ())
            .when ()
            .delete ("/booking/" + bookingId)
            .then ()
            .statusCode (201);
    }

    @Test
    @Description ("Example test for checking if booking is deleted by retrieving a deleted booking - Get request")
    @Severity (SeverityLevel.NORMAL)
    @Story ("End to End tests using rest-assured")
    @Step("Check by retrieving deleted booking")
    public void checkBookingIsDeleted () {
        given ().get ("/booking/" + bookingId)
            .then ()
            .statusCode (404);
    }

    private String generateToken () {
        return given ().body (tokenCreds)
            .when ()
            .post ("/auth")
            .then ()
            .statusCode (200)
            .and ()
            .assertThat ()
            .body ("token", Matchers.not (nullValue ()))
            .extract ()
            .path ("token");
    }
}
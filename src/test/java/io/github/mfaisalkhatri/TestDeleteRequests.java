package io.github.mfaisalkhatri;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

import static io.restassured.RestAssured.given;

/**
 * Created By Faisal Khatri on 20-11-2021
 */
public class TestDeleteRequests {

    private static final String URL = "https://reqres.in/api/users/";

    /**
     * Created By Faisal Khatri on 20-11-2021
     *
     * @return deleteUserData using rest assured
     */
    @DataProvider (name = "deleteUserRestAssured")
    public Iterator<Object []> deleteRestUsers () {
        final List<Object []> deleteData = new ArrayList<> ();
        deleteData.add (new Object [] { 2 });
        return deleteData.iterator ();
    }

    /**
     * Executing delete requests using Rest-assured. Created By Faisal Khatri on
     * 20-11-2021
     *
     * @param userId
     */
    @Test (dataProvider = "deleteUserRestAssured")
    public void deleteRequestTests (final int userId) {
        given ().when ()
            .delete (URL + userId)
            .then ()
            .assertThat ()
            .statusCode (204);
    }
}
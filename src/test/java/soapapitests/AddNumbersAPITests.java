package soapapitests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.stream.Stream;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.path.xml.XmlPath;
import io.restassured.path.xml.config.XmlPathConfig;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

/**
 * @author Faisal Khatri
 * @since 8/28/2022
 **/
public class AddNumbersAPITests {

    private static final String BASE_URL = "http://www.dneonline.com/calculator.asmx";

    @Test
    @Description ("Adding two numbers using SOAP API")
    @Epic ("Rest Assured POC - Example Tests")
    @Story ("Performing SOAP API Tests using Rest-Assured")
    public void testAddNumbers () {

        URI file = null;
        try {
            file = getClass ().getClassLoader ()
                .getResource ("addrequest.xml")
                .toURI ();
        } catch (URISyntaxException e) {
            throw new Error ("Error in URI syntax", e);
        }
        File body = new File (file);

        String response = given ().when ()
            .body (body)
            .header ("SOAPAction", "http://tempuri.org/Add")
            .contentType ("text/xml; charset=utf-8")
            .log ()
            .all ()
            .post (BASE_URL)
            .then ()
            .log ()
            .all ()
            .statusCode (200)
            .extract ()
            .response ()
            .asString ();

        XmlPath xmlpath = new XmlPath (response).using (new XmlPathConfig ("UTF-8"));
        assertEquals (xmlpath.getString ("AddResult"), "10");

    }
}

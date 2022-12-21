package soapapitests;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import io.restassured.path.xml.XmlPath;
import io.restassured.path.xml.config.XmlPathConfig;
import org.testng.annotations.Test;

/**
 * @author Faisal Khatri
 * @since 8/28/2022
 **/
@Epic ("Rest Assured POC - Example Tests")
public class NumberToWordsTest {

    private static final String BASE_URL = "https://www.dataaccess.com/webservicesserver/NumberConversion.wso";

    @Test
    @Description ("Printing numbers to words using SOAP API")
    @Story ("Performing SOAP API Tests using Rest-Assured")
    public void testNumberToWordsAPI () {
        URI file = null;
        try {
            file = getClass ().getClassLoader ()
                .getResource ("numbertowordrequest.xml")
                .toURI ();
        } catch (URISyntaxException e) {
            throw new Error ("Error in URI syntax", e);
        }
        File body = new File (file);

        String response = given ().when ()
            .body (body)
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
        assertEquals (xmlpath.getString ("m:NumberToWordsResult"), "five thousand nine hundred and fifty eight ");
    }
}

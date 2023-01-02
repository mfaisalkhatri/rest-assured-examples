package fileupload;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

/**
 * @author Faisal Khatri
 * @since 1/2/2023
 **/
@Epic ("Rest Assured POC - Example Tests")
@Feature ("Performing different API Tests using Rest-Assured")
public class MultiPartFileUploadTest {

    private static final String URL = "http://postman-echo.com/post";

    @Test
    @Description ("Example Test for uploading file using rest assured")
    @Severity (SeverityLevel.NORMAL)
    @Story ("Uploading file using rest assured")
    public void testFileUpload () {

        String fileName = "calc.csv";
        FileReader fileReader = new FileReader ();

        given ().when ()
            .log ()
            .all ()
            .contentType ("multipart/form-data")
            .multiPart (fileReader.fileToUpload (fileName))
            .when ()
            .post (URL)
            .then ()
            .statusCode (200)
            .log ()
            .all ()
            .and ()
            .assertThat ()
            .body ("files", hasKey (fileName), "files.'calc.csv'", is (notNullValue ()));

    }
}


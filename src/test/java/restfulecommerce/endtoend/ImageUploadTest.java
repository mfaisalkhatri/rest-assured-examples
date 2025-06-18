package restfulecommerce.endtoend;

import static data.restfulecommerce.AuthDataBuilder.getAuthData;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.startsWith;
import static restfulecommerce.endtoend.FileReader.getFile;

import data.restfulecommerce.AuthData;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ImageUploadTest {

    private String token;

    @BeforeTest
    public void beforeTest () {
        RequestSpecification requestSpecification = new RequestSpecBuilder ().setBaseUri ("http://localhost:3004")
            .addFilter (new RequestLoggingFilter ())
            .addFilter (new ResponseLoggingFilter ())
            .build ();

        ResponseSpecification responseSpecification = new ResponseSpecBuilder ().expectResponseTime (lessThan (20000L))
            .build ();

        RestAssured.requestSpecification = requestSpecification;
        RestAssured.responseSpecification = responseSpecification;
    }

    @Test
    public void testCreateToken () {
        AuthData authData = getAuthData ();
        token = given ().when ()
            .header ("Accept", "application/json")
            .contentType (ContentType.JSON)
            .body (authData)
            .post ("/auth")
            .then ()
            .assertThat ()
            .statusCode (201)
            .and ()
            .assertThat ()
            .body ("message", equalTo ("Authentication Successful!"))
            .body ("token", notNullValue ())
            .extract ()
            .path ("token");
    }

    @Test
    public void testUploadImage () {
        String fileName = "sample_image.png";
        given ().header ("Authorization", token)
            .contentType (ContentType.MULTIPART)
            .multiPart ("image", getFile (fileName), "image/png")
            .when ()
            .post ("/imageUpload")
            .then ()
            .statusCode (200)
            .and ()
            .assertThat ()
            .body ("message", equalTo ("File uploaded successfully!"))
            .body ("file.originalName", equalTo (fileName))
            .body ("file.path", startsWith ("uploads/"))
            .body ("file.size", notNullValue ());
    }
}

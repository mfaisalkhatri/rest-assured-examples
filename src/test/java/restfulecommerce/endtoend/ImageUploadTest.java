package restfulecommerce.endtoend;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static restfulecommerce.endtoend.FileReader.getFile;

import org.testng.annotations.Test;

public class ImageUploadTest {

    //Set the config and token

    @Test
    public void testUploadImage () {

        given ().header ("Authorization", token)
            .multiPart ("file", getFile ("sample_image.png"))
            .when ()
            .post ("/imageUpload")
            .then ()
            .statusCode (200)
            .and ()
            .assertThat ()
            .body ("message", equalTo ("File uploaded Successfully"));
    }
}

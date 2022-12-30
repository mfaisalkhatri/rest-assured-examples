package assertionexamples;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.emptyArray;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToCompressingWhiteSpace;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.startsWith;

import org.testng.annotations.Test;

/**
 * @author Faisal Khatri
 * @since 12/30/2022
 **/
public class StringRelatedAssertionTests {

    private static final String URL = "https://reqres.in/api/users/";

    @Test
    public void testStringAssertions () {

        given ().when ()
            .queryParam ("page", 2)
            .get (URL)
            .then ()
            .log ()
            .all ()
            .statusCode (200)
            .and ()
            .assertThat ()
            .body ("data[0].first_name", equalTo ("Michael"))
            .body ("data[0].first_name", equalToIgnoringCase ("MICHael"))
            .body ("data[0].email", containsString ("michael.lawson"))
            .body ("data[0].last_name", startsWith ("L"))
            .body ("data[0].last_name", endsWith ("n"))
            .body ("data[1].first_name", equalToCompressingWhiteSpace ("    Lindsay "));
    }

    @Test
    public void testNotNullAssertions () {
        given ().when ()
            .queryParam ("page", 2)
            .get (URL)
            .then ()
            .log ()
            .all ()
            .statusCode (200)
            .and ()
            .assertThat ()
            .body ("data[0].first_name", is (notNullValue ()));
    }

    @Test
    public void testHasKeyAssertion () {
        given ().when ()
            .queryParam ("page", 2)
            .get (URL)
            .then ()
            .log ()
            .all ()
            .statusCode (200)
            .and ()
            .assertThat ()
            .body ("data[0]", hasKey ("email"))
            .body ("support", hasKey ("url"))
            .body ("$", hasKey ("page"))
            .body ("$", hasKey ("total"));
    }

    @Test
    public void testNotAssertions () {
        given ().when ()
            .queryParam ("page", 2)
            .get (URL)
            .then ()
            .log ()
            .all ()
            .statusCode (200)
            .and ()
            .assertThat ()
            .body ("data", not (emptyArray ()))
            .body ("data[0].first_name", not (equalTo ("George")))
            .body ("data.size()", greaterThan (5));
    }

    @Test
    public void testMultipleAssertStatement () {
        given ().when ()
            .queryParam ("page", 2)
            .get (URL)
            .then ()
            .log ()
            .all ()
            .statusCode (200)
            .and ()
            .assertThat ()
            .body ("page", equalTo (2), "data[0].first_name", equalTo ("Michael"), "support.url", is (notNullValue ()));

    }
}
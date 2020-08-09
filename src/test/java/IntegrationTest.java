import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

public class IntegrationTest extends BaseClass {

    @Test
    public void firstXmlTest() {

        RestAssured.baseURI = "http://" + host;
        //  String endpoint = "/xml";

        Response r = RestAssured.given()
                //  .cookie(c)
                //  .auth()
                //  .digest(username, password)
                //  .param("Format", format)
                //  .param("Class", "")
                //  .when()
                .get("/xml")
                .then()
                .contentType(ContentType.XML)
                .extract().response();
        // System.out.println(r.asString());

        XmlPath xp = r.xmlPath();

        System.out.println(xp.get("slideshow@title"));
        // Assert.assertEquals("Sample Slide Show",   xp.get("slideshow.title"));

    }

    @Test
    public void firstJsonTest() {
        RestAssured.baseURI = "http://" + host;

        Response r = RestAssured.given()
                .get("/json")
                .then()
                .contentType(ContentType.JSON)
                .extract().response();
        // System.out.println(r.asString());

        JsonPath jp = r.jsonPath();
        System.out.println(jp.get("slideshow.author").toString());
        Assert.assertEquals("Yours Truly", jp.get("slideshow.author").toString());
    }
}


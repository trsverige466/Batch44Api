package get_http_request.tekrar;

import base_url.RegresinBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class T7 extends RegresinBaseUrl {
     /*
https://reqres.in/api/users URL request olustur.
body icerisindeki idsi 5 olan datayi
1) Matcher CLASS ile
2) JsonPath ile dogrulayin.
*/
    @Test
    public void GetRequest07(){
        spec01.pathParams("prmt1","api","prmt2","users");
        Response response = given().spec(spec01).when().get("/{prmt1}/{prmt2}");

        response.prettyPrint();

        response.then().assertThat().body("data[0].email", Matchers.equalTo("george.bluth@reqres.in"),
                "data[0].first_name",Matchers.equalTo("George"),
                "data[0].last_name",Matchers.equalTo("Bluth"),
                "data[0].avatar",Matchers.equalTo("https://reqres.in/img/faces/1-image.jpg"));

        JsonPath json = response.jsonPath();
        Assert.assertEquals("george.bluth@reqres.in",json.getString("data[0].email"));
        Assert.assertEquals("George",json.getString("data[0].first_name"));
        Assert.assertEquals("Bluth",json.getString("data[0].last_name"));
        Assert.assertEquals("https://reqres.in/img/faces/1-image.jpg",json.getString("data[0].avatar"));
    }
}

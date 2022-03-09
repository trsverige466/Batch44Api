package get_http_request.tekrar;

import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class T2 {

    @Test
    public void test02(){

        String url="https://reqres.in/api/users";

        Response response = given().when().get(url);

        response.prettyPeek();

        //headers

        response.then().statusCode(200).contentType("application/json; charset=utf-8").statusLine("HTTP/1.1 200 OK");

        //body
        response.then().body("data[0].first_name", equalTo("George")
                , "data[0].last_name", equalTo("Bluth")
                , "data[1].email", equalTo("janet.weaver@reqres.in"));

    }
}

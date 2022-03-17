package get_http_request.day06;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest11 {

    String endPoint="http://www.gmibank.com/api/tp-customers";
    String bearerToken= "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtdXJhdHRhbmMiLCJhdXRoIjoiUk9MRV9BRE1JTiIsImV4cCI6MTY0NjI0NzQyN30.gM95vAWCDXArV-bdKlUPlRiJJhSnUIFecRAJpnKWG9Aj2dbzN4Xc2dDxKp-yQeBDpes8rF_V26ux9ih-AUa5Nw";

    @Test
    public void test(){

        Response response = given()
                .header("Authorization","Bearer " + bearerToken)
                .when().get(endPoint).then().extract().response();

        response.prettyPrint();
    }
}

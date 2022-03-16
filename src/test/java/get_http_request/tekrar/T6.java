package get_http_request.tekrar;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class T6 {
      /*https://restful-booker.herokuapp.com/booking/5 url’ine
accept type’i “application/json” olan GET request’i yolladigimda
gelen response’un
status kodunun 200
ve content type’inin “application/json”
ve firstname’in “Jim”
ve totalprice’in 600
ve checkin date’in 2015-06-12"oldugunu test edin

     */
    @Test
    public void test01(){

        String url= "https://restful-booker.herokuapp.com/booking/5";

        Response response = given().when().get(url);

        response.prettyPeek();

        HashMap<String , Object> bookingdates= new HashMap<>();
        bookingdates.put("checkin","2017-11-18");
        bookingdates.put("checkout","2021-07-03");

        HashMap<String , Object> expectedData= new HashMap<>();
        expectedData.put("firstname","Susan");
        expectedData.put("totalprice",381);
        expectedData.put("bookingdates",bookingdates);

        System.out.println(expectedData);

        response.then().assertThat().statusCode(200).contentType("application/json")
                .body("firstname", equalTo(expectedData.get("firstname")),"totalprice",equalTo(381)
                ,"bookingdates.checkin",equalTo(
                                ((Map) (expectedData.get("bookingdates"))).get("checkin")
                        ));

        JsonPath json = response.jsonPath();

        /*{
    "firstname": "Susan",
    "lastname": "Jones",
    "totalprice": 381,
    "depositpaid": false,
    "bookingdates": {
        "checkin": "2017-11-18",
        "checkout": "2021-07-03"
    }
}

         */
        Assert.assertEquals(json.getString("firstname"),expectedData.get("firstname"));
        Assert.assertEquals(json.getInt("totalprice"),expectedData.get("totalprice"));
        Assert.assertEquals(json.getString("bookingdates.checkin"), ((Map) (expectedData.get("bookingdates"))).get("checkin"));

    }
}

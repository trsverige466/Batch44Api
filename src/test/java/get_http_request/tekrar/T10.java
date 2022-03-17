package get_http_request.tekrar;

import base_url.DummyBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class T10 extends DummyBaseUrl {
     /*
    http://dummy.restapiexample.com/api/v1/employees
    url ine bir istek gönderildiğinde,
    status kodun 200,
    gelen body de,
    5. çalışanın isminin "Airi Satou" olduğunu ,
    6. çalışanın maaşının "372000" olduğunu ,
    Toplam 24 tane çalışan olduğunu,
    "Rhona Davidson" ın employee lerden biri olduğunu
    "21", "23", "61" yaşlarında employeeler olduğunu test edin
    JSONPATH KULLARAK
*/
    @Test
    public void test10(){
        spec02.pathParams("bir","api","iki","v1","uc","employees");

        Response response = given().spec(spec02).when().get("/{bir}/{iki}/{uc}");

        response.prettyPeek();

        JsonPath json = response.jsonPath();

        Assert.assertEquals(200,response.statusCode());
        Assert.assertEquals("application/json",response.contentType());

        Assert.assertEquals("Airi Satou",json.getString("data[4].employee_name"));

        Assert.assertEquals(137500,json.getInt("data[6].employee_salary"));

        Assert.assertEquals(24,json.getList("data.id").size());

        Assert.assertTrue(json.getList("data.employee_name").contains("Rhona Davidson"));

    List<Integer> ages= Arrays.asList(21,23,61);

    Assert.assertTrue(json.getList("data.employee_age").containsAll(ages));

    }
}

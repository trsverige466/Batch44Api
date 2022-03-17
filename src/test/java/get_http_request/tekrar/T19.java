package get_http_request.tekrar;

import base_url.DummyBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.List;


import static io.restassured.RestAssured.given;

public class T19 extends DummyBaseUrl {
    /*
   http://dummy.restapiexample.com/api/v1/employees
   1) Status kodunun 200,
   2) 10’dan büyük tüm id'leri ekrana yazdırın ve 10’dan büyük 14 id olduğunu,
   3) 30’dan küçük tüm yaşları ekrana yazdırın ve bu yaşların içerisinde en büyük yaşın 23 olduğunu
   4) Maası 350000 den büyük olan tüm employee name'leri ekrana yazdırın
      ve bunların içerisinde "Charde Marshall" olduğunu test edin
   */
    @Test
    public void test019(){
        spec02.pathParams("bir","api","iki","v1","uc","employees");

        Response response = given().spec(spec02).when().get("/{bir}/{iki}/{uc}");

        response.prettyPrint();

        //status code 200
        response.then().assertThat().statusCode(200);
        Assert.assertEquals(200,response.statusCode());

        //2) 10’dan büyük tüm id'leri ekrana yazdırın ve 10’dan büyük 14 id olduğunu,
        JsonPath json = response.jsonPath();

        List<Integer> idList= json.getList("data.findAll{it.id>10}.id");

        System.out.println("id listesi" + idList);

        //3) 30’dan küçük tüm yaşları ekrana yazdırın ve bu yaşların içerisinde en büyük yaşın 23 olduğunu

        List<Integer> yasListesi= json.getList("data.findAll{it.employee_age<30}.id");

        System.out.println("yas listesi : "+ yasListesi);

        Collections.sort(yasListesi);
        Assert.assertEquals((Integer) 24,yasListesi.get(yasListesi.size()-1));

        // 4) Maası 350000 den büyük olan tüm employee name'leri ekrana yazdırın
        //      ve bunların içerisinde "Charde Marshall" olduğunu test edin

        List<Integer>salaryList=json.getList("data.findAll{it.employee_salary>350000}.employee_name");
        System.out.println(salaryList);

        Assert.assertTrue(salaryList.contains("Charde Marshall"));
    }
}

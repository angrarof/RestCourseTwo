import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class GetRequestDemo {
    @BeforeClass
    public void setup(){
        RestAssured.baseURI="https://maps.googleapis.com";
        RestAssured.basePath ="/maps/api";
    }

    @Test
    public void statusCodeVerification(){
        /*Response res =  */given()
                .queryParam("origins","place_id:ChIJFw1Fq1xxVo8RCeurFVcV_F0")
                .queryParam("destinations","San+Francisco")
                .queryParam("language","en")
                .queryParam("key","AIzaSyDXbWvOa95j9u9TK8PzsG1vR4RvbOvVkR4")
        .when().get("/distancematrix/json")
        .then().log().body().assertThat().statusCode(200).body("rows[0].elements[0].status",equalTo("OK"));

        //System.out.println(res.asString());
    }
}

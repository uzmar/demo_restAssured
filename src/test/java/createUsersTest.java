import com.jayway.jsonpath.Configuration;
import framework.utils.BaseTest;
import framework.utils.userUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.Console;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.expect;
import static io.restassured.RestAssured.given;


public class createUsersTest extends BaseTest {

    String  userId= "", page, total, name, job;

    @Test(priority = 0, groups = {"Smoke", "Regression"}, description = "demo test", alwaysRun = true)
    public void testCreateUser() throws Exception {
        super.setup();
        String actualPayload = userUtils.getCreateUsersJson();
     //   Object document = Configuration.defaultConfiguration().jsonProvider().parse(actualPayload);
        Response response = given().log().all()
                .headers(headers)
                .contentType(ContentType.JSON)
                .when()
                .body(actualPayload)
                .post(BASE_URL  + "/users")
                .then().log().all()
                .assertThat().statusCode(201).and().extract().response();
        userId=response.jsonPath().get("id");
        name=response.jsonPath().get("name");
        job=response.jsonPath().get("job");

        Assert.assertEquals(name,"morpheus");
        Assert.assertEquals(job, "leader");

    }

    @Test(priority = 1, groups = {"Smoke", "Regression"}, description = "demo test", alwaysRun = true)
    public void testGetListOfUsers() throws Exception {
        super.setup();
        Map<String,String> params=new HashMap<>();
        params.put("page", "2");
        Response response = given().log().all()
                .headers(headers)
                .contentType(ContentType.JSON)
                .queryParams(params)
                .when()
                .get(BASE_URL+ "/users")
                .then().log().all()
                .assertThat().statusCode(200).and().extract().response();
        page=response.jsonPath().get("page").toString();
        total=response.jsonPath().get("total").toString();
        Assert.assertEquals( page,"2");
        Assert.assertEquals(total, "12");



    }
    @Test(priority = 2, groups = {"Regression"}, description = "demo test", alwaysRun = true)
    public void testGetUserById() throws Exception {
        super.setup();
        Response response = given().log().all()
                .headers(headers)
                .contentType(ContentType.JSON)
                .when()
                .get(BASE_URL+ "/users/2")
                .then().log().all()
                .assertThat().statusCode(200).and().extract().response();
        System.out.println(response);
        Assert.assertEquals(response.jsonPath().get("data.id").toString(),"2");
        Assert.assertEquals(response.jsonPath().get("data.email").toString(), "janet.weaver@reqres.in");

    }
}

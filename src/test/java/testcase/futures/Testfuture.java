package testcase.futures;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import testcase.BaseTest;

public class Testfuture extends BaseTest{

    @DataProvider(name="testfuturedata")
    public Object[] getData(){
        Object[] aaa = {"BTC-PERP","ETH-PERP"};
        return aaa;
    }

    @Test(dataProvider = "testfuturedata")
    public void testFuture(String market){
        RequestSpecification httpRequest = RestAssured.given();

        Response res= httpRequest.request(Method.GET, "/futures/" + market);
        String responseBody = res.getBody().asString();
        System.out.println(responseBody);
    }


}

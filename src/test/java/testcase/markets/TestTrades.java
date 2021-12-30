package testcase.markets;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import testcase.BaseTest;

public class TestTrades extends BaseTest {
    @Test
    public void testTrades(){
        RequestSpecification httpRequest = RestAssured.given();
        Response res= httpRequest.request(Method.GET, "/markets/btc/usd/trades");

        String responseBody = res.getBody().asString();
        System.out.println(responseBody);

        //body key validation
        Assert.assertEquals(responseBody.contains("liquidation"),true,"Liquidation is not inside Response Body");
    }
}

package testcase.orders;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestPlaceOrder {

    @Test
    public void testPlaceOrder() {
        RestAssured.baseURI = "https://ftx.com/api";
        RequestSpecification httpRequest = RestAssured.given();
        JSONObject requestParams = new JSONObject();

        requestParams.put("market", "BTC/USD");
        requestParams.put("side", "buy");
        requestParams.put("price", 1);
        requestParams.put("type", "limit");
        requestParams.put("size", 1);

        httpRequest.header("Content-Type", "application/json");
        httpRequest.body(requestParams.toJSONString()); //attach above data to the request

        Response res= httpRequest.request(Method.POST,"/orders");

        String responseBody = res.getBody().asString();
        System.out.println(responseBody);

        //status code validation
        int statusCode = res.getStatusCode();
        Assert.assertEquals(statusCode,201,"Status Code not match");

        //success code validation
        String successValue = res.jsonPath().get("success");
        Assert.assertEquals(successValue,"true","success not equal to True");

    }
}

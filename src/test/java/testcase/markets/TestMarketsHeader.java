package testcase.markets;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import testcase.BaseTest;

public class TestMarketsHeader extends BaseTest {
    @Test
    public void testMarketHeader(){
        RequestSpecification httpRequest = RestAssured.given();
        Response res= httpRequest.request(Method.GET,"markets/btc/usd");

        String contentType = res.header("Content-Type");
        String contentEncoding = res.header("Content-Encoding");

        String responseBody = res.getBody().asString();
        System.out.println(responseBody);

        //Header validation
        Assert.assertEquals(contentType,"application/json","Header Content Type not match");
        Assert.assertEquals(contentEncoding,"gzip","Header Content Encoding not match");

    }

    @Test
    public void testMarketAllHeaders(){
        RequestSpecification httpRequest = RestAssured.given();
        Response res= httpRequest.request(Method.GET,"markets/btc/usd");

        Headers allheaders = res.headers(); //capture all headers

        for(Header header : allheaders){
            System.out.println(header.getName() + " : " + header.getValue());
        }

    }
}

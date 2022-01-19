package testcase.markets;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import testcase.BaseTest;

public class TestHistoricalPrices extends BaseTest {
    @Test
    public void testHistoricalPrices(){
        RequestSpecification httpRequest = RestAssured.given();
        Response res= httpRequest.request(Method.GET,
                "/markets/btc/usd/candles?resolution=15" +
                        "&start_time=1640793880" +
                        "&end_time=1640793885");
        convertResponseResultToMap(res);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(hm.get("high"),"48012.0","High not match");
        softAssert.assertEquals(hm.get("low"),"47996.0","low not match");
        softAssert.assertEquals(hm.get("time"),"1.6407939E12","time not match");
        softAssert.assertEquals(hm.get("close"),"48012.0","close not match");
        softAssert.assertEquals(hm.get("volume"),"158167.06","volume not match");
        softAssert.assertEquals(hm.get("open"),"47997.0","open not match");
        softAssert.assertAll();
    }
}

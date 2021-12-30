package testcase.markets;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import testcase.BaseTest;

import java.util.HashMap;

public class TestHistoricalPrices extends BaseTest {
    @Test
    public void testHistoricalPrices(){
        RequestSpecification httpRequest = RestAssured.given();
        Response res= httpRequest.request(Method.GET,
                "/markets/btc/usd/candles?resolution=15" +
                        "&start_time=1640793880" +
                        "&end_time=1640793885");

        JsonPath jsonPath = res.jsonPath();

        HashMap<String,String> hm = new HashMap();
        String jsonPathString = jsonPath.get("result").toString().replace(" ","");
        String[] s = jsonPathString.substring(2,jsonPathString.length()-2).split(",");
        for(int i=0;i<s.length;i++){
            String[] sSplit = s[i].split("=");
            hm.put(sSplit[0],sSplit[1]);
        }

//        for (Map.Entry<String, String> set : hm.entrySet()) {
//            System.out.println(set.getKey() + " = " + set.getValue());
//        }

        Assert.assertEquals(hm.get("high"),"48012.0","High not match");
        Assert.assertEquals(hm.get("low"),"47996.0","low not match");
        Assert.assertEquals(hm.get("time"),"1.6407939E12","time not match");
        Assert.assertEquals(hm.get("close"),"48012.0","close not match");
        Assert.assertEquals(hm.get("volume"),"158167.06","volume not match");
        Assert.assertEquals(hm.get("startTime"),"2021-12-29T16:04:45+00:00","startTime not match");
        Assert.assertEquals(hm.get("open"),"47997.0","open not match");
    }
}

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestSingleMarket extends BaseTest{
    @Test
    public void testBtcUsdPrice(){
        RequestSpecification httpRequest = RestAssured.given();
        Response res= httpRequest.request(Method.GET,"markets/btc/usd");

        String contentType = res.header("Content-Type");
        String contentEncoding = res.header("Content-Encoding");

        String responseBody = res.getBody().asString();
        System.out.println(responseBody);

        //status code validation
        int statusCode = res.getStatusCode();
        Assert.assertEquals(statusCode,200,"Status Code not match");

        //status line validation
        String statusLine = res.getStatusLine();
        Assert.assertEquals(statusLine, "HTTP/1.1 200 OK","Status line not match");

        //Header validation
        Assert.assertEquals(contentType,"application/json","Header Content Type not match");
        Assert.assertEquals(contentEncoding,"gzip","Header Content Encoding not match");

    }
}

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestMarketStatus {

    @Test
    public void testBtcUsdPrice(){
        RestAssured.baseURI = "https://ftx.com/api/markets";
        RequestSpecification httpRequest = RestAssured.given();
        Response res= httpRequest.request(Method.GET,"/btc/usd");
        String responseBody = res.getBody().asString();
        System.out.println(responseBody);

        //status code validation
        int statusCode = res.getStatusCode();
        Assert.assertEquals(statusCode,200,"Status Code not match");

        //status line validation
        String statusLine = res.getStatusLine();
        Assert.assertEquals(statusLine, "HTTP/1.1 200 OK","Status line not match");
    }
}

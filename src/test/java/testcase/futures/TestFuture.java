package testcase.futures;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import common.util.XLUtils;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import testcase.BaseTest;

import java.io.IOException;

public class TestFuture extends BaseTest{
    @DataProvider(name="testfuturedata")
    public Object[][] getData() throws IOException {
        //read data from excel
        String path = System.getProperty("user.dir") + "/src/test/java/testcase/futures/testFutureData.xlsx";
        int rowNum = XLUtils.getRowCount(path,"Sheet1");
        int colNum = XLUtils.getCellCount(path,"sheet1",1);
        String[][] data = new String [rowNum][colNum];

        for(int i=1;i<=rowNum;i++){
            for(int j=0;j<colNum;j++){
                data[i-1][j] = XLUtils.getCellData(path,"Sheet1",i,j);
            }
        }
        return data;
    }

    @Test(dataProvider = "testfuturedata", priority = 1)
    public void testFuture(String name,String underlying, String description){
        ExtentTest extentTest = extentReports.createTest("testFuture","to verify Future API cases");

        logger.info("Test : [testFuture] " + name + "|" + underlying + "|" + description);
        RequestSpecification httpRequest = RestAssured.given();
        Response res= httpRequest.request(Method.GET, "/futures/" + name);
        convertResponseResultToMap(res);

        JsonPath jp = res.jsonPath();
        String resName = jp.get("result.name");
        String resUnderlying = jp.get("result.underlying");
        String resDescription = jp.get("result.description");
        logger.info("Name = " + resName);
        logger.info("Underlying = " + resUnderlying);
        logger.info("Description = " + resDescription);
        extentTest.info("Name = " + resName);
        extentTest.info("Underlying = " + resUnderlying);
        extentTest.info("Description = " + resDescription);

        int statusCode = res.getStatusCode();
        String responseBody = res.getBody().toString();
        long responseTime = res.getTime();
        String statusLine = res.getStatusLine();
        String contentType = res.getContentType();
        String serverType = res.header("Server");
        String contentEncoding = res.header("Content-Encoding");

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(statusCode, 200, "Status Code is not expected");
        softAssert.assertTrue(responseBody!=null, "Response Body is not empty");
        softAssert.assertTrue(responseTime<2000, "Response time is not more than 2000ms");
        softAssert.assertEquals(statusLine,"HTTP/1.1 200 OK","Status line is not expected");
        softAssert.assertEquals(contentType,"application/json","Content Type is not expected");
        softAssert.assertEquals(serverType,"cloudflare", "Server Type is not expected");
        softAssert.assertEquals(contentEncoding,"gzip", "Content Encoding is not expected");

        softAssert.assertEquals(resName,name,"name is not matched");
        softAssert.assertEquals(resUnderlying,underlying,underlying + "is not matched");
        softAssert.assertEquals(resDescription,description,description + "is not matched");
        softAssert.assertAll();
    }


}

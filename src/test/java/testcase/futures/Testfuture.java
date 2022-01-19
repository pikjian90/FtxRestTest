package testcase.futures;

import common.util.XLUtils;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import testcase.BaseTest;

import java.io.IOException;

public class Testfuture extends BaseTest{

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

    @Test(dataProvider = "testfuturedata")
    public void testFuture(String name,String underlying, String description){
        RequestSpecification httpRequest = RestAssured.given();
        Response res= httpRequest.request(Method.GET, "/futures/" + name);
        convertResponseResultToMap(res);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(hm.get("underlying"),underlying,underlying + "is not matched");
        softAssert.assertEquals(hm.get("description"),description,description + "is not matched");
        softAssert.assertAll();
    }


}

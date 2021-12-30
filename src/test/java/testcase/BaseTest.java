package testcase;

import common.EndPoints;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeTest;

public class BaseTest {
    @BeforeTest
    public void beforeTest(){
        RestAssured.baseURI = EndPoints.endPoint;
    }
}

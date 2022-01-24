package testcase;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import common.EndPoints;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;

import java.util.HashMap;

public class BaseTest {
    public static ExtentHtmlReporter htmlReporter;
    public static ExtentReports extentReports;

    public static HashMap<String,String> hm = new HashMap();
    public Logger logger;

    @BeforeSuite
    public void beforeSuite(){
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/abc.html");
        htmlReporter.config().setDocumentTitle("API Testing Report");
        htmlReporter.config().setReportName("FTX REST API Test");
        htmlReporter.config().setTheme(Theme.DARK);

        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReporter);
        extentReports.setSystemInfo("Host Name","Local Host");
        extentReports.setSystemInfo("Environment","QA");
        extentReports.setSystemInfo("Tester","QA");

        RestAssured.baseURI = EndPoints.endPoint;

        logger = Logger.getLogger("ftxRestTest"); // added logger
        PropertyConfigurator.configure("log4j.properties");
        logger.setLevel(Level.INFO);

        logger.info("BeforeSuite : setup Extent Report");
        logger.info("BeforeSuite : setup Endpoint " + EndPoints.endPoint);
        logger.info("BeforeSuite : setup log4j");

    }

    public HashMap<String,String> convertResponseResultToMap(Response res){
        JsonPath jsonPath = res.jsonPath();
        String jsonPathString = jsonPath.get("result").toString()
                .replace("{","")
                .replace("}","")
                .replace("[","")
                .replace("]","");

        String[] s = jsonPathString.split(",");
        for(int i=0;i<s.length;i++){
            String[] sSplit = s[i].split("=");
            if(sSplit[0].startsWith(" ")){
                sSplit[0] = sSplit[0].substring(1,sSplit[0].length());
            }
            hm.put(sSplit[0],sSplit[1]);
        }

// for (Map.Entry<String, String> set : hm.entrySet()) {
//     System.out.println(set.getKey() + " = " + set.getValue());
// }
        return hm;
    }

    @AfterClass
    public void tearDown(){
        logger.info("AfterClass - tearDown");
    }

    @AfterTest
    public void afterTest(){
        logger.info("AfterTest - afterTest");
    }

    @AfterSuite
    public void tearDownSuite(){
        logger.info("AfterSuite - tearDownSuite");
        extentReports.flush();
    }
}

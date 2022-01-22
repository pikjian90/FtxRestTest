package common.util;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.ITestContext;

public class Listeners extends TestListenerAdapter {
    public ExtentTest extentTest;
    public ExtentReports extentReports;
    public ExtentHtmlReporter htmlReporter;

    public void onStart(ITestContext testContext) {
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/abc.html");
        htmlReporter.config().setDocumentTitle("API Testing Report");
        htmlReporter.config().setReportName("Report Name");
        htmlReporter.config().setTheme(Theme.DARK);

        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReporter);
        extentReports.setSystemInfo("Host Name","Local Host");
        extentReports.setSystemInfo("Environment","QA");
        extentReports.setSystemInfo("Tester","QA");
    }

    public void onTestSuccess(ITestResult result){
        extentTest = extentReports.createTest(result.getName());
        extentTest.log(Status.PASS,"Test Case Passed : " + result.getName());
}
    public void onTestFailure(ITestResult result){
        extentTest = extentReports.createTest(result.getName());
        extentTest.log(Status.FAIL,"Test Case Failed : " + result.getName());
        extentTest.log(Status.FAIL,"Test Case Failed : " + result.getThrowable());
    }

    public void onTestSkipped(ITestResult result){
        extentTest = extentReports.createTest(result.getName());
        extentTest.log(Status.SKIP,"Test Case Skipped : " + result.getName());
    }
}

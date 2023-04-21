package appiumproject.testUtils;

import appiumproject.utils.AppiumCommonUtils;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import io.appium.java_client.AppiumDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGListener;
import org.testng.ITestResult;

public class TestListeners extends AppiumCommonUtils implements ITestListener {

    ExtentReports extentReports = ExtentReportTestNG.getReporterObject();
    ExtentTest test;

    @Override
    public void onTestStart(ITestResult result) {
        ITestListener.super.onTestStart(result);
        test = extentReports.createTest(result.getMethod().getMethodName()); //testcase name
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ITestListener.super.onTestSuccess(result);
        test.log(Status.PASS,"Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ITestListener.super.onTestFailure(result);
        //failing test entry in report
        test.fail(MarkupHelper.createLabel(result.getName()+ " - Test failed due to below issue/error: ", ExtentColor.RED));
        test.fail(result.getThrowable());
        try {
       driver = (AppiumDriver) result.getTestClass().getRealClass().getField("driver")
               .get(result.getInstance());
        }catch (Exception e){
            e.printStackTrace();
            e.getMessage();
        }
        try {
            System.out.println("************** "+result.getMethod().getMethodName() + " -> Test failed! ,check screenshot and report ***************");

            //method: take screen shot and add screenshot to the report
            //Argument Explain:(testcasename on screenshot ,title name of screenshot that showing in report)

            //Method 1: by Media Entity Builder
            test.fail("Failed step: ", MediaEntityBuilder.createScreenCaptureFromPath(getScreenshotPath(result.getName(), driver),result.getName()).build());

            //Method 2: (Rahul shetty) - by addScreenCaptureFromPath method

           //test.addScreenCaptureFromPath(getScreenshotPath(result.getMethod().getMethodName(),driver),result.getMethod().getMethodName());
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Screen-capture has been taken but not attached to Extent report:- "+ e.getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    @Override
    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);
    }

    @Override
    public void onFinish(ITestContext context) {
        ITestListener.super.onFinish(context);
        extentReports.flush();

    }
}

//Testcase Name -> result.getMethod().getMethodName()
package appiumproject.testUtils;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportTestNG {

    static ExtentReports extentReports ;

    public static ExtentReports getReporterObject(){

      String path = System.getProperty("user.dir") + "\\TestReports\\ExtentHtmlReport.html";

      ExtentSparkReporter reporter = new ExtentSparkReporter(path) ;
      reporter.config().setReportName("Appium Automation Report");
      reporter.config().setDocumentTitle("Test Results");

      extentReports = new ExtentReports();
      extentReports.attachReporter(reporter);
      extentReports.setSystemInfo("Tester", "Achal");

      return extentReports;
    }
}

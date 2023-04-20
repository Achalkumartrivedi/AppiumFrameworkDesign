package appiumproject.testUtils;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportTestNG extends SuperBaseClass{
    static String path = projectdir + "\\TestReports\\ExtentHtmlReport.html";
    static ExtentSparkReporter reporter;
    static ExtentReports extentReports ;

    public static ExtentReports getReporterObject(){

      reporter = new ExtentSparkReporter(path) ;
      reporter.config().setReportName("Appium Automation Report");
      reporter.config().setDocumentTitle("Test Results");

      extentReports = new ExtentReports();
      extentReports.attachReporter(reporter);
      extentReports.setSystemInfo("Tester", "Achal");

      return extentReports;
    }
}

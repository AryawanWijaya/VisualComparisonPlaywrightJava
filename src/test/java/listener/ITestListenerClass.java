package listener;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.JsonFormatter;
import com.microsoft.playwright.Page;
import org.qa.automation.BaseTest;
import org.qa.automation.LoginPageTest;
import org.qa.automation.constant.ExpectedImagePath;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author nikolaus.wijaya on 26/10/2024
 * @project VisualComparisonPlaywright
 */
public class ITestListenerClass extends BaseTest implements ITestListener, ISuiteListener {

  //  run when entering suite at testNgXml <suite>
  public void onStart(ISuite suite) {
    System.out.println(
        "1 -  ================= Run listener before suite - init extent report =================");
    ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter("target/Report.html");
    JsonFormatter json = new JsonFormatter("target/report.json");
    extentReports = new ExtentReports();
    try {
      extentReports.createDomainFromJsonArchive("target/report.json");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    extentReports.attachReporter(json, extentSparkReporter);
    extentReports.setSystemInfo("OS", System.getProperty("os.name"));
    extentReports.setSystemInfo("Java Version", System.getProperty("java.version"));
  }

  //  run when entering test at testNgXml <test>
  public void onStart(ITestContext context) {
    System.out.println(
        "2 -  ================= Run listener before <test> - create test extent report =================");
  }

  //  run after @BeforeTest annotation -> run before each test run
  public void onTestStart(ITestResult result) {
    System.out.println(
        "4 -  ================= Run listener before each test - setup detail browser extent report =================");
    extentTest = extentReports.createTest(result.getTestContext().getName());
    if (result.getTestContext().getCurrentXmlTest().getParameter("author") != null) {
      extentTest.assignAuthor(result.getTestContext().getCurrentXmlTest().getParameter("author"));
    } else {
      extentTest.assignAuthor("default-user");
    }

    extentTest.assignDevice(
        playwrightBased.browser.browserType().name() + "  " + playwrightBased.browser.version());

  }

  public void onTestSuccess(ITestResult result) {
    extentTest.pass("Scenario from method " + result.getTestContext().getName() + " is pass");
    extentTest.assignCategory(result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(
        Test.class).groups());
  }

  public void onTestFailure(ITestResult result) {
    String path;
    if (pathImageComparison == null) {
      path = getScreenShot(result.getName() + "_" + result.getMethod().getMethodName());
    } else {
      path = pathImageComparison;
    }
    System.out.println(path);
    MediaEntityBuilder ss = MediaEntityBuilder.createScreenCaptureFromPath(path);
    extentTest.log(Status.FAIL, result.getThrowable(), ss.build());
    if (expectedPathImage != null) {
      MediaEntityBuilder expected = MediaEntityBuilder.createScreenCaptureFromPath(
          System.getProperty("user.dir") + expectedPathImage);
      extentTest.log(Status.INFO, "Expected image", expected.build());
    }
    //    extentTest.addScreenCaptureFromPath(path);
    //    extentTest.fail(result.getThrowable());
    extentTest.assignCategory(result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(
        Test.class).groups());
  }

  public void onFinish(ISuite suite) {
    extentReports.flush();
    try {
      Desktop.getDesktop()
          .browse(new File(System.getProperty("user.dir") + "/target/Report.html").toURI());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}

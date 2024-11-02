package org.qa.automation;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.github.romankh3.image.comparison.ImageComparison;
import com.github.romankh3.image.comparison.ImageComparisonUtil;
import com.github.romankh3.image.comparison.model.ImageComparisonResult;
import com.github.romankh3.image.comparison.model.ImageComparisonState;
import com.microsoft.playwright.Page;
import org.qa.automation.data.E2EData;
import org.qa.automation.pages.LoginPage;
import org.qa.automation.utils.PlaywrightBased;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Properties;
import java.util.UUID;

/**
 * @author nikolaus.wijaya on 23/10/2024
 * @project VisualComparisonPlaywright
 */
public class BaseTest {
  public static ExtentReports extentReports;
  public static ExtentTest extentTest;
  public static String pathImageComparison;
  public static String expectedPathImage;
  public static PlaywrightBased playwrightBased;
  public static Page page;
  public static E2EData e2EData;
  Properties properties;
  LoginPage loginPage;
  File file;

  private String subFolder;

  @Parameters({"browserName"})
  @BeforeTest
  public void setup(@Optional String browserName, ITestContext context) {
    System.out.println(
        "3 -  ================= Run @BeforeTest - Init playwright and load properties =================");
    playwrightBased = new PlaywrightBased();
    properties = playwrightBased.loadProperties();
    page = playwrightBased.playwrightInit(properties, browserName);
    e2EData = new E2EData();
    loginPage = new LoginPage(page);
    pathImageComparison = null;
  }

  @AfterTest
  public void tearDown() {
    page.context().browser().close();
  }

  public void navigateToUrl(String url) {
    page.navigate(url);
    extentTest.info("Navigate to url... " + url);
  }

  public String getScreenShot(String fileName) {
    if (subFolder == null) {
      LocalDateTime localDateTime = LocalDateTime.now();
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
      subFolder = formatter.format(localDateTime);
    }

    String path =
        System.getProperty("user.dir") + "/target/ss/" + subFolder + "/" + fileName + ".jpg";
    page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));
    return path;
  }

  public ImageComparisonState visualComparison(String expectedPath) {
    expectedPathImage = expectedPath;
    String imageTitle = UUID.randomUUID().toString();
    BufferedImage expectedImage =
        ImageComparisonUtil.readImageFromResources(
            System.getProperty("user.dir") + expectedPathImage);
    String actualImagePath = getScreenShot(imageTitle);
    BufferedImage actualImage = ImageComparisonUtil.readImageFromResources(actualImagePath);
    file = new File(
        System.getProperty("user.dir") + "/target/ss/" + subFolder + "/result/" + imageTitle
            + ".jpg");
    ImageComparison imageComparison = new ImageComparison(expectedImage, actualImage, file);
    //    set destination to store the result
    imageComparison.setDestination(file);
    //        set thickness of the line if there is a difference
    imageComparison.setRectangleLineWidth(5);
    //        set color of rectangle
    imageComparison.setDifferenceRectangleColor(Color.BLUE);
    //    set threshold of comparison in percent 0 -> meaning must same image,50% meaning more tolerance
    imageComparison.setAllowingPercentOfDifferentPixels(Double.parseDouble(properties.getProperty(
        "image-comparison-threshold")));
    //    compare the image
    ImageComparisonResult result = imageComparison.compareImages();
    //    if not match will save the result
    if (result.getImageComparisonState() != ImageComparisonState.MATCH) {
      BufferedImage imageResult = result.getResult();
      ImageComparisonUtil.saveImage(imageComparison.getDestination().get(), imageResult);
    }
    return result.getImageComparisonState();
  }
}

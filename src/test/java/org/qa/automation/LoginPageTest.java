package org.qa.automation;

import com.github.romankh3.image.comparison.model.ImageComparisonState;
import listener.ITestListenerClass;
import org.qa.automation.constant.ExpectedImagePath;
import org.qa.automation.constant.TextValidationConstant;
import org.qa.automation.pages.ProductListingPage;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * @author nikolaus.wijaya on 23/10/2024
 * @project VisualComparisonPlaywright
 */
@Listeners(ITestListenerClass.class)
public class LoginPageTest extends BaseTest {

  @Test(groups = {"Sanity"})
  public void successVisualComparisonLoginPage() {
    navigateToUrl(properties.getProperty("url"));
    extentTest.info("Navigate to url... ");
    ImageComparisonState state = visualComparison(ExpectedImagePath.LOGIN_IMAGE);
    extentTest.info("Compare current image with expected image");

    if (state != ImageComparisonState.MATCH) {
      pathImageComparison = file.toString();
    }
    Assert.assertEquals(state, ImageComparisonState.MATCH, "Image is not match");
    extentTest.pass(
        "Visual comparisson is pass, all pixel are match with threshold " + properties.getProperty(
            "image-comparison-threshold"));
  }

  @Test(groups = {"Regression"})
  public void failedVisualComparisonLoginPage() {
    navigateToUrl(properties.getProperty("url"));
    ImageComparisonState state = visualComparison(ExpectedImagePath.FAILED_LOGIN_IMAGE);
    extentTest.info("Compare current image with expected image");

    if (state != ImageComparisonState.MATCH) {
      pathImageComparison = file.toString();
    }
    Assert.assertEquals(state, ImageComparisonState.MATCH, "Image is not match");
    extentTest.pass(
        "Visual comparisson is pass, all pixel are match with threshold " + properties.getProperty(
            "image-comparison-threshold"));
  }

  @Test(groups = {"Regression"})
  public void threshHoldTest() {
    navigateToUrl(properties.getProperty("url"));
    ImageComparisonState state = visualComparison(ExpectedImagePath.THRESHOLD_TEST);
    extentTest.info("Compare current image with expected image");

    if (state != ImageComparisonState.MATCH) {
      pathImageComparison = file.toString();
    }
    Assert.assertEquals(state, ImageComparisonState.MATCH, "Image is not match");
    extentTest.pass(
        "Visual comparisson is pass, all pixel are match with threshold " + properties.getProperty(
            "image-comparison-threshold"));
  }
}

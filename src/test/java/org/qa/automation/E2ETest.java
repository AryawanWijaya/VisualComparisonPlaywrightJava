package org.qa.automation;

import listener.ITestListenerClass;
import org.qa.automation.constant.TextValidationConstant;
import org.qa.automation.pages.CheckoutCompletePage;
import org.qa.automation.pages.CheckoutConfirmationPage;
import org.qa.automation.pages.CheckoutCustInfopage;
import org.qa.automation.pages.CheckoutPage;
import org.qa.automation.pages.ProductDetailPage;
import org.qa.automation.pages.ProductListingPage;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * @author nikolaus.wijaya on 31/10/2024
 * @project VisualComparisonPlaywright
 */
@Listeners(ITestListenerClass.class)
public class E2ETest extends BaseTest {

  @Test(groups = "Integration")
  public void e2eTest() {
    //    navigate to url
    navigateToUrl(properties.getProperty("url"));
    Assert.assertEquals(loginPage.getPageTitle(), TextValidationConstant.TITLE_LOGINPAGE);
    extentTest.pass("Success open login page");
    //    trigger login
    extentTest.info("User login with correct username and password ");
    loginPage.fillUsername(properties.getProperty("standart.username"));
    loginPage.fillPassword(properties.getProperty("password"));
    ProductListingPage plp = loginPage.clickLoginBtn();
    Assert.assertEquals(plp.getTitlePlp(), TextValidationConstant.TITLE_PLP);
    extentTest.pass("User success to login");
    //    choose 1 product
    int productNumber = Integer.parseInt(properties.getProperty("buyProduct.number"));
    e2EData.setProductName(plp.getProductName(productNumber));
    e2EData.setProductDesc(plp.getProductDesc(productNumber));
    e2EData.setProductPrice(plp.getProductPrice(productNumber));
    extentTest.info(
        "User click product number " + productNumber + " and go to Product detail page");
    ProductDetailPage pdp = plp.clickProductNumber(productNumber);
    Assert.assertEquals(pdp.getTitlePdpPage(), TextValidationConstant.TITLE_PDP,
        "User failed to enter Pdp Page");
    extentTest.pass("User success to enter product Detail page");
    //    verified all detail product match
    extentTest.info("verified all data at product detail page");
    Assert.assertEquals(pdp.getProductName(),
        e2EData.getProductName(), "Product name is not match");
    extentTest.pass("Name of product is match");
    Assert.assertEquals(pdp.getProductDesc(),
        e2EData.getProductDesc(), "Desc product is not match");
    extentTest.pass("Desc of product is match");
    Assert.assertEquals(pdp.getProductPrice(),
        e2EData.getProductPrice(), "price is not match");
    extentTest.pass("price of product is match");
    //    click add to cart product
    extentTest.info("user add to cart product");
    pdp.clickAddToCart();
    Assert.assertEquals(pdp.removeBtnVisible(), true, "Failed to add to cart");
    e2EData.setProductQty(properties.getProperty("buyProduct.qty"));
    Assert.assertEquals(pdp.getNumberShoppingCartNum(), e2EData.getProductQty(),
        "number of qty not match");
    extentTest.pass("Success to add to cart product");
    //    continue to checkout page
    extentTest.info("User click cart icon");
    CheckoutPage checkoutPage = pdp.clickShoppingCartIcon();
    Assert.assertEquals(checkoutPage.getTitleCheckoutPage(),
        TextValidationConstant.TITLE_CHECKOUT_PAGE,
        "User failed to enter checkout Page");
    extentTest.pass("User success to enter checkout page");
    //    verified all detail product
    extentTest.info("verified all data at checkout page");
    Assert.assertEquals(checkoutPage.getProductName(),
        e2EData.getProductName(), "Product name is not match");
    extentTest.pass("Name of product is match");
    Assert.assertEquals(checkoutPage.getProductDesc(),
        e2EData.getProductDesc(), "Desc product is not match");
    extentTest.pass("Desc of product is match");
    Assert.assertEquals(checkoutPage.getProductPrice(),
        e2EData.getProductPrice(), "price is not match");
    extentTest.pass("price of product is match");
    Assert.assertEquals(checkoutPage.getProductQty(),
        e2EData.getProductQty(), "Qty is not match");
    extentTest.pass("Qty of product is match");
    //    click checkout btn to continue
    extentTest.info("user click checkout btn");
    CheckoutCustInfopage custInfopage = checkoutPage.clickCheckout();
    Assert.assertEquals(custInfopage.getTitleCustPage(),
        TextValidationConstant.TITLE_CHECKOUT_CUST_INFO,
        "failed to enter checkout cust info page");
    extentTest.pass("User success to enter checkout customer info page");
    extentTest.info("user fill all detail data and click continue");
    custInfopage.fillFirstName(properties.getProperty("user.firstname"));
    custInfopage.fillLastName(properties.getProperty("user.lastname"));
    custInfopage.fillPostalCode(properties.getProperty("user.postalCode"));
    CheckoutConfirmationPage confirmationPage = custInfopage.clickContinue();
    Assert.assertEquals(confirmationPage.getTitleOfPage(),
        TextValidationConstant.TITLE_CHECK_OUT_CONFIRMATION,
        "user failed to enter confirmation page");
    extentTest.pass("User success to enter checkout confirmation page");
    extentTest.info("verified all data at checkout confirmation page");
    //    verified all detail product
    Assert.assertEquals(confirmationPage.getProductName(),
        e2EData.getProductName(), "Product name is not match");
    extentTest.pass("Name of product is match");
    Assert.assertEquals(confirmationPage.getProductDesc(),
        e2EData.getProductDesc(), "Desc product is not match");
    extentTest.pass("Desc of product is match");
    Assert.assertEquals(confirmationPage.getProductPrice(),
        e2EData.getProductPrice(), "price is not match");
    extentTest.pass("price of product is match");
    Assert.assertEquals(confirmationPage.getProductQty(),
        e2EData.getProductQty(), "Qty is not match");
    extentTest.pass("Qty of product is match");
    Assert.assertEquals(confirmationPage.getConfirmationPrice(),
        "Item total: " + e2EData.getProductPrice(), "price confirmation is not match");
    extentTest.pass("price confirmation is match");
    //    click finish
    extentTest.info("user click finish btn");
    CheckoutCompletePage completePage = confirmationPage.clickFinish();
    Assert.assertEquals(completePage.getTitleOfPage(),
        TextValidationConstant.TITLE_CHECKOUT_COMPLETE,
        "Failed to enter checkout complete page");
    extentTest.pass("User success to enter checkout complete page");
    Assert.assertEquals(completePage.getTyTextTitle(),
        TextValidationConstant.TY_TEXT_TITLE,
        "Title of thank you text is not match");
    Assert.assertEquals(completePage.getOrderProceedDetail(),
        TextValidationConstant.TY_TEXT_DESC,
        "Detail of thank you text is not match");
    extentTest.pass("User success to Finish their order flow");
  }
}

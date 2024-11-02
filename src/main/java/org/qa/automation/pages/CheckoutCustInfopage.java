package org.qa.automation.pages;

import com.microsoft.playwright.Page;

/**
 * @author nikolaus.wijaya on 30/10/2024
 * @project VisualComparisonPlaywright
 */
public class CheckoutCustInfopage {
  private String titleCheckoutCustInfo="span.title";
  private String firstName ="first-name";
  private String lastName="last-name";
  private String postalCode="postal-code";
  private String continueBtn="continue";
  Page page;

  public CheckoutCustInfopage(Page page) {
    this.page = page;
  }

  public String getTitleCustPage() {
    return page.locator(titleCheckoutCustInfo).textContent().trim();
  }

  public void fillFirstName(String input) {
    page.locator("id=" + firstName).fill(input);
  }

  public void fillLastName(String input) {
    page.locator("id=" + lastName).fill(input);
  }

  public void fillPostalCode(String input) {
    page.locator("id=" + postalCode).fill(input);
  }

  public CheckoutConfirmationPage clickContinue() {
    page.locator("id=" + continueBtn).click();
    return new CheckoutConfirmationPage(page);
  }
}

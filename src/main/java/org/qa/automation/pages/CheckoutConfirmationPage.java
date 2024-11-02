package org.qa.automation.pages;

import com.microsoft.playwright.Page;

/**
 * @author nikolaus.wijaya on 30/10/2024
 * @project VisualComparisonPlaywright
 */
public class CheckoutConfirmationPage {
  private final String checkoutTitle="span.title";
  private final String productName="div.inventory_item_name";
  private final String productDesc="div.inventory_item_desc";
  private final String productPrice="div.inventory_item_price";
  private final String confirmationPrice="div.summary_subtotal_label";
  private final String productQty="div.cart_quantity";
  private final String finishBtn="finish";

  Page page;

  public CheckoutConfirmationPage(Page page) {
    this.page = page;
  }

  public String getTitleOfPage() {
    return page.locator(checkoutTitle).textContent().trim();
  }

  public String getProductName() {
    return page.locator(productName).textContent().trim();
  }

  public String getProductDesc() {
    return page.locator(productDesc).textContent().trim();
  }

  public String getProductPrice() {
    return page.locator(productPrice).textContent().trim();
  }

  public String getConfirmationPrice() {
    return page.locator(confirmationPrice).textContent().trim();
  }

  public String getProductQty() {
    return page.locator(productQty).textContent().trim();
  }

  public CheckoutCompletePage clickFinish() {
    page.locator("id=" + finishBtn).click();
    return new CheckoutCompletePage(page);
  }
}

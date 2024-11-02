package org.qa.automation.pages;

import com.microsoft.playwright.Page;

/**
 * @author nikolaus.wijaya on 24/10/2024
 * @project VisualComparisonPlaywright
 */
public class ProductListingPage {
  private final String titlePlp="span.title";
  private final String productName="div.inventory_item_name";
  private final String productDesc="div.inventory_item_desc";
  private final String productPrice="div.inventory_item_price";
  Page page;

  public ProductListingPage(Page page) {
    this.page = page;
  }

  public String getTitlePlp() {
    return page.locator(titlePlp).textContent();
  }

  public ProductDetailPage clickProductNumber(int productNumber) {
    page.locator(productName).nth(productNumber - 1).click();
    return new ProductDetailPage(page);
  }
  public String getProductName(int productNumber) {
    return page.locator(productName).nth(productNumber - 1).textContent().trim();
  }
  public String getProductDesc(int productNumber) {
    return page.locator(productDesc).nth(productNumber - 1).textContent().trim();
  }
  public String getProductPrice(int productNumber) {
    return page.locator(productPrice).nth(productNumber - 1).textContent().trim();
  }
}

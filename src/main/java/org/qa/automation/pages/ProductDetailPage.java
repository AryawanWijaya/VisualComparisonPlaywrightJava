package org.qa.automation.pages;

import com.microsoft.playwright.Page;

/**
 * @author nikolaus.wijaya on 30/10/2024
 * @project VisualComparisonPlaywright
 */
public class ProductDetailPage {
  private final String titlePdpPage="back-to-products";
  private final String addToCartBtn= "add-to-cart";
  private final String removeBtn="remove";
  private final String titleProduct="div.inventory_details_name";
  private final String priceProduct="div.inventory_details_price";
  private final String descProduct= "div.inventory_details_desc";
  private final String shoppingCartBadge="span.shopping_cart_badge";
  Page page;

  public ProductDetailPage(Page page) {
    this.page = page;
  }

  public String getTitlePdpPage() {
    return page.locator("id=" + titlePdpPage).textContent().trim();
  }

  public String getProductName() {
    return page.locator(titleProduct).textContent().trim();
  }

  public String getProductDesc() {
    return page.locator(descProduct).textContent().trim();
  }

  public String getProductPrice() {
    return page.locator(priceProduct).textContent().trim();
  }

  public void clickAddToCart() {
    page.locator("id=" + addToCartBtn).click();
  }

  public boolean removeBtnVisible() {
    return page.locator("id=" + removeBtn).isVisible();
  }

  public String getNumberShoppingCartNum() {
    return page.locator(shoppingCartBadge).textContent().trim();
  }

  public CheckoutPage clickShoppingCartIcon() {
    page.locator(shoppingCartBadge).click();
    return new CheckoutPage(page);
  }
}

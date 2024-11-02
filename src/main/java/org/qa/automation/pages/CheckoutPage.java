package org.qa.automation.pages;

import com.microsoft.playwright.Page;

/**
 * @author nikolaus.wijaya on 30/10/2024
 * @project VisualComparisonPlaywright
 */
public class CheckoutPage {
  private final String titleCheckoutPage="span.title";
  private final String qty="div.cart_quantity";
  private final String productName="div.inventory_item_name";
  private final String productDesc="div.inventory_item_desc";
  private final String priceProduct ="div.inventory_item_price";
  private final String checkoutBtn="checkout";
  Page page;
  public CheckoutPage(Page page){
    this.page=page;
  }
  public String getTitleCheckoutPage(){
    return page.locator(titleCheckoutPage).textContent().trim();
  }

  public String getProductName(){
    return page.locator(productName).textContent().trim();
  }
  public String getProductDesc(){
    return page.locator(productDesc).textContent().trim();
  }
  public String getProductPrice(){
    return page.locator(priceProduct).textContent().trim();
  }
  public String getProductQty(){
    return page.locator(qty).textContent().trim();
  }
  public CheckoutCustInfopage clickCheckout(){
    page.locator("id="+checkoutBtn).click();
    return new CheckoutCustInfopage(page);
  }
}

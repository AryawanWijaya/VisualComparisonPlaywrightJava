package org.qa.automation.pages;

import com.microsoft.playwright.Page;

/**
 * @author nikolaus.wijaya on 31/10/2024
 * @project VisualComparisonPlaywright
 */
public class CheckoutCompletePage {
  private final String checkoutTitle="span.title";
  private final String tyOrder="h2.complete-header";
  private final String orderProceedDetail="div.complete-text";
  Page page;
  public CheckoutCompletePage (Page page){
    this.page=page;
  }
  public String getTitleOfPage(){
    return page.locator(checkoutTitle).textContent().trim();
  }
  public String getTyTextTitle(){
    return page.locator(tyOrder).textContent().trim();
  }
  public String getOrderProceedDetail(){
    return page.locator(orderProceedDetail).textContent().trim();
  }
}

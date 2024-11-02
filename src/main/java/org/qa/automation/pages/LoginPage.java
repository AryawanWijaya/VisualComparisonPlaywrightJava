package org.qa.automation.pages;

import com.microsoft.playwright.Page;

/**
 * @author nikolaus.wijaya on 23/10/2024
 * @project VisualComparisonPlaywright
 */
public class LoginPage {

  private final String titleLoginPage ="div.login_logo";
  private final String usernamePath="user-name";
  private final String passwordPath="password";
  private final String submit="login-button";
  Page page;
  public LoginPage(Page page){
    this.page=page;
  }

  public String getPageTitle(){
    return page.locator(titleLoginPage).textContent();
  }
  public void fillUsername(String username){
    page.fill("id="+usernamePath,username);
  }

  public void fillPassword(String password){
    page.fill("id="+passwordPath,password);
  }

  public ProductListingPage clickLoginBtn(){
    page.click("id="+submit);
    return new ProductListingPage(page);
  }
}

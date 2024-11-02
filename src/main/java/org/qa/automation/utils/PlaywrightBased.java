package org.qa.automation.utils;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author nikolaus.wijaya on 21/10/2024
 * @project VisualComparisonPlaywright
 */
public class PlaywrightBased {

  Playwright playwright;
  public Browser browser;
  BrowserContext context;
  Page page;

  Properties properties;

  public Page playwrightInit(Properties props, String browserName) {
    if (browserName == null) {
      browserName = props.getProperty("browser");
    }
    playwright = Playwright.create();

    boolean headlessMode = Boolean.parseBoolean(props.getProperty("headlessMode"));
    switch (browserName.toLowerCase()) {
      case "chromium":
        browser =
            playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(headlessMode));
        break;
      case "chrome":
        browser = playwright.chromium()
            .launch(new BrowserType.LaunchOptions().setHeadless(headlessMode).setChannel("chrome"));
        break;
      case "firefox":
        browser =
            playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(headlessMode));
        break;
      case "safari":
        browser =
            playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(headlessMode));
        break;
      default:
        System.out.println("wrong browser");
    }
    context = browser.newContext();
    page = context.newPage();
    return page;
  }

  public Properties loadProperties() {
    String name = "config.properties";
    ClassLoader loader = Thread.currentThread().getContextClassLoader();
    properties = new Properties();
    InputStream inputStream = loader.getResourceAsStream(name);
    try {
      properties.load(inputStream);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return properties;
  }
}

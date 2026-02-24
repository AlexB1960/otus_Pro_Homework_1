package factory;

import exceptions.BrowserNotSupportedException;
import factory.settings.ChromeDriverSettings;
import factory.settings.EdgeDriverSettings;
import factory.settings.FirefoxDriverSettings;
import factory.settings.ISettings;
import listeners.StyleUpdateListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.events.EventFiringDecorator;

public class WebDriverFactory {
  //private String browser = System.getProperty("browser").trim().toLowerCase();
  WebDriver driver;

  public WebDriver create(String browser, String mode) {

    switch(browser) {
      case "chrome": {
        ISettings set = new ChromeDriverSettings();
        driver = new ChromeDriver((ChromeOptions) set.settings(mode));
        break;
      }
      case "firefox": {
        ISettings set = new FirefoxDriverSettings();
        driver = new FirefoxDriver((FirefoxOptions) set.settings(mode));
        break;
      }
      case "edge": {
        ISettings set = new EdgeDriverSettings();
        driver = new EdgeDriver((EdgeOptions) set.settings(mode));
        break;
      }
      case null, default: {
        throw new BrowserNotSupportedException(browser);
      }
    }
    StyleUpdateListener styleUpdateListener = new StyleUpdateListener(driver);
    return new EventFiringDecorator<>(styleUpdateListener).decorate(driver);
  }

}

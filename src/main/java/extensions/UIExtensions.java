package extensions;

import com.google.inject.Guice;
import factory.WebDriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import modules.GuicePagesModule;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.decorators.Decorated;

public class UIExtensions implements BeforeEachCallback, AfterEachCallback {
  private WebDriver driver;
  protected String mode = "--start-maximized";
  protected String browser = System.getProperty("browser").toLowerCase().trim();
  public static WebDriverManager webDriverManager;

  @Override
  public void beforeEach(ExtensionContext context) {
    driver = new WebDriverFactory().create(browser, mode);
    Guice.createInjector(new GuicePagesModule(driver)).injectMembers(context.getTestInstance().get());
    //Guice.createInjector(new GuiceComponentsModule(driver)).injectMembers(context.getTestInstance().get());
    //Guice.createInjector(new GuicePopupsModule(driver)).injectMembers(context.getTestInstance().get());
  }

  @Override
  public void afterEach(ExtensionContext context) {
    if (driver != null) {
      driver.quit();
      //quit(driver);
    }
  }

  public void quit(WebDriver driver) {
    WebDriver original = ((Decorated<WebDriver>) driver).getOriginal();
    webDriverManager.quit(original);
  }

}

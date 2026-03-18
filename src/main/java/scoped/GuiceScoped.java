package scoped;

import factory.WebDriverFactory;
import io.cucumber.guice.ScenarioScoped;
import lombok.Setter;
import org.openqa.selenium.WebDriver;

@ScenarioScoped
public class GuiceScoped {
  @Setter
  public String browser = System.getProperty("browser").trim().toLowerCase();
  public WebDriver driver = new WebDriverFactory()
      .create(browser, "maximize");
  public String title;
  public StringBuilder direction = new StringBuilder();

}

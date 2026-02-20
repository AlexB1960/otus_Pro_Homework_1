package factory.settings;

import extensions.UIExtensions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
//import static extensions.UIExtensions.webDriverManager;

public class ChromeDriverSettings implements ISettings {

  @Override
  public AbstractDriverOptions settings(String... userArgs) {

    //webDriverManager.chromedriver().setup();
    WebDriverManager.chromedriver().setup();
    ChromeOptions chromeOptions = new ChromeOptions();
    switch (userArgs[0].toLowerCase().trim()) {
      case "headless": {
        chromeOptions.addArguments("--headless");
        break;
      }
      case "fullscreen": {
        chromeOptions.addArguments("--fullscreen");
        break;
      }
      case "maximize": {
        chromeOptions.addArguments("--start-maximized");
        break;
      }
      case null, default: {
        chromeOptions.addArguments("--start-maximized");
      }
    }
    //chromeOptions.merge(desiredCapabilities);
    return chromeOptions;
  }

}

package listeners;

import com.google.inject.Singleton;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;

@Singleton
public class StyleUpdateListener implements WebDriverListener {
  @SneakyThrows
  @Override
  public void afterFindElement(WebDriver driver, By locator, WebElement result) {
    if (result.getTagName().equals("button")) {
      ((JavascriptExecutor) driver)
          .executeScript("arguments[0].setAttribute(\"style\", \"border:5px solid red\");", result);

      //TimeUnit.SECONDS.sleep(1);
    }
  }
}

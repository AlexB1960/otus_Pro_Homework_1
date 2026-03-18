package components;

import annotations.Component;
import com.google.inject.Inject;
import common.AbsCommon;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import scoped.GuiceScoped;

public abstract class AbsComponent extends AbsCommon {

  @Inject
  public AbsComponent(GuiceScoped guiceScoped) {
    super(guiceScoped);
  }

  {
    Assertions.assertTrue(waitForElementPresent(getComponentBy()));
  }

  private By getComponentBy() {
    Class clazz = getClass();
    if (clazz.isAnnotationPresent(Component.class)) {
      Component component = (Component) clazz.getDeclaredAnnotation(Component.class);

      String[] componentData = component.value().split(":");
      switch (componentData[0]) {
        case "css": {
          return By.cssSelector(componentData[1]);
        }
        case "xpath": {
          return By.xpath(componentData[1]);
        }
      }
    }
    return null; //либо exception
  }

  protected WebElement getComponentEntry() {
    return driver.findElement(getComponentBy());
  }

}

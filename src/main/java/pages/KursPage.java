package pages;

import annotations.Path;
import com.google.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import scoped.GuiceScoped;

@Path("/lessons/network-engineer-specialization/")
public class KursPage extends AbsBasePage<KursPage> {
  private final By courseNameSelector = By.cssSelector("div h1");

  @Inject
  public KursPage(GuiceScoped guiceScoped) {
    super(guiceScoped);
  }

  public void assertCourseName(String name) {
    if (waitForElementPresent(courseNameSelector)) {
      Assertions.assertEquals(getElement(courseNameSelector).getText(), name);
      //return this;
    } else {
      System.out.println("К сожалению, courseNameSelector is stale");
      throw new RuntimeException();
    }
  }

}

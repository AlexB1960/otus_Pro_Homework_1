package otus.hooks;

import com.google.inject.Inject;
import io.cucumber.java.After;
import scoped.GuiceScoped;

public class Hooks {
  @Inject
  private GuiceScoped guiceScoped;

  @After
  public void closeBrowser() {
    if (guiceScoped.driver != null) {
      guiceScoped.driver.close();
      guiceScoped.driver.quit();
    }
  }

}

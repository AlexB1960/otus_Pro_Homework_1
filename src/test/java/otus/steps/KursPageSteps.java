package otus.steps;

import com.google.inject.Inject;
import io.cucumber.java.ru.Тогда;
import pages.KursPage;
import scoped.GuiceScoped;

public class KursPageSteps {
  @Inject
  private KursPage kursPage;

  @Inject
  private GuiceScoped guiceScoped;

  @Тогда("Проверяем название выбранного курса на его открывшейся странице")
  public void checkCourseName() {
    kursPage.assertCourseName(guiceScoped.title);
  }

}

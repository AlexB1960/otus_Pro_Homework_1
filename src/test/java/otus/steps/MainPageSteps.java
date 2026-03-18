package otus.steps;

import com.google.inject.Inject;
import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.Пусть;
import pages.MainPage;
import scoped.GuiceScoped;

public class MainPageSteps {
  @Inject
  private MainPage mainPage;

  @Inject
  private GuiceScoped guiceScoped;

  @Пусть("Я открываю браузер {string}")
  public void letBrowser(String browser) {
    guiceScoped.setBrowser(browser.trim().toLowerCase());
  }

  @Пусть("Открываем главную страницу")
  public void openMainPage() {
    mainPage.open();
  }

  @Пусть("Подтверждаем куки")
  public void confirmCookie() {
    mainPage.start();
  }

  @Если("Открываем страницу с курсами кнопкой Все курсы")
  public void clickButtonAllCourses() {
    mainPage.openCoursesPageByClick();
  }

  @Если("Выбираем случайное значение направления курсов")
  public void getRandomCoursesDirection() {
    mainPage.getRandomDirection(guiceScoped.direction);
  }

}

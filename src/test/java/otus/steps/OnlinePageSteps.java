package otus.steps;

import com.google.inject.Inject;
import io.cucumber.java.ru.Тогда;
import pages.OnlinePage;
import scoped.GuiceScoped;

public class OnlinePageSteps {

  @Inject
  OnlinePage onlinePage;

  @Inject
  private GuiceScoped guiceScoped;

  @Тогда("Вывод в консоль данных о самом дешевом курсе")
  public void outCourseByMinPrice() {
    onlinePage.getMinMaxPriceCourse("Min");
  }

  @Тогда("Вывод в консоль данных о самом дорогом курсе")
  public void outCourseByMaxPrice() {
    onlinePage.getMinMaxPriceCourse("Max");
  }

}

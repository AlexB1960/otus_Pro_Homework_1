package otus.steps;

import com.google.inject.Inject;
import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.Тогда;
import pages.CoursesPage;
import scoped.GuiceScoped;
import java.time.LocalDate;

public class CoursePageSteps {
  @Inject
  private CoursesPage coursesPage;

  @Inject
  private GuiceScoped guiceScoped;

  @Если("Клик на карточке выбранного курса {string}")
  public void clickOnCourseCard(String course) {
    guiceScoped.title = course;
    coursesPage.getCourse(course);
  }

  @Тогда("Находим и проверяем у самого раннего курса его название и дату")
  public void checkMinCourse() {
    coursesPage.getMinMaxDateCourse("Min");
  }

  @Тогда("Находим и проверяем у самого позднего курса его название и дату")
  public void checkMaxCourse() {
    coursesPage.getMinMaxDateCourse("Max");
  }

  @Тогда("Проверяем выбор чекбокса случайно выбранного направления курсов")
  public void checkRandomDirection() {
    coursesPage.assertDirection(guiceScoped.direction, true);
  }

  @Тогда("Находим и выводим в консоль курсы начиная с {string}") //(.+)$
  public void outCoursesFromDate(String startStringDate) {
    LocalDate startDate = localdate(startStringDate);
    coursesPage.getCoursesFromDate(startDate);
  }

  public LocalDate localdate(String dateString) {
    return LocalDate.parse(dateString);
  }

}

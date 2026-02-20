package courses;

import com.google.inject.Inject;
import extensions.UIExtensions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.MainPage;

@ExtendWith(UIExtensions.class)
public class CoursesPageTest {

  @Inject
  private MainPage mainPage;

  /*@Inject
  private BlockMainMenu blockMainMenu;

  @Inject
  private EducationPopup educationPopup;*/

  //Сценарий 1 - проверка выбранного курса
  @Test
  @DisplayName("Сценарий 1")
  public void getCorrectCourse() {
    //Здесь можно изменить выбранный курс обучения
    String currentCourse = "Аналитик данных";
    mainPage.open()
        .start()
        .openCoursesPageByClick()
        .getCourse(currentCourse)
        .assertCourseName(currentCourse);
  }

  //Сценарий 2.1 - Самый ранний курс
  @Test
  @DisplayName("Сценарий 2.1")
  public void getFirstCourse() {
    mainPage.open()
        .start()
        .openCoursesPageByClick()
        .getMinMaxCourse("Min");
  }

  //Сценарий 2.2 - Самый поздний курс
  @Test
  @DisplayName("Сценарий 2.2")
  public void getLastCourse() {
    mainPage.open()
        .start()
        .openCoursesPageByClick()
        .getMinMaxCourse("Max");
  }

  //Сценарий 3 - Случайный выбор и проверка направления
  @Test
  @DisplayName("Сценарий 3")
  public void getCorrectCategory() {
    StringBuilder direction = new StringBuilder();
    mainPage.open()
        .start()
        .getRandomDirection(direction)
        .moveToEducationItem()
        .popupShouldBeVisible()
        .clickOnDirection(direction)
        .assertDirection(direction, true);
  }

}

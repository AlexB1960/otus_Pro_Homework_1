package otus.steps;

import com.google.inject.Inject;
import components.popups.EducationPopup;
import io.cucumber.java.ru.Если;
import scoped.GuiceScoped;

public class EducationPopupSteps {
  @Inject
  private EducationPopup educationPopup;

  @Inject
  private GuiceScoped guiceScoped;

  @Если("Проверяем видимость всплывающего меню")
  public void checkPopupVisibility() {
    educationPopup.popupShouldBeVisible();
  }

  @Если("Клик на названии случайно выбранного направления курсов")
  public void clickRandomDirection() {
    educationPopup.clickOnDirection(guiceScoped.direction);
  }

  @Если("Клик на названии направления Подготовительные курсы")
  public void clickOnDirectionPreCourses() {
    educationPopup.clickOnStringDirection("Подготовительные курсы");
  }

  @Если("Наводим мышкой на пункт главного меню Обучение")
  public void moveMouseToEducation() {
    educationPopup.moveToEducationItem();
  }

}

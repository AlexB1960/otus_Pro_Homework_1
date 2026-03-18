package components.popups;

import com.google.inject.Inject;
import common.AbsCommon;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import scoped.GuiceScoped;

public class EducationPopup extends AbsCommon implements IPopup<EducationPopup> {
  @FindBy(xpath = "//p[text()='Направления']")
  private WebElement directionsElement;

  @FindBy(xpath = "//span[@title='Обучение']")
  private WebElement educationMenuButton;

  @Inject
  public EducationPopup(GuiceScoped guiceScoped) {
    super(guiceScoped);

    //Бага - ???
    //popupShouldNotBeVisible();
  }

  @Override
  public void popupShouldNotBeVisible() {
    if (waitForCondition(ExpectedConditions.invisibilityOf(directionsElement))) {
      //return this;
    } else {
      System.out.println("Меню Образование - открыто без вызова");
      throw new RuntimeException();
    }
  }

  @Override
  public void popupShouldBeVisible() {
    if (isVisible(directionsElement)) {
      //return this;
    } else {
      System.out.println("Меню Образование - не было открыто при вызове");
      throw new RuntimeException();
    }
  }

  //клик по выбранному направлению direction во всплывающем меню
  public void clickOnDirection(StringBuilder direction) {
    directionsElement.findElement(By.xpath(String
        .format("//a[text()='%s']", direction))).click();
    //return new CoursesPage(driver);
  }

  //клик по выбранному направлению direction=Подготовительные курсы во всплывающем меню
  public void clickOnStringDirection(String direction) {
    directionsElement.findElement(By.xpath(String
        .format("//a[text()='%s']", direction))).click();
    //return new CoursesPage(driver);
  }

  //мышкой наводим на пункт меню Обучение
  public void moveToEducationItem() {
    waitForElementStaleness(educationMenuButton);
    actions.moveToElement(educationMenuButton).build().perform();
    //return this;
  }

}

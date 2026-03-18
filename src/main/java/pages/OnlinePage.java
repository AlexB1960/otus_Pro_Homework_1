package pages;

import annotations.Path;
import com.google.inject.Inject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import scoped.GuiceScoped;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Optional;

@Path("/catalog/courses?education_types=online")
public class OnlinePage extends AbsBasePage<OnlinePage> {
  private final By onlineCourseSelector = By.cssSelector("[href^='/online/']");

  @Inject
  public OnlinePage(GuiceScoped guiceScoped) {
    super(guiceScoped);
  }

  //Поиск и вывод в консоль самого дорогого или самого дешевого курса на странице
  public void getMinMaxPriceCourse(String end) {
    WebElement priceElement;
    Optional<WebElement> element;
    //Выбираем в element соответствующую карточку (с ценой Min или Max)
    if (waitForElementStaleness(getPresentElement(onlineCourseSelector))) {
      try {
        if (end.trim().equalsIgnoreCase("min")) {
          element = getPresentElements(onlineCourseSelector).stream()
              .reduce((el1, el2) ->
                  (getElementPrice(el1) < getElementPrice(el2) ? el1 : el2));
        } else if (end.trim().equalsIgnoreCase("max")) {
          element = getPresentElements(onlineCourseSelector).stream()
              .reduce((el1, el2) ->
                  (getElementPrice(el1) > getElementPrice(el2) ? el1 : el2));
        } else throw new NoSuchElementException();
        priceElement = element.get();
        System.out.println("Курс с "
            + (end.equals("Max") ? "максимальной" : "минимальной")
            +" ценой " + getElementPrice(priceElement));
        System.out.println(element.get().getText());
        //return this;
      } catch (NoSuchElementException e) {
        System.out.println("Ошибка выбора " + end +" даты курсов");
        throw new RuntimeException();
      }
    } else {
      System.out.println("Ошибка ожидания локатора");
      throw new RuntimeException();
    }
  }

  public Integer getElementPrice(WebElement element) {
    //Берем в links данные о стоимости курса Внутри страницы карточки
    String cost;
    String courseHref = element.getAttribute("href");
    assert courseHref != null;
    Document doc = null;
    try {
      doc = Jsoup.connect(courseHref).get();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    Elements links = doc.select(".sc-153sikp-11");
    if (links.isEmpty()) {
      cost = "0";
    } else {
      cost = links.getFirst().text();
      cost = cost.substring(0, cost.length()-2).trim();
    }
    return Integer.parseInt(cost);
  }

}

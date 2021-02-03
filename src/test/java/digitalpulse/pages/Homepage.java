package digitalpulse.pages;

import digitalpulse.World;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;

public class Homepage {
    World w;
    String URL = "https://www.digitalpulse.pwc.com.au";
    By headingsPath = By.xpath("//div[@id='banners']//li[contains(@class,'flex-active-slide')]/div//h2");
    By nextCarouselPath = By.xpath("//a[@class='flex-next']");
    By prevCarouselPath = By.xpath("//a[@class='flex-prev']");
    By menuOpenPath = By.xpath("//i[contains(@class,'bt-bars')]");
    By menuContactUsPath = By.xpath("//div[@class='menu-holder']//a[text()='Contact us']");
    By searchIconPath = By.xpath("//i[contains(@class,'bt-search')]");
    By searchInputPath = By.xpath("//input[@id='search-input']");

    String title = "Digital Pulse - Disruption, Innovation and Industry Change";

    public Homepage(World w){
        this.w = w;
    }

    public void open(){
        w.driver.navigate().to(URL);
        w.attachScreenshot();
    }

    public void verifyOnHomepage(){
        assertThat(w.driver.getTitle()).isEqualTo(title);
    }

    public List<String> getCarouselArticleHeadings(){
        w.wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(headingsPath));
        List<String> articleHeadings = w.driver.findElements(headingsPath).stream().map(WebElement::getText).collect(Collectors.toList());
        w.sc.log("Headings: " + String.join(", ", articleHeadings));
        return articleHeadings;
    }

    public void nextCarousel(){
        w.driver.findElement(nextCarouselPath).click();
        w.wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(headingsPath));
    }

    public void previousCarousel(){
        w.driver.findElement(prevCarouselPath).click();
        w.wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(headingsPath));
    }

    public void gotoContactUs(){
        w.driver.findElement(menuOpenPath).click();
        w.driver.findElement(menuContactUsPath).click();
    }

    public void clickSearch() {
        w.driver.findElement(searchIconPath).click();
    }

    public void searchText(String text){
        w.driver.findElement(searchInputPath).sendKeys(text);
    }

    public void performSearch(){
        w.driver.findElement(searchInputPath).sendKeys(Keys.ENTER);
    }
}

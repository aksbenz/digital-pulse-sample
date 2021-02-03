package digitalpulse.pages;

import digitalpulse.World;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static org.assertj.core.api.Assertions.*;

public class SearchResult {
    World w;
    By searchResPagePath = By.xpath("//span[text()='Showing search results for']");
    By searchResultsPath = By.xpath("//section[contains(@class,'main-content')]//div[contains(@class,'type-post')]");
    public SearchResult(World w){
        this.w = w;
    }

    public void verifyOnSearchResultPage() {
        w.wait.until(ExpectedConditions.visibilityOf(w.driver.findElement(searchResPagePath)));
        w.attachScreenshot();
    }

    public void minimumResults(Integer count) {
        assertThat(w.driver.findElements(searchResultsPath).size()).isGreaterThanOrEqualTo(count)
                .as("Number of search results");
    }
}

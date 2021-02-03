package digitalpulse.pages;

import digitalpulse.World;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class ContactUs {
    World w;
    By headingPath = By.xpath("//h1[text()='Contact us']");
    By optionsPath = By.xpath("//section[contains(@class,'contacts')]//h2/parent::div");
    By optionHeadingPath(String heading) {
        return By.xpath("//section[contains(@class,'contacts')]//h2[normalize-space()='"+ heading +"']");
    }
    By optionLinkPath(String heading) {
        return By.xpath("//section[contains(@class,'contacts')]//h2[normalize-space()='"+ heading +"']/parent::div//a");
    }

    public ContactUs(World w){
        this.w = w;
    }

    public void verifyOnContactUsPage(){
        w.wait.until(ExpectedConditions.visibilityOf(w.driver.findElement(headingPath)));
        w.attachScreenshot();
    }

    /**
     *
     * @param expectedOptions: [{"Heading": "Value", "ContactLink": "Value"}]
     */
    public void verifyOptions(List<Map<String,String>> expectedOptions){
        SoftAssertions softAssertions = new SoftAssertions();

        // Verify number of options are same
        softAssertions.assertThat(w.driver.findElements(optionsPath).size())
                .as("Number of headings")
                .isEqualTo(expectedOptions.size());

        // Verify each option
        for (Map<String, String> expectedOption : expectedOptions) {
            String expectedHeading = expectedOption.get("Heading");
            String expectedLink = expectedOption.get("ContactLink");
            w.sc.log("Expected Heading: " + expectedHeading);
            w.sc.log("Expected Link: " + expectedLink);

            softAssertions.assertThat(w.driver.findElements(optionHeadingPath(expectedHeading)).size())
                    .as("Heading is present: " + expectedHeading)
                    .isGreaterThan(0);

            if (StringUtils.isNotBlank(expectedLink)) {
                String link = w.driver.findElements(optionLinkPath(expectedHeading)).size() > 0 ?
                        w.driver.findElement(optionLinkPath(expectedHeading)).getAttribute("href") : "";
                softAssertions.assertThat(link).as("Contact Link is present: " + expectedHeading).isEqualTo(expectedLink);
            }
            else
                // Verify Contact link not present
                assertThatThrownBy(() -> w.driver.findElement(optionLinkPath(expectedHeading))).isInstanceOf(NoSuchElementException.class);
        }

        softAssertions.assertAll();
    }
}

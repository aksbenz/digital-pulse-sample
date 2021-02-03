package digitalpulse;

import digitalpulse.pages.ContactUs;
import digitalpulse.pages.Homepage;
import digitalpulse.pages.SearchResult;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class StepDefinitions {
    World w;
    List<String> currArticleHeadings;
    List<String> newArticleHeadings;
    Homepage homepage;
    ContactUs contactUs;
    SearchResult searchResult;

    public StepDefinitions(World w){
        this.w = w;
        homepage = new Homepage(w);
        contactUs = new ContactUs(w);
        searchResult = new SearchResult(w);
    }

    @Given("I navigate to the PwC Digital Pulse website")
    public void i_navigate_to_the_pw_c_digital_pulse_website() throws InterruptedException {
        homepage.open();
    }

    @When("I am viewing the 'Home' page")
    public void i_am_viewing_the_home_page() {
        homepage.verifyOnHomepage();
    }

    @Then("I am presented with a carousel displaying {int} featured articles")
    public void i_am_presented_with_a_carousel_displaying_featured_articles(Integer noOfArticles) {
        currArticleHeadings = homepage.getCarouselArticleHeadings();
        assertThat(currArticleHeadings.size()).isEqualTo(noOfArticles).as("Number of articles displayed");
    }

    @Then("Clicking the 'Next' button on the carousel will load the next {int} featured articles")
    public void clicking_the_next_button_on_the_carousel_will_load_the_next_featured_articles(Integer noOfArticles) {
        currArticleHeadings = homepage.getCarouselArticleHeadings();
        homepage.nextCarousel();
        newArticleHeadings = homepage.getCarouselArticleHeadings();

        assertThat(newArticleHeadings.size()).as("Number of articles displayed").isEqualTo(noOfArticles);
        assertThat(newArticleHeadings).as("New articles should have different headings").doesNotContainAnyElementsOf(currArticleHeadings);
    }

    @Then("Clicking the 'Previous' button on the carousel will load the previous {int} featured articles")
    public void clicking_the_previous_button_on_the_carousel_will_load_the_previous_featured_articles(Integer noOfArticles) {
        currArticleHeadings = homepage.getCarouselArticleHeadings();
        homepage.nextCarousel();
        newArticleHeadings = homepage.getCarouselArticleHeadings();
        assertThat(newArticleHeadings).as("New articles should have different headings").doesNotContainAnyElementsOf(currArticleHeadings);

        homepage.previousCarousel();
        newArticleHeadings = homepage.getCarouselArticleHeadings();

        assertThat(newArticleHeadings.size()).as("Number of articles displayed").isEqualTo(noOfArticles);
        assertThat(newArticleHeadings).as("Previous articles should have same headings").containsExactlyElementsOf(currArticleHeadings);
    }

    @When("I select 'Contact us' from the hamburger menu")
    public void i_select_contact_us_from_the_hamburger_menu() {
        homepage.gotoContactUs();
    }

    @Then("I am taken to the 'Contact us' page")
    public void i_am_taken_to_the_contact_us_page() {
        contactUs.verifyOnContactUsPage();
    }

    /**
     * Verifies options present on Contact us page
     * @param options: Datatable parameter with Headers: "Heading", "ContactLink"
     *                 Example: | Heading              | ContactLink                                |
     *                          | PwC Digital Services | https://digital.pwc.com/en/contact-us.html |
     */
    @Then("I am presented with the below options for contacts")
    public void i_am_presented_with_the_below_options_for_contacts(List<Map<String,String>> options) {
        w.sc.log(options.toString());
        contactUs.verifyOptions(options);
    }

    @When("I click on the 'Magnifying glass' icon to perform a search")
    public void i_click_on_the_magnifying_glass_icon_to_perform_a_search() {
        homepage.clickSearch();
    }

    @When("I submit the search")
    public void i_submit_the_search() {
        homepage.performSearch();
    }

    @Then("I am taken to the search results page")
    public void i_am_taken_to_the_search_results_page() {
        searchResult.verifyOnSearchResultPage();
    }

    @Then("I am presented with at least {int} search result")
    public void i_am_presented_with_at_least_search_result(Integer count) {
        searchResult.minimumResults(count);
    }

    @And("I enter the text {string}")
    public void iEnterTheText(String searchText) {
        homepage.searchText(searchText);
    }
}

Feature: Homepage

  Scenario: Carousel Verification
    Given I navigate to the PwC Digital Pulse website
    When I am viewing the 'Home' page
    Then I am presented with a carousel displaying 3 featured articles
    And Clicking the 'Next' button on the carousel will load the next 3 featured articles
    And Clicking the 'Previous' button on the carousel will load the previous 3 featured articles

  Scenario: Contact Us Page
    Given I navigate to the PwC Digital Pulse website
    When I select 'Contact us' from the hamburger menu
    Then I am taken to the 'Contact us' page
    And I am presented with the below options for contacts
      | Heading                      | ContactLink                                |
      | PwC Digital Services         | https://digital.pwc.com/en/contact-us.html |
      | Digital Pulse editorial team |                                            |
      | Careers at PwC               | https://www.pwc.com/gx/en/careers.html     |
      | General enquiries            | https://www.pwc.com/gx/en.html             |

  Scenario: Search
    Given I navigate to the PwC Digital Pulse website
    When I click on the 'Magnifying glass' icon to perform a search
    And I enter the text "Single page applications"
    And I submit the search
    Then I am taken to the search results page
    And I am presented with at least 1 search result
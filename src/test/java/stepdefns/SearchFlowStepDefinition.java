package stepdefns;

import driverfactory.DriverFactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.Assertions;
import pages.HomePage;
import utils.PropertFileReader;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


public class SearchFlowStepDefinition {


    private static Logger logger = LogManager.getLogger(SearchFlowStepDefinition.class);


    private HomePage homePage = new HomePage(DriverFactory.getDriver());

    @Given("the departure and return fields are available on the screen")
    public void the_departure_and_return_fields_are_available_on_the_screen() {

        assertThat(homePage.fieldsArePresent()).isEqualTo(true);
        logger.info("They are present");
    }

    @When("the user enters departure and arrival into the fields and click on search button")
    public void the_user_enters_and_into_the_fields_and_click_on_search_button(DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps();
        homePage.addDetailsAndSearch(data.get(0).get("Departing"), data.get(0).get("Arrival"));

    }

    @Then("it should display {string}")
    public void it_should_display(String string) {
        assertThat(homePage.verifyMessageContent()).isEqualTo(string);
    }

    @Given("the text {string} is available on the screen")
    public void the_text_is_available_on_the_screen(String string) {
        assertThat(homePage.verifyTextWithRedPlanet()).isEqualTo(string);
    }

    @When("the user clicks the text, it should land to the homepage")
    public void the_user_clicks_the_text_it_should_land_to_the_homepage() {

        assertThat(homePage.verifyRedirectionToHomePage()).isEqualTo("Mars Airlines: Home");
    }

    @When("also on clicking  the MarsAir logo on the top left  should  take the user to the home page.")
    public void also_on_clicking_the_mars_air_logo_on_the_top_left_should_take_the_user_to_the_home_page() {
        assertThat(homePage.verifyRedirectionToHomePageOnClickingLogo()).isEqualTo("Mars Airlines: Home");
    }



    @When("the user enters {string} and {string} into the fields and {string} and click on search button")
    public void theUserEntersAndIntoTheFieldsAndAndClickOnSearchButton(String arg0, String arg1, String arg2) {
        homePage.enterPromoCodeAndClickSearch(arg0,arg1,arg2);
    }


    @Then("verify {string}  for the {string} is as expected")
    public void verifyForTheIsAsExpected(String arg0, String arg1) {
        String actualMessage =homePage.validatePromotionalCodeAndMessageAndGetActualMessage(arg1,arg0);
        assertThat(actualMessage).isEqualTo(arg0);
    }
}


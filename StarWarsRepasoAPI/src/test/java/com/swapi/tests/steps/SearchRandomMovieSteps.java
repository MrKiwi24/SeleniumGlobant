package com.swapi.tests.steps;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.openqa.selenium.WebDriver;
import utils.MyWebDriver;
import utils.WikipediaServices;

public class SearchRandomMovieSteps {
    private WebDriver driver;
    private String movieTitle;
    private WikipediaServices wikiServices;
    private Response response;
    public SearchRandomMovieSteps(){
        MyWebDriver myWebDriver = new MyWebDriver();
        this.driver = myWebDriver.getDriver();
        wikiServices = new WikipediaServices(driver);
        wikiServices.setHomePage();
    }
    @Given("User requests a random Star Wars movie")
    public void user_requests_a_random_star_wars_movie() {
        response = wikiServices.getRandomMovie();
        movieTitle = response.jsonPath().getString("title");
    }

    @When("User searches for that movie on Wikipedia")
    public void user_searches_for_that_movie_on_wikipedia() {
        wikiServices.searchTerm(movieTitle);
        String moviePageTitle = movieTitle + " - Wikipedia";
        wikiServices.verifyPageTitle(moviePageTitle, driver.getTitle());
    }

    @Then("Edit page should be displayed correctly")
    public void edit_page_should_be_displayed_correctly() {
        wikiServices.clickEditButton();
    }

    @Then("Edit page title should match the selected movie")
    public void edit_page_title_should_match_the_selected_movie() {
        String editPageTitle = "Editing " + movieTitle + " (film) - Wikipedia";
        wikiServices.verifyPageTitle(editPageTitle, driver.getTitle());
        wikiServices.checkEditHeading(movieTitle);
    }
    @After
    public void quit(){
        wikiServices.finishAssertions();
        driver.quit();
    }
}

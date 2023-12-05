package com.swapi.tests.steps;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.openqa.selenium.WebDriver;
import utils.MyWebDriver;
import utils.WikipediaServices;

public class SearchCharacterSteps {

    private String characterName;
    private String wikiPageTitle;
    private WebDriver driver;
    private WikipediaServices wikiService;
    private Response response;
    public SearchCharacterSteps(){
        MyWebDriver myWebDriver = new MyWebDriver();
        this.driver = myWebDriver.getDriver();
        wikiService = new WikipediaServices(driver);
        wikiService.setHomePage();
    }
    @Given("I am a user at the Wikipedia WebPage requesting SW character {int}")
    public void iAmAUserAtWikipedia(int characterNumber) {
        response = wikiService.getCharacterData(characterNumber);
        characterName = response.jsonPath().getString("name");
    }

    @When("I search the requested character name on Wikipedia search page")
    public void searchCharacterOnWikipedia() {
        wikiService.searchTerm(characterName);
    }

    @Then("I should be able to see the article page correctly displayed for the requested character")
    public void verifyWikipediaArticle() {
        wikiPageTitle = characterName + " - Wikipedia";
        wikiService.verifyPageTitle(wikiPageTitle, driver.getTitle());
    }
    @After
    public void quit(){
        wikiService.finishAssertions();
        driver.quit();
    }
}

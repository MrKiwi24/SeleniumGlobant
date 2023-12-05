package utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import java.util.Random;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class WikipediaServices {
    private final WebDriver driver;
    private final FluentWaitForElement wait;
    private SoftAssert softAssert;
    public WikipediaServices(WebDriver driver){
        this.driver = driver;
        wait = new FluentWaitForElement();
        softAssert = new SoftAssert();
    }
    public void setHomePage(){
        driver.get("https://www.wikipedia.org/");
    }
    public void searchTerm(String searchTerm){
        driver.findElement(By.cssSelector("#searchInput")).sendKeys(searchTerm);
        driver.findElement(By.cssSelector("#searchInput")).sendKeys(Keys.ENTER);
        By contentContainer = By.cssSelector(".mw-content-container");
        wait.forElement(contentContainer, driver);
    }
    public void verifyPageTitle(String expectedValue, String currentValue){
        softAssert.assertEquals(expectedValue, currentValue);

    }
    public void clickEditButton(){
        driver.findElement(By.cssSelector("li[id='ca-edit'] a[title='Edit this page [Alt+Shift+e]']")).click();
        By popUpWarning = By.xpath("//div[@class='oo-ui-window-content oo-ui-dialog-content oo-ui-messageDialog-content oo-ui-window-content-setup oo-ui-window-content-ready']");
        wait.forElement(popUpWarning, driver);
        driver.findElement(By.xpath("//span[@class='oo-ui-widget oo-ui-widget-enabled oo-ui-buttonElement oo-ui-labelElement oo-ui-flaggedElement-progressive oo-ui-flaggedElement-primary oo-ui-buttonWidget oo-ui-actionWidget oo-ui-buttonElement-framed']//a[@role='button']")).click();
    }
    public void checkEditHeading(String movieTitle){
        softAssert.assertEquals(
                "Editing " + movieTitle,
                driver.findElement(By.cssSelector("#firstHeadingTitle")).getText()
                );
    }
    public void finishAssertions(){
        softAssert.assertAll();
    }
    public Response getRandomMovie(){
        RestAssured.baseURI = "https://swapi.dev/api/films/";
        Random rand = new Random();
        Response response = given().get("/"+rand.nextInt(1,6));
        return response;
    }
    public Response getCharacterData(int characterNumber){
        RestAssured.baseURI = "https://swapi.dev/api/people/";
        Response response = given().get("/" + characterNumber);
        return response;
    }
}

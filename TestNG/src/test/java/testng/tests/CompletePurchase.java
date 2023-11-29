package testng.tests;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import testng.services.FluentWaitForElement;
import testng.services.SwagLabsOperations;


public class CompletePurchase {
    private final WebDriver driver;
    public CompletePurchase(WebDriver driver){
        this.driver = driver;
        swagOps = new SwagLabsOperations(this.driver);
    }
    private SwagLabsOperations swagOps;
    private FluentWaitForElement wait = new FluentWaitForElement();
    public void completePurchase(){
        swagOps.setUpDriver();
        swagOps.login();
        swagOps.addRandomItemsAndSeeCart(1);
        swagOps.checkout();

        By pageContents = By.xpath("//div[@id='contents_wrapper']");
        wait.forElement(pageContents, driver);
    }
}

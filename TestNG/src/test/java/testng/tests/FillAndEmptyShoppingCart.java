package testng.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;
import testng.services.SwagLabsOperations;

import java.util.List;

public class FillAndEmptyShoppingCart {
    private final WebDriver driver;
    public FillAndEmptyShoppingCart(WebDriver driver){
        this.driver = driver;
        swagOps = new SwagLabsOperations(this.driver);
    }
    public SwagLabsOperations swagOps;
    public void fillAndEmptyShoppingCart(){
        swagOps.setUpDriver();
        swagOps.login();
        swagOps.addRandomItemsAndSeeCart(3);
        swagOps.emptyCart();
    }
}
